<template>
  <div class="home">
    <transition appear name="fade">
      <img src="@/assets/KNX_logo.svg" />
    </transition>
    <transition appear name="fade">
      <HelloWorld msg="Bienvenue sur le réseau KNX"/>
    </transition>
  </div>
</template>

<style src="../assets/style.css"></style>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import HelloWorld from '@/components/HelloWorld.vue';

@Component({
  components: {
    HelloWorld,
  }
})

export default class Home extends Vue {
  show=true
}
</script>
