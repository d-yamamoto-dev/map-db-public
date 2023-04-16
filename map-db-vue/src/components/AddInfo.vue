<template>
  <div class="container">
    <form>
      <div>
        <label id="name-header">Name</label>
        <br />
        <input v-model="name" id="name-value" class="form-control" />
      </div>
      <div>
        <label id="address-header">Address</label>
        <br />
        <input v-model="address" id="address-value" class="form-control" />
      </div>
      <div>
        <input
          v-model="latitude"
          v-value="latitude"
          name="latitude"
          class="form-control"
          type="hidden"
        />
      </div>
      <div>
        <input
          v-model="longitude"
          v-value="longitude"
          name="longitude"
          class="form-control"
          type="hidden"
        />
      </div>
      <br>
      <div>
        <router-link :to="'/map-db/'">
          <button
            type="submit"
            @click="addInfo"
            id="submit-btn"
            class="btn"
          >
          Add
          </button>
          <button id="cancel-btn" class="btn" @click="clickCancel">
          Cancel
          </button>
        </router-link>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "addInfo",
  data() {
    return {
      latitude: this.$route.params.latitude,
      longitude: this.$route.params.longitude,
    };
  },
  methods: {
    addInfo() {
      const path =
        process.env.VUE_APP_API_BASE_URL + "/map-db/building-info/create";
      axios
        .post(path, {
          name: this.name,
          address: this.address,
          latitude: this.latitude,
          longitude: this.longitude,
        })
        .catch((error) => console.log(error));
    },
    clickCancel() {
      location.href = '/map-db/';
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

.btn {
  width: 60px;
  padding: 5px;
  border: 2px solid #8c8c8e;
  background-color: #8c8c8e;
  border-color:whitesmoke;
  color: whitesmoke;
  font-weight: bold;
}

.btn:hover {
  width: 60px;
  padding: 5px;
  background-color: whitesmoke;
  border-color: #8c8c8e;
  border-color:#8c8c8e;
  color: #8c8c8e;
  font-weight: bold;
}
</style>