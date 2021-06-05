<template>
  <header>
    <h1>Lottery MainPage</h1>
    <nav>
      <ul>
        <li><a :href="loginPath">로그인</a></li>
      </ul>
    </nav>
  </header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      loginPath: 'http://localhost:9090',
    }
  },
  methods: {

  }
}

</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
}

h1 {
  margin-left: 50px;
  color: gold;
}

nav {
  display: inline-block;
  vertical-align: middle;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
}

a {
  display: block;
  text-align: center;
  margin: .10rem;
  padding: .5rem 1rem;
  text-decoration: none;
  font-weight: bold;
  color: #222222;
  background: gold;
}

a:hover {
  background: goldenrod;
}

*, *:before, *:after {
  box-sizing: inherit;
}
</style>