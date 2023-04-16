<template>
    <div id="container">
        <div id="header">
            <button id="reload-map-btn" class="headerBtn" @click="reloadPage">
                <div>Reload<br>Map</div>
            </button>
            <button id="add-all-btn" class="headerBtn" @click="showAlllMarkers">
                <div v-if="isActive == true">Show All Markers</div>
                <div v-else>Hide All Markers</div>
            </button>
            <router-link :to="'/map-db/add/' + this.addLat + '/' + this.addLog" v-if="showAdd == true">
                <button class="headerBtn">Register <br/> Info</button>
            </router-link>
        </div>
        <div>
            <router-view></router-view>
        </div>
        <div id="mapView"></div>
    </div>
</template>

<script>
    import  L from 'leaflet'
    import axios from 'axios'

    delete  L.Icon.Default.prototype._getIconUrl

    L.Icon.Default.mergeOptions(
        {   iconUrl         : require( 'leaflet/dist/images/marker-icon.png' )
        ,   iconRetinaUrl   : require( 'leaflet/dist/images/marker-icon-2x.png' )
        ,   shadowUrl       : require( 'leaflet/dist/images/marker-shadow.png' )
        }
    )

    let markers = [];
    var map;
    export default {
        mounted() {
            map = L.map('mapView', { zoomCotrol: false });
            map.setView([43.151505, 141.655655], 15);
            var gsiattr = "<a href='https://maps.gsi.go.jp/development/ichiran.html' target='_blank'>地理院タイル</a>";
            var gsi = L.tileLayer('https://cyberjapandata.gsi.go.jp/xyz/std/{z}/{x}/{y}.png', { attribution: gsiattr });
            var gsipale = L.tileLayer('http://cyberjapandata.gsi.go.jp/xyz/pale/{z}/{x}/{y}.png', { attribution: gsiattr });
            var osm = L.tileLayer('http://tile.openstreetmap.jp/{z}/{x}/{y}.png', { attribution: "<a href='http://osm.org/copyright' target='_blank'>OpenStreetMap</a> contributors" });

            var gsirelief = L.tileLayer('http://cyberjapandata.gsi.go.jp/xyz/relief/{z}/{x}/{y}.png', { opacity: 0.7, maxNativeZoom: 15, attribution: gsiattr });
            var gsirehillshademap = L.tileLayer('http://cyberjapandata.gsi.go.jp/xyz/hillshademap/{z}/{x}/{y}.png', { opacity: 0.5, maxNativeZoom: 16, attribution: gsiattr });
            var baseMaps = {
                "地理院地図": gsi,
                "淡色地図": gsipale,
                "オープンストリートマップ": osm
            };
            var overlayMaps = {
                "識別標高図": gsirelief,
                "陰影起伏図": gsirehillshademap
            };
            L.control.layers(baseMaps, overlayMaps).addTo(map);
            gsi.addTo(map);

            // マーカー追加 & 新規登録画面表示
            map.on('click', this.onMapClick);

            this.autoShowAllMarkers();

        },
        data() {
            return {
                isActive: false,
                addLat: null,
                addLog: null,
                showAdd: false
            }
        },
        methods: {
            onMapClick(e) {
                L.marker(e.latlng).addTo(map);
                this.addLat = e.latlng.lat.toFixed(6);
                this.addLog = e.latlng.lng.toFixed(6);
                this.showAdd = true;
            },
            showAlllMarkers() {
                if (this.isActive == true) {
                    this.getInfoList().then(response => {
                        var buildingInfo = response.data;
                        for (var i in buildingInfo) {
                            // var buildingId = buildingInfo[i].buildingId;
                            // var name = buildingInfo[i].name;
                            // var address = buildingInfo[i].address;
                            var latitude = buildingInfo[i].latitude;
                            var longitude = buildingInfo[i].longitude;
                            var marker = L.marker([parseFloat(latitude), parseFloat(longitude)], { customId: buildingInfo[i].buildingId, riseOnHover: true });
                            markers.push(marker);
                            marker.addTo(map);
                            // var popupContent = function() {
                            //     location.href = '/map-db/detail/' + buildingId
                            // };
                            // var popup = L.popup({width:450,height:450}).setContent(popupContent);
                            // marker.bindPopup(popup);
                            marker.on('click', function() {  
                                location.href = '/map-db/detail/' + this.options.customId;
                            })
                        }
                    })
                } else {
                    for (var i in markers) {
                        map.removeLayer(markers[i]);
                    }
                }
                this.isActive = !this.isActive;
            },
            autoShowAllMarkers() {
                this.getInfoList().then(response => {
                    var buildingInfo = response.data;
                    for (var i in buildingInfo) {
                        // var buildingId = buildingInfo[i].buildingId;
                        // var name = buildingInfo[i].name;
                        // var address = buildingInfo[i].address;
                        var latitude = buildingInfo[i].latitude;
                        var longitude = buildingInfo[i].longitude;
                        var marker = L.marker([parseFloat(latitude), parseFloat(longitude)], { customId: buildingInfo[i].buildingId, riseOnHover: true });
                        markers.push(marker);
                        marker.addTo(map);
                        // var popupContent = function() {
                        //     location.href = '/map-db/detail/' + buildingId
                        // };
                        // var popup = L.popup({width:450,height:450}).setContent(popupContent);
                        // marker.bindPopup(popup);
                        marker.on('click', function() {
                            location.href = '/map-db/detail/' + this.options.customId;
                        })
                    }
                })
            },
            getInfoList() {
                const path = process.env.VUE_APP_API_BASE_URL + '/map-db/building-info/list'
                return axios.get(path);
            },
            reloadPage() {
                location.reload();
            },
        },
    }
</script>

<style scoped>
#mapView {
    border: solid 2px #8c8c8e
}
.addInfo {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 9999;
    opacity: 0;
    visibility: hidden;
    transition: .6s;
}
.addInfo.is-show {
    opacity: 1;
    visibility: visible;
}
#mapView {
    position: perspective;
    width: 100%;
    height: 99vh;
    margin: 0;
    min-height: -webkit-fill-available;
}
.infostyle {
    border: solid 1px;
    background-color: azure;
    border-radius: 10px;
    opacity: 0.8;
    padding: 5px;
    font-size: 15px;
    color: black;
}
.headerBtn {
  width: 100px;
  padding: 5px;
  border: 2px solid #8c8c8e;
  background-color: #8c8c8e;
  border-color:whitesmoke;
  color: whitesmoke;
  font-weight: bold;
}
.headerBtn:hover {
  width: 100px;
  padding: 5px;
  background-color: whitesmoke;
  border-color: #8c8c8e;
  color: #8c8c8e;
  font-weight: bold;
}
</style>