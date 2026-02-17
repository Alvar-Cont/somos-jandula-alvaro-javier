<template>
  <div id="app">
    <!-- Header -->
    <div class="header">
      <div class="header-content">
        <div class="header-title">
          <svg class="icon-large" viewBox="0 0 24 24" fill="none" stroke="currentColor">
            <path d="M22 12h-4l-3 9L9 3l-3 9H2"></path>
          </svg>
          <div>
            <h1>Monitor de Redes WiFi</h1>
            <p>Sistema de escaneo y monitorización ESP-32</p>
          </div>
        </div>
        <button @click="actualizarDatos" :disabled="loading" class="btn btn-primary">
          <span v-if="loading">🔄 Actualizando...</span>
          <span v-else>🔄 Actualizar</span>
        </button>
      </div>
    </div>

    <!-- Tabs Navigation -->
    <div class="tabs">
      <div class="tabs-header">
        <button 
          @click="activeTab = 'dashboard'" 
          :class="['tab-button', { active: activeTab === 'dashboard' }]">
          📊 Dashboard
        </button>
        <button 
          @click="activeTab = 'admin'" 
          :class="['tab-button', { active: activeTab === 'admin' }]">
          ⚙️ Administración
        </button>
        <button 
          @click="activeTab = 'settings'" 
          :class="['tab-button', { active: activeTab === 'settings' }]">
          🔧 Configuración
        </button>
      </div>
    </div>

    <!-- Tab Content -->
    <Dashboard v-if="activeTab === 'dashboard'" :telemetria="telemetria" :loading="loading" />
    <Administracion v-if="activeTab === 'admin'" :redes="redes" @actualizar="cargarRedes" />
    <Configuracion 
      v-if="activeTab === 'settings'" 
      :autoRefresh="autoRefresh"
      :refreshInterval="refreshInterval"
      :redes="redes"
      :telemetria="telemetria"
      @update:autoRefresh="autoRefresh = $event"
      @update:refreshInterval="refreshInterval = $event"
    />
  </div>
</template>

<script>
import axios from 'axios'
import Dashboard from './components/Dashboard.vue'
import Administracion from './components/Administracion.vue'
import Configuracion from './components/Configuracion.vue'

export default {
  name: 'App',
  components: {
    Dashboard,
    Administracion,
    Configuracion
  },
  data() {
    return {
      activeTab: 'dashboard',
      telemetria: [],
      redes: [],
      loading: false,
      autoRefresh: true,
      refreshInterval: 30,
      intervalId: null,
      API_BASE: 'http://localhost:8080'
    }
  },
  methods: {
    async cargarTelemetria() {
      try {
        this.loading = true
        const response = await axios.get(`${this.API_BASE}/registros-redes`)
        this.telemetria = response.data
      } catch (error) {
        console.error('Error al cargar telemetría:', error)
        alert('Error al cargar los datos de telemetría')
      } finally {
        this.loading = false
      }
    },
    async cargarRedes() {
      try {
        const response = await axios.get(`${this.API_BASE}/configuracion-redes`)
        this.redes = response.data
      } catch (error) {
        console.error('Error al cargar redes:', error)
        alert('Error al cargar las redes configuradas')
      }
    },
    actualizarDatos() {
      this.cargarTelemetria()
      this.cargarRedes()
    },
    iniciarAutoRefresh() {
      if (this.intervalId) {
        clearInterval(this.intervalId)
      }
      if (this.autoRefresh) {
        this.intervalId = setInterval(() => {
          if (this.activeTab === 'dashboard') {
            this.cargarTelemetria()
          }
        }, this.refreshInterval * 1000)
      }
    }
  },
  watch: {
    autoRefresh() {
      this.iniciarAutoRefresh()
    },
    refreshInterval() {
      this.iniciarAutoRefresh()
    }
  },
  mounted() {
    this.actualizarDatos()
    this.iniciarAutoRefresh()
  },
  beforeUnmount() {
    if (this.intervalId) {
      clearInterval(this.intervalId)
    }
  }
}
</script>