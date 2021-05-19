<template>
  <div id="root">
    <h1>{{ msg }}</h1>
    <form v-on:submit="onSubmitForm">
      <input type="text" v-model="round">
      <button>검색</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "MainPage",
  data() {
    return {
      round: ''
    }
  },
  methods: {
    onSubmitForm(e) {
      e.preventDefault();
      console.log(this.round);
      this.axios.get(`http://localhost:8080/lotto/search/${this.round}`)
          .then(response => console.log(response))
          .catch(err => console.log(err));
    }
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>