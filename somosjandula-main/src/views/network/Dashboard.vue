<template>
  <div :class="{ 'fullscreen': estadoExpandido }">
    <!-- Estado Actual -->
    <div :class="['card', { 'card-fullscreen': estadoExpandido }]">
      <div class="card-header-flex">
        <h2 class="card-title"> Estado Actual de Redes</h2>
        <button @click="estadoExpandido = !estadoExpandido" class="btn-expandir">
          {{ estadoExpandido ? '🔽 Minimizar' : '🔼 Expandir' }}
        </button>
      </div>
      
      <div v-if="cargando" class="loading">
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
      <h2 class="card-title">Histórico Completo</h2>
      
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
import { SESSION_JWT_TOKEN } from '@/utils/constants'

export default {
  name: 'Dashboard',
  data() {
    return {
      telemetria: [],  // Array con todos los registros de telemetría
      cargando: true,  // Flag para mostrar estado de carga
      estadoExpandido: false  // Flag para expandir/contraer vista
    }
  },
  computed: {
    // Calcula la última telemetría por cada SSID
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
    // Ordena la telemetría por fecha descendente
    telemetriaOrdenada() {
      return [...this.telemetria].sort((a, b) => 
        new Date(b.fechaReporte) - new Date(a.fechaReporte)
      )
    }
  },
  methods: {
    // Carga los registros de telemetría del servidor
    async cargarTelemetria() {
      this.cargando = true
      try {
        const token = sessionStorage.getItem(SESSION_JWT_TOKEN)
        const response = await fetch('http://localhost:8084/registros-redes', {
          headers: token ? { Authorization: `Bearer ${token}` } : {}
        })

        if (!response.ok) {
          throw new Error(`Error HTTP ${response.status}`)
        }

        const data = await response.json()
        this.telemetria = Array.isArray(data) ? data : []
      } catch (error) {
        console.log('Error cargando telemetría:', error)
      } finally {
        this.cargando = false
      }
    },
    // Devuelve la clase CSS según el estado
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
  },
  mounted() {
    this.cargarTelemetria()
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
  max-height: auto;
  display: flex;
  flex-direction: column;
}

.card-fullscreen .status-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 15px;
  overflow: visible;
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

.card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.card-title {
  font-size: 1.3em;
  color: #333;
  margin-bottom: 15px;
  border-bottom: 2px solid #667eea;
  padding-bottom: 10px;
}

.loading {
  text-align: center;
  padding: 40px;
  color: #666;
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #999;
}

.icon-empty {
  width: 48px;
  height: 48px;
  margin-bottom: 15px;
  color: #ccc;
}

.status-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 15px;
}

.status-card {
  padding: 15px;
  border-radius: 8px;
  background: #f8f9fa;
  border-left: 4px solid #999;
  transition: all 0.3s ease;
}

.status-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.status-card.conectado {
  border-left-color: #28a745;
  background: #f0f8f5;
}

.status-card.fallo {
  border-left-color: #dc3545;
  background: #fdf8f8;
}

.status-card.sin-senal {
  border-left-color: #ffc107;
  background: #fffbf0;
}

.status-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 10px;
}

.status-icon {
  font-size: 1.8em;
}

.status-info h3 {
  margin: 0;
  font-size: 1.1em;
  color: #333;
}

.status-info p {
  margin: 5px 0 0 0;
  font-size: 0.9em;
  color: #666;
}

.status-timestamp {
  font-size: 0.85em;
  color: #999;
  margin-top: 10px;
}

.status-timestamp p {
  margin: 2px 0;
}

.table-container {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.95em;
}

thead {
  background: #f0f0f0;
  border-bottom: 2px solid #667eea;
}

th {
  padding: 12px;
  text-align: left;
  font-weight: 600;
  color: #333;
}

td {
  padding: 12px;
  border-bottom: 1px solid #e0e0e0;
}

tbody tr:hover {
  background: #f9f9f9;
}

.timestamp {
  color: #666;
  font-size: 0.9em;
}

.badge {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.85em;
  font-weight: 500;
}

.badge.conectado {
  background: #d4edda;
  color: #155724;
}

.badge.fallo {
  background: #f8d7da;
  color: #721c24;
}

.badge.sin-senal {
  background: #fff3cd;
  color: #856404;
}

/* Tabla resumen en esquina superior derecha */
.networks-summary-table {
  position: fixed;
  top: 80px;
  right: 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 100;
  max-width: 220px;
  font-size: 0.85em;
}

.summary-title {
  margin: 0 0 8px 0;
  font-size: 0.9em;
  font-weight: 600;
  color: #333;
  padding: 0;
}

.summary-table {
  width: 100%;
  border-collapse: collapse;
  margin: 0;
}

.summary-table tbody tr {
  border-bottom: 1px solid #f0f0f0;
}

.summary-table tbody tr:last-child {
  border-bottom: none;
}

.network-name {
  padding: 6px 8px;
  text-align: left;
  font-weight: 500;
  color: #333;
  max-width: 160px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.status-dot {
  padding: 6px 8px;
  text-align: center;
  width: 20px;
}

.status-dot::before {
  content: '';
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
}

.status-dot.conectado::before {
  background-color: #28a745;
}

.status-dot.fallo::before {
  background-color: #dc3545;
}

.status-dot.sin-senal::before {
  background-color: #ffc107;
}

.status-dot.desconocido::before {
  background-color: #6c757d;
}
</style>