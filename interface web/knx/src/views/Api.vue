<template>
  <div class="api" id="app">
    <div>
      <img alt="vert" src="../assets/Button_Icon_Green.png" v-on:click="startStop(false)" >
      <p>lancer le chenillard</p>
    </div>
    <div>
      <img alt="rouge" src="../assets/Button_Icon_Red.png" v-on:click="startStop(true)" >
      <p>arrêter le chenillard</p>
    </div>
    <table>
      <tr>
        <td>
          <img class="img" alt="random" src="../assets/random.png" v-on:click="aleatoire()" >
        </td>
        <td>
          <img class="img" alt="appel de phare" src="../assets/sirene.png" v-on:click="phare()" >
        </td>
      </tr>
      <tr>
        <td>
          <p>lancer aléatoirement</p>
        </td>
        <td>
          <p>appel de phare</p>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <img class="img" alt="accélérer" src="../assets/lievre.png" v-on:click="accelerer()" >
        </td>
        <td>
          <img class="img" alt="ralentir" src="../assets/tortue.png" v-on:click="ralentir()" >
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
