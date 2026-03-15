<template>
  <header>
    <h1>Very good restoran planner</h1>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/view">View Booked</router-link>
    </nav>
  </header>
  <!-- The router-view is where the matched route will render -->
  <div class="content">
    <router-view />
  </div>
</template>


<script setup>
</script>

<style>
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: white;
}

html,
body,
#app {
  margin: 0;
  min-height: 100%;
  height: 100%;
}

header {
  background-color: #007bff;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content {
  background-color: #f6f7f8;
  min-height: 100%;
  padding: 10px;
}

h1 {
  text-shadow: 4px 4px 6px rgba(0, 0, 0, 0.5);
  color: beige;
  margin: 0;
  font-size: 30px;
}
</style>
