<template>
  <div class="api" id="app">
    <div v-if="!running">
      <img  alt="rouge" src="../assets/Button_Icon_Red.png" v-on:click="startStop()" >
      <p>allumer le chenillard</p>
    </div>
    <div v-else>
      <img alt="vert" src="../assets/Button_Icon_Green.png" v-on:click="startStop()" >
      <p>arrêter le chenillard</p>
    </div>
    <table>
      <tr>
        <td>
          <img id="up" class="img" alt="accélérer" src="../assets/lievre.png" v-on:click="accelerer()" >
        </td>
        <td>
          <img id="down" class="img" alt="ralentir" src="../assets/tortue.png" v-on:click="ralentir()" >
        </td>
      </tr>
      <tr>
        <td>
          <p>Accélérer</p>
        </td>
        <td>
          <p>Ralentir</p>
        </td>
      </tr>
    </table>
  </div>
</template>

<style src="../assets/style.css"></style>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="../script/scripts.js"></script>
