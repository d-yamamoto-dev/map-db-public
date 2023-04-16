<template>
    <div class="container">
        <input type="button" id="reload-btn" value="地図更新" th:onclick="reloadMap()">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th v-for="(label, key) in header" :key="key" class="align-middle">{{label}}</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="info in infoList" :key="info.buildingId" class="align-middle">
                    <!-- <td>{{info.buildingId}}</td> -->
                    <td>{{info.name}}</td>
                    <td>{{info.address}}</td>
                    <td>{{info.latitude}}</td>
                    <td>{{info.longitude}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            header: {buildingId:"ID", name:"Name", address:"Address", latitude:"Latitude", longtitude:"Longitude" },
            infoList: []
        }
    },
    methods: {
        getInfoList() {
            const path = process.env.VUE_APP_API_BASE_URL + '/map-db/building-info/list'
            axios.get(path).then(response => {
                this.infoList = response.data
            }).catch(error => {
                console.log(error)
            })
        }
    },
    created() {
        this.getInfoList()
    }
}
</script>

<style scoped>
table{
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
  }
  
  table th,table td{
    padding: 10px 0;
    text-align: center;
  }

  thead tr:nth-child(1) {
    background: #8c8c8e
  }
  
  tbody tr:nth-child(odd){
    background-color: #eee
  }

  #name-header {
    color: whitesmoke;
    font-weight: bold;
  }

  #address-header {
    color: whitesmoke;
    font-weight: bold;
  }

  #reload-btn {
    border: 2px solid #8c8c8e;
    margin-bottom: 5px;
    background-color: #8c8c8e;
    color: whitesmoke;
  }

  #reload-btn:hover {
    background-color: whitesmoke;
    border-color: #8c8c8e;
    color: #8c8c8e;
  }

  .detail-btn {
    border: 2px solid #8c8c8e;
    background-color: #8c8c8e;
    color: whitesmoke;
  }

  .detail-btn:hover {
    background-color: whitesmoke;
    border-color: #8c8c8e;
    color: #8c8c8e;
  }
</style>
