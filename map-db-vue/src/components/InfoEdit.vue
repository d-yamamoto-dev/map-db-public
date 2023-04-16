<template>
  <div class="container">
    <form>
        <div>
            <input v-model="buildingId" v-value="buildingId" id="buildingId-value" class="form-control">
        </div>
      <div>
        <label id="name-header">Name</label>
        <br />
        <input v-model="name" v-value="name" id="name-value" class="form-control">
      </div>
      <div>
        <label id="address-header">Address</label>
        <br />
        <input v-model="address" v-value="address" id="address-value" class="form-control">
      </div>
      <div>
        <input v-model="latitude" v-value="latitude" id="latitude-value" class="form-control">
      </div>
      <div>
        <input v-model="longitude" v-value="longitude" id="longitude-value" class="form-control">
      </div>
      <br>
      <router-link :to="'/map-db/detail/' + this.$route.params.buildingId">
        <button
            type="submit"
            @click="updateInfo"
            id="submit-btn"
            class="btn btn-primary"
        >
            update
        </button>
      </router-link>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
        buildingId: this.$route.params.buildingId,
        name: this.$route.params.name,
        address: this.$route.params.address,
        latitude: this.$route.params.latitude,
        longitude: this.$route.params.longitude,
    };
  },
  methods: {
    updateInfo() {
      const path =
        process.env.VUE_APP_API_BASE_URL + "/map-db/building-info/update";
      axios
        .put(path, {
          buildingId: this.buildingId,
          name: this.name,
          address: this.address,
          latitude: this.latitude,
          longitude: this.longitude,
        })
        .then({}
        )
        .catch((error) => console.log(error));
    },
    popupMessage() {
      alert('Updated Building Info');
      this.closeWin;
    },
    closeWin() {
      window.close();
    }
  },
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

#submit-btn {
  width: 60px;
  padding: 5px;
  border: 2px solid #8c8c8e;
  background-color: #8c8c8e;
  color: whitesmoke;
  font-weight: bold;
}

#submit-btn:hover {
  width: 60px;
  padding: 5px;
  background-color: whitesmoke;
  border-color: #8c8c8e;
  color: #8c8c8e;
  font-weight: bold;
}

#buildingId-value {
    display:none
}

#latitude-value {
    display:none
}

#longitude-value {
    display:none
}
</style>