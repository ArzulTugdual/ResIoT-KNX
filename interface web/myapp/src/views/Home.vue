<template>
  <div class="home">
    <img  v-if="rouge" src="../assets/Button_Icon_Red.svg.png" v-on:click="changeImage()" />
    <img  v-else src="../assets/Button_Icon_Green.svg.png" v-on:click="changeImage()" />
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import HelloWorld from '@/components/HelloWorld.vue' // @ is an alias to /src

@Component({
  components: {
    HelloWorld
  }
})

export default class Home extends Vue {
  rouge=true
  changeImage () {
    this.rouge = !this.rouge
  }
}
</script>
