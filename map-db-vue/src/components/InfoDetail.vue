<template>
    <div class="container">
        <div>
            <label id="name-header">Name</label>
            <br />
            <div id="name-value" class="form-control">{{ name }}</div>
        </div>
        <div>
            <label id="address-header">Address</label>
            <br />
            <div id="address-value" class="form-control">{{ address }}</div>
        </div>
        <div>
            <div id="latitude-value" class="form-control">{{ latitude }}</div>
        </div>
        <div>
            <div id="longitude-value" class="form-control">{{ longitude }}</div>
        </div>
        <br/>
        <button id="edit-btn" class="btn" @click="clickEdit">Edit</button>
        <button id="close-btn" class="btn" @click="clickClose">Close</button>
    </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: null,
      address: null,
      latitude: null,
      longitude: null,
    };
  },
  methods: {
    showDetail() {
      const path =
        process.env.VUE_APP_API_BASE_URL + "/map-db/building-info/" + this.$route.params.buildingId;
      axios
        .get(path).then(response => {
            this.name = response.data.name
            this.address = response.data.address
            this.latitude = response.data.latitude
            this.longitude = response.data.longitude
        })
        .catch(error => {
            console.log(error)
        })
    },
    clickEdit() {
      window.location.href="/map-db/edit/" 
      + this.$route.params.buildingId 
      + "/" + this.name 
      + "/" + this.address 
      + "/" + this.latitude 
      + "/" + this.longitude;
    },
    clickClose() {
      location.href = '/map-db/';
      // window.close();
    }
  },
  created() {
    this.showDetail();
  }
};
</script>

<style scoped>
body {
  background: whitesmoke;
}

#name-header {
  padding: 1px 1px;
  padding-left: 1px;
  /* margin: 2em 0; */
  color: #7f7f7f;
  font-weight: bold;
}

#name-value {
  font-size: 1em;
  padding: 5px 5px 10px;
  font-family: "Source Sans Pro", arial, sans-serif;
  border: 1px solid #cecece;
  background: #d7d7d7;
  color: black;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  -moz-background-clip: padding;
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  width: 80%;
  max-width: 600px;
}

#address-header {
  padding: 1px 1px;
  padding-left: 1px;
  margin-top: 1em;
  color: #7f7f7f;
  font-weight: bold;
}

#address-value {
  font-size: 1em;
  padding: 5px 5px 10px;
  font-family: "Source Sans Pro", arial, sans-serif;
  border: 1px solid #cecece;
  background: #d7d7d7;
  color: black;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  -moz-background-clip: padding;
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  max-width: 600px;
}

#list-btn {
  color: #7f7f7f;
  font-weight: bold;
}

.btn {
  width: 50px;
  padding: 5px;
  border: 2px solid #8c8c8e;
  background-color: #8c8c8e;
  border-color:whitesmoke;
  color: whitesmoke;
  font-weight: bold;
}

.btn:hover {
  width: 50px;
  padding: 5px;
  background-color: whitesmoke;
  border-color: #8c8c8e;
  border-color:#8c8c8e;
  color: #8c8c8e;
  font-weight: bold;
}

#latitude-value {
  display:none
}

#longitude-value {
  display:none
}
</style>