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
      default: () => []
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
      API_BASE: 'http://localhost:8084',
      redes: []
    }
  },
  methods: {
    cargarRedes() {
      try {
        fetch(`${this.API_BASE}/configRed`)
          .then(res => res.json())
          .then(data => {
            this.redes = Array.isArray(data) ? data : []
          })
          .catch(err => console.log('Error:', err))
      } catch (error) {
        console.log('Error:', error)
      }
    },
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
        
        const response = await fetch(
          `${this.API_BASE}/gestionCredenciales`,
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              SSID: this.nuevaRed.SSID,
              contrasena: this.nuevaRed.contrasena
            })
          }
        )

        if (response.ok) {
          alert('✅ Red agregada exitosamente')
          
          // Limpiar formulario
          this.nuevaRed = {
            SSID: '',
            contrasena: '',
            configSeguridad: 'WPA2'
          }
          
          // Recargar lista
          this.cargarRedes()
        } else {
          alert('❌ Error al agregar la red')
        }
      } catch (error) {
        console.error('Error al agregar red:', error)
        alert('❌ Error de conexión con el servidor')
      } finally {
        this.guardando = false
      }
    },
    ocultarContrasena(contrasena) {
      if (!contrasena) return '••••••••'
      return '•'.repeat(contrasena.length)
    }
  },
  mounted() {
    this.cargarRedes()
  }
}
</script>

<style scoped>
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

.form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 8px;
  color: #333;
  font-weight: 600;
  font-size: 0.95em;
}

.form-group input,
.form-group select {
  padding: 10px 12px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 1em;
  transition: border-color 0.3s;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 0.95em;
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-primary:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.btn-primary:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-block {
  width: 100%;
  margin-top: 10px;
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #999;
}

.text-small {
  font-size: 0.9em;
  color: #666;
  margin-top: 10px;
}

.networks-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
}

.network-card {
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  border-left: 4px solid #667eea;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.network-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  width: 100%;
}

.network-info h3 {
  margin: 0;
  color: #333;
  font-size: 1.1em;
}

.network-info p {
  margin: 5px 0 0 0;
  color: #666;
  font-size: 0.9em;
}

.network-icon {
  font-size: 1.5em;
}
</style>