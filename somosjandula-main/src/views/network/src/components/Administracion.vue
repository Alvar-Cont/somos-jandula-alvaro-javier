<template>
  <div>
    <!-- Agregar Nueva Red -->
    <div class="card">
      <h2 class="card-title">➕ Agregar Nueva Red</h2>
      
      <div class="form">
        <div class="form-group">
          <label>SSID (Nombre de la red) *</label>
          <input 
            v-model="nuevaRed.SSID" 
            type="text" 
            placeholder="Ej: Andared_Corporativo"
            @keyup.enter="agregarRed"
          />
        </div>

        <div class="form-group">
          <label>Contraseña *</label>
          <input 
            v-model="nuevaRed.contrasena" 
            type="password" 
            placeholder="Contraseña de la red"
            @keyup.enter="agregarRed"
          />
        </div>

        <div class="form-group">
          <label>Configuración de Seguridad</label>
          <select v-model="nuevaRed.configSeguridad">
            <option value="WPA2">WPA2</option>
            <option value="WPA3">WPA3</option>
            <option value="WPA">WPA</option>
            <option value="WEP">WEP</option>
            <option value="Abierta">Abierta</option>
          </select>
        </div>

        <button @click="agregarRed" :disabled="guardando" class="btn btn-primary btn-block">
          <span v-if="guardando">⏳ Guardando...</span>
          <span v-else>➕ Agregar Red</span>
        </button>
      </div>
    </div>

    <!-- Redes Configuradas -->
    <div class="card">
      <h2 class="card-title">🌐 Redes Configuradas</h2>
      
      <div v-if="redes.length === 0" class="empty-state">
        <p>No hay redes configuradas</p>
        <p class="text-small">Agrega una red usando el formulario de arriba</p>
      </div>

      <div v-else class="networks-grid">
        <div v-for="(red, index) in redes" :key="index" class="network-card">
          <div class="network-header">
            <div class="network-info">
              <h3>{{ red.ssid }}</h3>
              <p>🔒 Contraseña: {{ ocultarContrasena(red.contrasena) }}</p>
            </div>
            <span class="network-icon">📶</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Administracion',
  props: {
    redes: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      nuevaRed: {
        SSID: '',
        contrasena: '',
        configSeguridad: 'WPA2'
      },
      guardando: false,
      API_BASE: 'http://localhost:8080'
    }
  },
  methods: {
    async agregarRed() {
      // Validación
      if (!this.nuevaRed.SSID || !this.nuevaRed.contrasena) {
        alert('⚠️ Por favor completa todos los campos obligatorios')
        return
      }

      if (this.nuevaRed.SSID.trim() === '' || this.nuevaRed.contrasena.trim() === '') {
        alert('⚠️ Los campos no pueden estar vacíos')
        return
      }

      try {
        this.guardando = true
        
        const response = await axios.post(
          `${this.API_BASE}/configuracion-redes`,
          this.nuevaRed
        )

        if (response.status === 200) {
          alert('✅ Red agregada exitosamente')
          
          // Limpiar formulario
          this.nuevaRed = {
            SSID: '',
            contrasena: '',
            configSeguridad: 'WPA2'
          }
          
          // Emitir evento para actualizar la lista
          this.$emit('actualizar')
        }
      } catch (error) {
        console.error('Error al agregar red:', error)
        
        if (error.response) {
          alert(`❌ Error: ${error.response.data}`)
        } else {
          alert('❌ Error de conexión con el servidor')
        }
      } finally {
        this.guardando = false
      }
    },
    ocultarContrasena(contrasena) {
      if (!contrasena) return '••••••••'
      return '•'.repeat(contrasena.length)
    }
  }
}
</script>

<style scoped>
.btn-block {
  width: 100%;
  margin-top: 10px;
}

.text-small {
  font-size: 0.9em;
  color: #666;
  margin-top: 10px;
}
</style>