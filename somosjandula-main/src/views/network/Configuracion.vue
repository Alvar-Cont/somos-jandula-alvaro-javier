<template>
  <div class="card">
    <h2 class="card-title">🔧 Configuración del Sistema</h2>

    <div class="config-section">
      <!-- Auto Refresh -->
      <div class="config-item">
        <div class="config-header">
          <div>
            <h3>Actualización Automática</h3>
            <p class="config-description">Refrescar datos automáticamente en el dashboard</p>
          </div>
          <label class="switch">
            <input 
              type="checkbox" 
              v-model="autoRefresh"
            />
            <span class="slider"></span>
          </label>
        </div>
      </div>

      <div class="divider"></div>

      <!-- Intervalo de Refresco -->
      <div class="config-item">
        <h3>Intervalo de Refresco</h3>
        <p class="config-description">
          Los datos se actualizarán cada {{ refreshInterval }} segundos
        </p>
        
        <div class="slider-container">
          <input 
            type="range" 
            min="5" 
            max="120" 
            step="5"
            v-model.number="refreshInterval"
            :disabled="!autoRefresh"
            class="range-slider"
          />
          <div class="slider-value">
            <span class="value-display">{{ refreshInterval }}s</span>
          </div>
        </div>

        <div class="slider-labels">
          <span>5s</span>
          <span>60s</span>
          <span>120s</span>
        </div>
      </div>

      <div class="divider"></div>

      <!-- Información del Sistema -->
      <div class="config-item">
        <h3>📊 Información del Sistema</h3>
        
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">API Base URL:</span>
            <span class="info-value">http://localhost:8084</span>
          </div>
          
          <div class="info-item">
            <span class="info-label">Redes monitoreadas:</span>
            <span class="info-value badge-info">{{ redes.length }}</span>
          </div>
          
          <div class="info-item">
            <span class="info-label">Registros de telemetría:</span>
            <span class="info-value badge-info">{{ telemetria.length }}</span>
          </div>
          
          <div class="info-item">
            <span class="info-label">Estado del auto-refresh:</span>
            <span :class="['info-value', 'badge-status', autoRefresh ? 'active' : 'inactive']">
              {{ autoRefresh ? '✅ Activo' : '⏸️ Pausado' }}
            </span>
          </div>
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
      redes: [],
      telemetria: []
    }
  },
  mounted() {
    this.cargarDatos()
  },
  methods: {
    cargarDatos() {
      try {
        fetch('http://localhost:8084/configuracion-redes')
          .then(res => res.json())
          .then(data => {
            this.redes = Array.isArray(data) ? data : []
          })
          .catch(err => console.log('Error:', err))
        
        fetch('http://localhost:8084/registros-redes')
          .then(res => res.json())
          .then(data => {
            this.telemetria = Array.isArray(data) ? data : []
          })
          .catch(err => console.log('Error:', err))
      } catch (error) {
        console.log('Error:', error)
      }
    }
  }
}
</script>

<style scoped>
.config-section {
  margin-top: 20px;
}

.config-item {
  margin-bottom: 30px;
}

.config-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.config-item h3 {
  font-size: 1.2em;
  color: #333;
  margin-bottom: 8px;
}

.config-description {
  color: #666;
  font-size: 0.95em;
}

.divider {
  height: 1px;
  background: #e2e8f0;
  margin: 30px 0;
}

/* Switch Toggle */
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

/* Range Slider */
.slider-container {
  display: flex;
  align-items: center;
  gap: 20px;
  margin: 20px 0;
}

.range-slider {
  flex: 1;
  height: 8px;
  border-radius: 5px;
  background: #e2e8f0;
  outline: none;
  -webkit-appearance: none;
}

.range-slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background: #667eea;
  cursor: pointer;
  transition: all 0.3s ease;
}

.range-slider::-webkit-slider-thumb:hover {
  transform: scale(1.2);
  box-shadow: 0 0 0 8px rgba(102, 126, 234, 0.2);
}

.range-slider:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.range-slider:disabled::-webkit-slider-thumb {
  cursor: not-allowed;
}

.value-display {
  font-size: 1.3em;
  font-weight: bold;
  color: #667eea;
  min-width: 60px;
  text-align: center;
}

.slider-labels {
  display: flex;
  justify-content: space-between;
  color: #666;
  font-size: 0.85em;
  margin-top: 5px;
}

/* Info Grid */
.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 15px;
  margin-top: 15px;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  background: #f8fafc;
  border-radius: 8px;
  border-left: 4px solid #667eea;
}

.info-label {
  font-weight: 600;
  color: #475569;
}

.info-value {
  font-weight: 600;
  color: #1e293b;
}

.badge-info {
  background: #667eea;
  color: white;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 0.9em;
}

.badge-status {
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 0.9em;
}

.badge-status.active {
  background: #10b981;
  color: white;
}

.badge-status.inactive {
  background: #94a3b8;
  color: white;
}
</style>