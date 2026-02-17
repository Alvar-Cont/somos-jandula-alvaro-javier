<template>
  <div :class="{ 'fullscreen': estadoExpandido }">
    <!-- Estado Actual -->
    <div :class="['card', { 'card-fullscreen': estadoExpandido }]">
      <div class="card-header-flex">
        <h2 class="card-title">📡 Estado Actual de Redes</h2>
        <button @click="estadoExpandido = !estadoExpandido" class="btn-expandir">
          {{ estadoExpandido ? '🔽 Minimizar' : '🔼 Expandir' }}
        </button>
      </div>
      
      <div v-if="loading" class="loading">
        <p>Cargando datos...</p>
      </div>
      
      <div v-else-if="ultimasTelemetrias.length === 0" class="empty-state">
        <svg class="icon-empty" viewBox="0 0 24 24" fill="none" stroke="currentColor">
          <path d="M1 1l6 6m0 0l6-6M7 7l6 6M7 7l-6 6"></path>
        </svg>
        <p>No hay datos de telemetría disponibles</p>
      </div>

      <div v-else class="status-grid">
        <div 
          v-for="item in ultimasTelemetrias" 
          :key="item.ssid"
          :class="['status-card', obtenerClaseEstado(item.estado)]">
          <div class="status-header">
            <span class="status-icon">{{ obtenerIcono(item.estado) }}</span>
            <div class="status-info">
              <h3>{{ item.ssid }}</h3>
              <p><strong>{{ obtenerTextoEstado(item.estado) }}</strong></p>
            </div>
          </div>
          <div class="status-timestamp">
            <p>🕐 Última actualización:</p>
            <p>{{ formatearFecha(item.fechaReporte) }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Histórico Completo -->
    <div v-if="!estadoExpandido" class="card">
      <h2 class="card-title">📋 Histórico Completo</h2>
      
      <div v-if="telemetria.length === 0" class="empty-state">
        <p>No hay registros en el histórico</p>
      </div>

      <div v-else class="table-container">
        <table>
          <thead>
            <tr>
              <th>SSID</th>
              <th>Estado</th>
              <th>Fecha/Hora</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in telemetriaOrdenada" :key="index">
              <td><strong>{{ item.ssid }}</strong></td>
              <td>
                <span :class="['badge', obtenerClaseEstado(item.estado)]">
                  {{ obtenerTextoEstado(item.estado) }}
                </span>
              </td>
              <td class="timestamp">{{ formatearFecha(item.fechaReporte) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Dashboard',
  props: {
    telemetria: {
      type: Array,
      required: true
    },
    loading: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      estadoExpandido: false
    }
  },
  computed: {
    ultimasTelemetrias() {
      const ultimasPorSSID = {}
      this.telemetria.forEach(item => {
        if (!ultimasPorSSID[item.ssid] || 
            new Date(item.fechaReporte) > new Date(ultimasPorSSID[item.ssid].fechaReporte)) {
          ultimasPorSSID[item.ssid] = item
        }
      })
      return Object.values(ultimasPorSSID)
    },
    telemetriaOrdenada() {
      return [...this.telemetria].sort((a, b) => 
        new Date(b.fechaReporte) - new Date(a.fechaReporte)
      )
    }
  },
  methods: {
    obtenerClaseEstado(estado) {
      const clases = {
        'Conectado': 'conectado',
        'FalloDeAuth': 'fallo',
        'SinSenal': 'sin-senal'
      }
      return clases[estado] || 'desconocido'
    },
    obtenerIcono(estado) {
      const iconos = {
        'Conectado': '📶',
        'FalloDeAuth': '❌',
        'SinSenal': '⚠️'
      }
      return iconos[estado] || '❓'
    },
    obtenerTextoEstado(estado) {
      const textos = {
        'Conectado': 'Conectado',
        'FalloDeAuth': 'Fallo de Autenticación',
        'SinSenal': 'Sin Señal'
      }
      return textos[estado] || 'Desconocido'
    },
    formatearFecha(timestamp) {
      const fecha = new Date(timestamp)
      return fecha.toLocaleString('es-ES', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      })
    }
  }
}
</script>

<style scoped>
.fullscreen {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: #f5f5f5;
  z-index: 9999;
  overflow: auto;
  padding: 1rem;
}

.card-fullscreen {
  height: calc(100vh - 2rem);
  display: flex;
  flex-direction: column;
}

.card-fullscreen .status-grid {
  flex: 1;
  overflow: auto;
}

.card-header-flex {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.btn-expandir {
  padding: 0.5rem 1rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: background-color 0.3s;
  white-space: nowrap;
}

.btn-expandir:hover {
  background-color: #0056b3;
}
</style>