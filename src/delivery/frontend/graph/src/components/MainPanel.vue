<template>
  <main class="main-panel">
    <div class="content-section">
      <h1>Main Panel</h1>
      <p>Esta es la sección principal del panel.</p>
    </div>
  </main>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'MainPanel',
});
</script>

<style scoped>
.main-panel {
  background-color: #18191A;
  color: #FFFFFF;
  width: 100%;
  height: 100%;
  padding: 16px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 0;
  left: 250px;
  z-index: 900;
}

.content-section {
  margin-top: 20px;
}

.content-section h1 {
  margin-bottom: 16px;
}

.content-section p {
  line-height: 1.5;
}
</style>

