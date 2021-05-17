<template>
  <div class="home">
    <div v-if="red">
      <img  alt="rouge" src="../assets/Button_Icon_Red.png" v-on:click="changeImage()" >
      <p>allumer le chenillard</p>
    </div>
    <div v-else>
      <img alt="vert" src="../assets/Button_Icon_Green.svg.png" v-on:click="changeImage()" >
      <p>arrêter le chenillard</p>
    </div>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import HelloWorld from '@/components/HelloWorld.vue' // @ is an alias to /src

@Component({
  components: {
    HelloWorld
  }
})

export default class Home extends Vue {
  red=true;
  changeImage() {
    this.red = !this.red;
  }
}
</script>
