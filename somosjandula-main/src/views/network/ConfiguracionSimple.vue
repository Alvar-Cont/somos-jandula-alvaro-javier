<template>
  <div class="configuracion">
    <div class="card">
      <h2 class="card-title">⚙️ Configuración del Sistema</h2>
      
      <div class="config-section">
        <h3>🔄 Actualización Automática</h3>
        <label class="switch">
          <input v-model="autoRefresh" type="checkbox" />
          <span class="slider"></span>
        </label>
        <p class="description">{{ autoRefresh ? 'Habilitado' : 'Deshabilitado' }}</p>
      </div>

      <div class="config-section">
        <h3>⏱️ Intervalo de Refresco</h3>
        <div class="slider-container">
          <input 
            v-model.number="refreshInterval" 
            type="range" 
            min="5" 
            max="120" 
            step="5"
            class="range-slider"
          />
          <span class="value-display">{{ refreshInterval }}s</span>
        </div>
        <p class="description">Los datos se actualizarán cada {{ refreshInterval }} segundos</p>
      </div>

      <div class="config-section">
        <h3>🎨 Tema</h3>
        <select v-model="tema" class="select-input">
          <option value="light">Claro</option>
          <option value="dark">Oscuro</option>
          <option value="auto">Automático</option>
        </select>
        <p class="description">Selecciona el tema de la interfaz</p>
      </div>

      <button @click="guardarConfiguracion" class="btn btn-primary">
        💾 Guardar Configuración
      </button>
    </div>

    <div class="card">
      <h2 class="card-title">ℹ️ Información del Sistema</h2>
      
      <div class="info-grid">
        <div class="info-item">
          <span class="info-label">🔗 API Backend:</span>
          <span class="info-value">http://localhost:8084</span>
        </div>
        <div class="info-item">
          <span class="info-label">🗄️ Base de Datos:</span>
          <span class="info-value">redes_db (MySQL)</span>
        </div>
        <div class="info-item">
          <span class="info-label">📱 Frontend:</span>
          <span class="info-value">Vue 3 + Vite</span>
        </div>
        <div class="info-item">
          <span class="info-label">🔐 Autenticación:</span>
          <span class="info-value">JWT Token</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Configuracion',
  data() {
    return {
      autoRefresh: true,
      refreshInterval: 30,
      tema: 'light'
    }
  },
  methods: {
    guardarConfiguracion() {
      alert('✅ Configuración guardada exitosamente')
      // Guardar en localStorage
      localStorage.setItem('config', JSON.stringify({
        autoRefresh: this.autoRefresh,
        refreshInterval: this.refreshInterval,
        tema: this.tema
      }))
    }
  },
  mounted() {
    // Cargar configuración guardada
    const config = localStorage.getItem('config')
    if (config) {
      const parsed = JSON.parse(config)
      this.autoRefresh = parsed.autoRefresh
      this.refreshInterval = parsed.refreshInterval
      this.tema = parsed.tema
    }
  }
}
</script>

<style scoped>
.configuracion {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card {
  background: white;
  border-radius: 12px;
  padding: 25px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 1.3em;
  color: #333;
  margin-bottom: 20px;
  border-bottom: 2px solid #667eea;
  padding-bottom: 10px;
}

.config-section {
  margin-bottom: 30px;
  padding-bottom: 20px;
  border-bottom: 1px solid #e0e0e0;
}

.config-section:last-of-type {
  border-bottom: none;
}

.config-section h3 {
  margin: 0 0 15px 0;
  color: #333;
  font-size: 1.1em;
}

.description {
  margin: 10px 0 0 0;
  color: #666;
  font-size: 0.9em;
}

.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  transition: 0.4s;
  border-radius: 34px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: 0.4s;
  border-radius: 50%;
}

input:checked + .slider {
  background-color: #667eea;
}

input:checked + .slider:before {
  transform: translateX(26px);
}

.slider-container {
  display: flex;
  align-items: center;
  gap: 15px;
  margin: 15px 0;
}

.range-slider {
  flex: 1;
  cursor: pointer;
}

.value-display {
  background: #f0f0f0;
  padding: 5px 10px;
  border-radius: 5px;
  font-weight: 600;
  color: #667eea;
  min-width: 60px;
  text-align: center;
}

.select-input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1em;
  cursor: pointer;
  background: white;
}

.btn {
  padding: 12px 24px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1em;
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  margin-top: 20px;
}

.btn-primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.info-item {
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  border-left: 4px solid #667eea;
}

.info-label {
  display: block;
  color: #666;
  font-weight: 600;
  margin-bottom: 5px;
}

.info-value {
  display: block;
  color: #333;
  font-family: monospace;
  word-break: break-all;
}
</style>
