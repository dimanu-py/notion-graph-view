<template>
  <div>
    <button
        v-if="!isPanelOpen"
        class="toggle-button"
        @click="togglePanel">
      Abrir Panel
    </button>

    <aside v-if="isPanelOpen" class="side-panel">
      <button class="close-button" @click="togglePanel">&times;</button>
      <div class="database-id-section">
        <input id="db-id" v-model="databaseId" type="text" placeholder="Enter database id" />
      </div>
    </aside>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'SidePanel',
  setup() {
    const databaseId = ref('');
    const isPanelOpen = ref(true);

    const togglePanel = () => {
      isPanelOpen.value = !isPanelOpen.value;
    };

    return {
      databaseId,
      isPanelOpen,
      togglePanel
    };
  }
});
</script>

<style scoped>
.side-panel {
  background-color: #202124;
  color: #FFFFFF;
  width: 250px;
  height: 100%;
  padding: 16px;
  box-sizing: border-box;
  flex-shrink: 0;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  display: flex;
  flex-direction: column;
}

.close-button {
  background: none;
  border: none;
  color: #FFFFFF;
  font-size: 24px;
  cursor: pointer;
  align-self: flex-start;
  margin-bottom: 16px;
}

.toggle-button {
  position: fixed;
  top: 16px;
  left: 16px;
  z-index: 1100;
  background-color: #202124;
  color: #FFFFFF;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
  font-size: 16px;
}

.database-id-section {
  margin-bottom: 20px;
}

.database-id-section input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
</style>
