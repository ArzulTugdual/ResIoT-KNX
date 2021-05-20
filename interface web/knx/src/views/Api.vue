<template>
  <div class="api" id="app">
    <p>{{ info }}</p>
    <button type="button" v-on:click="allumer()">Allumer le chenillard</button>
  </div>
</template>

<style src="../assets/style.css"></style>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="../script/scripts.js"></script>
