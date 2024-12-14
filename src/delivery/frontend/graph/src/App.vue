<template>
  <div class="app-container">
    <side-panel />
    <main-panel />
  </div>
</template>

<script>
import { defineComponent } from 'vue';
import SidePanel from './components/SidePanel.vue';
import MainPanel from './components/MainPanel.vue';

export default defineComponent({
  name: 'App',
  components: {
    SidePanel,
    MainPanel
  }
});
</script>

<style>
.app-container {
  display: flex;
  height: 100vh;
  margin: 0;
  padding: 0;
  font-family: sans-serif;
}
</style>

