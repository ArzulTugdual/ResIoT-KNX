<template>
  <div class="api" id="app">
    <div v-if="red">
      <img  alt="rouge" src="../assets/Button_Icon_Red.png" v-on:click="startStop()" >
      <p>allumer le chenillard</p>
    </div>
    <div v-else>
      <img alt="vert" src="../assets/Button_Icon_Green.png" v-on:click="startStop()" >
      <p>arrêter le chenillard</p>
    </div>
    <div>
      <img class="img" alt="accélérer" src="../assets/lievre.png" v-on:click="accelerer()" >
      <img class="img" alt="ralentir" src="../assets/tortue.png" v-on:click="ralentir()" >
    </div>
  </div>
</template>

<style src="../assets/style.css"></style>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="../script/scripts.js"></script>
