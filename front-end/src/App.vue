<template>
  <div id="app">
    <Header/>
    <MainPage/>
  </div>
</template>

<script>
import Header from './components/Header'
import MainPage from './components/MainPage'

export default {
  name: 'App',
  components: {
    Header,
    MainPage
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

html {
  background: #222222;
}
</style>
