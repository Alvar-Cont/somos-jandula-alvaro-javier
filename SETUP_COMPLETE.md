# ✅ Setup Reaktor - Completado

## Resumen de la configuración realizada

### 1. Dependencias Node.js ✅
- **Proyecto**: `somosjandula-main`
- **Estado**: ✅ Instaladas correctamente
- **Paquetes**: 740 paquetes instalados
- **Comando**: `npm install`

### 2. Claves de Seguridad ✅
Ubicación: `C:\claves\`
- ✅ `private_key.pem` - Clave privada RSA (2048 bits)
- ✅ `public_key.pem` - Clave pública derivada
- ✅ `firebaseGoogleCredentials.json` - Credenciales Firebase

### 3. Configuración Firebase ✅
- **Proyecto**: `reactor-af093`
- **API Key**: Configurada
- **Auth Domain**: `reactor-af093.firebaseapp.com`
- **Archivo de configuración**: `.env.development` ya existe con todos los datos
- **Ubicación del archivo**: `somosjandula-main/.env.development`

### 4. OpenSSL ✅
- **Estado**: Instalado y disponible
- **Versión**: OpenSSL 3.0.16

---

## Próximos pasos necesarios

### 📋 Para los servidores Java/Maven:
**Nota**: Se requiere Maven instalado para compilar los proyectos Java

Orden de compilación recomendado:
1. `Reaktor_Dependencies-main` - Contiene las dependencias base
2. `Reaktor_Base-main` - Funcionalidades comunes
3. `Reaktor_BaseServer-main` - Utilidades de servidor
4. `Reaktor_BaseClient-main` - Utilidades de cliente
5. `Reaktor_FirebaseServer-main` - Servidor de autenticación
6. Otros servidores según sea necesario

Comando para compilar cada proyecto:
```bash
cd [directorio-proyecto]
mvn clean install
```

### 🌐 Para lanzar la web somosjandula:
```bash
cd somosjandula-main
npm run dev
```
Acceso: `http://localhost:5173`

### 💾 Base de datos:
Se requiere MySQL Community Edition con usuario `root` y contraseña `toor`

### 👤 Usuarios:
Es necesario crear al menos un usuario en la BBDD `reaktor_firebaseserver` con tu correo de Google:
```sql
use reaktor_firebaseserver;
INSERT INTO usuario(email, nombre, apellidos, roles) VALUES ('tu_email@gmail.com', 'Tu Nombre', 'Tus Apellidos', 'PROFESOR,DIRECCION,ADMINISTRADOR');
```

---

## Configuración de los application.yaml

Todos los proyectos Java deben tener configurados en su `application.yaml`:

```yaml
reaktor:
  publicKeyFile: C:\claves\public_key.pem
  privateKeyFile: C:\claves\private_key.pem              # Solo en FirebaseServer
  googleCredentialsFile: C:\claves\firebaseGoogleCredentials.json  # Solo en FirebaseServer
  urlCors: http://localhost:5173, http://192.168.1.209:5173
```

---

## Estado General
✅ **Frontend (Node.js)**: Listo para desarrollo
✅ **Claves de Seguridad**: Generadas y disponibles
✅ **Configuración Firebase**: Completada
⏳ **Backend (Java/Maven)**: Requiere Maven instalado y compilación

