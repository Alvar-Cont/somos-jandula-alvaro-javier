<template>
  <div id="app" style="padding-top: 20px;">
    <!-- Navegación de Tabs -->
    <div class="nav-tabs">
      <button 
        v-for="tab in tabs" 
        :key="tab"
        @click="tabActivo = tab"
        :class="['tab-btn', { activo: tabActivo === tab }]"
      >
        {{ tab === 'dashboard' ? '📊 Dashboard' : tab === 'config' ? '⚙️ Configuración' : '🔧 Administración' }}
      </button>
    </div>

    <!-- Contenido -->
    <div class="container">
      <div v-if="tabActivo === 'dashboard'">
        <Dashboard />
      </div>
      <div v-if="tabActivo === 'config'">
        <Configuracion />
      </div>
      <div v-if="tabActivo === 'admin'">
        <Administracion :redes="redes" />
      </div>
    </div>
  </div>
</template>

<script>
import Dashboard from './Dashboard.vue'
import ConfiguracionSimple from './ConfiguracionSimple.vue'
import AdministracionSimple from './AdministracionSimple.vue'

export default {
  components: {
    Dashboard,
    'Configuracion': ConfiguracionSimple,
    'Administracion': AdministracionSimple
  },
  data() {
    return {
      tabActivo: 'dashboard',
      tabs: ['dashboard', 'config', 'admin']
    }
  }
}
</script>

<style scoped>
#app {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
}

.nav-tabs {
  background: rgba(255, 255, 255, 0.95);
  display: flex;
  gap: 0;
  padding: 0;
  max-width: 1200px;
  margin: 0 auto;
  border-bottom: 2px solid #e0e0e0;
  border-radius: 8px 8px 0 0;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.tab-btn {
  flex: 1;
  padding: 15px 20px;
  border: none;
  background: transparent;
  cursor: pointer;
  font-size: 14px;
  font-weight: 600;
  color: #666;
  transition: all 0.3s ease;
  border-bottom: 3px solid transparent;
}

.tab-btn:hover {
  color: #667eea;
  background: rgba(102, 126, 234, 0.05);
}

.tab-btn.activo {
  color: #667eea;
  border-bottom-color: #667eea;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  min-height: calc(100vh - 150px);
}
</style>

