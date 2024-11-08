
# Documentación de la API

## Base URL
La base URL para esta API es:
```
http://<host>:<puerto>/api
```

## Endpoints

### 1. **Usuarios**

#### Obtener todos los usuarios
- **URL**: `/usuarios/all`
- **Método**: `GET`
- **Descripción**: Devuelve una lista de todos los usuarios.
- **Ejemplo de Respuesta**:
  ```json
  [
    {
      "user": "12345",
      "nombre": "Juan",
      "apellidos": "Pérez",
      "correo": "juan@example.com",
      "telefono": "123456789"
    },
    ...
  ]
  ```

#### Crear un nuevo usuario
- **URL**: `/usuarios/create`
- **Método**: `POST`
- **Descripción**: Crea un nuevo usuario.
- **Body**:
  ```json
  {
    "user": "12345",
    "nombre": "Juan",
    "apellidos": "Pérez",
    "password": "mypassword",
    "correo": "juan@example.com"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "user": "12345",
    "nombre": "Juan",
    "apellidos": "Pérez",
    "correo": "juan@example.com"
  }
  ```

#### Obtener un usuario por ID
- **URL**: `/usuarios/find/{id}`
- **Método**: `GET`
- **Descripción**: Obtiene un usuario específico por su `id`.
- **Ejemplo de Respuesta**:
  ```json
  {
    "user": "12345",
    "nombre": "Juan",
    "apellidos": "Pérez",
    "correo": "juan@example.com"
  }
  ```

#### Actualizar un usuario
- **URL**: `/usuarios/update/{id}`
- **Método**: `PUT`
- **Descripción**: Actualiza la información de un usuario.
- **Body**:
  ```json
  {
    "nombre": "Juan",
    "apellidos": "González",
    "correo": "juan.g@example.com"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "user": "12345",
    "nombre": "Juan",
    "apellidos": "González",
    "correo": "juan.g@example.com"
  }
  ```

#### Eliminar un usuario
- **URL**: `/usuarios/delete/{id}`
- **Método**: `DELETE`
- **Descripción**: Elimina un usuario por su `id`.
- **Ejemplo de Respuesta**:
  ```json
  {
    "message": "Usuario eliminado exitosamente"
  }
  ```

---

### 2. **Tests**

#### Obtener todos los tests
- **URL**: `/tests/all`
- **Método**: `GET`
- **Descripción**: Devuelve todos los tests registrados en el sistema.
- **Ejemplo de Respuesta**:
  ```json
  [
    {
      "id": "1",
      "nombre": "Examen Matemáticas",
      "fecha": "2024-01-20T08:30:00",
      "calificacion": 95.5
    },
    ...
  ]
  ```

#### Crear un nuevo test
- **URL**: `/tests/create`
- **Método**: `POST`
- **Descripción**: Crea un nuevo test.
- **Body**:
  ```json
  {
    "nombre": "Examen Física",
    "fecha": "2024-01-22T10:00:00",
    "usuario": "12345"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "id": "2",
    "nombre": "Examen Física",
    "fecha": "2024-01-22T10:00:00",
    "usuario": {
      "user": "12345",
      "nombre": "Juan"
    }
  }
  ```

---

### 3. **Resultados**

#### Obtener todos los resultados
- **URL**: `/resultados/all`
- **Método**: `GET`
- **Descripción**: Devuelve todos los resultados de las pruebas.
- **Ejemplo de Respuesta**:
  ```json
  [
    {
      "id": "1",
      "resultado": "Aprobado",
      "fecha": "2024-01-20T10:00:00",
      "observaciones": "Buen rendimiento"
    },
    ...
  ]
  ```

#### Crear un nuevo resultado
- **URL**: `/resultados/create`
- **Método**: `POST`
- **Descripción**: Crea un nuevo resultado para una prueba.
- **Body**:
  ```json
  {
    "resultado": "Aprobado",
    "fecha": "2024-01-22T11:00:00",
    "test": "1"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "id": "3",
    "resultado": "Aprobado",
    "fecha": "2024-01-22T11:00:00",
    "test": {
      "id": "1",
      "nombre": "Examen Matemáticas"
    }
  }
  ```

---

### 4. **Pagos**

#### Obtener todos los pagos
- **URL**: `/pagos/all`
- **Método**: `GET`
- **Descripción**: Devuelve todos los pagos realizados.
- **Ejemplo de Respuesta**:
  ```json
  [
    {
      "id": "1",
      "nombre": "Pago Examen Matemáticas",
      "monto": 50.0
    },
    ...
  ]
  ```

#### Crear un nuevo pago
- **URL**: `/pagos/create`
- **Método**: `POST`
- **Descripción**: Crea un nuevo pago.
- **Body**:
  ```json
  {
    "nombre": "Pago Examen Física",
    "monto": 70.0,
    "test": "2",
    "tipoPago": "1"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "id": "2",
    "nombre": "Pago Examen Física",
    "monto": 70.0,
    "test": {
      "id": "2",
      "nombre": "Examen Física"
    },
    "tipoPago": {
      "id": "1",
      "tipo": "Crédito"
    }
  }
  ```

---

### 5. **TipoPago**

#### Obtener todos los tipos de pago
- **URL**: `/tipopagos/all`
- **Método**: `GET`
- **Descripción**: Devuelve todos los tipos de pago disponibles.
- **Ejemplo de Respuesta**:
  ```json
  [
    {
      "id": "1",
      "tipo": "Crédito",
      "nro": "1234"
    },
    ...
  ]
  ```

#### Crear un nuevo tipo de pago
- **URL**: `/tipopagos/create`
- **Método**: `POST`
- **Descripción**: Crea un nuevo tipo de pago.
- **Body**:
  ```json
  {
    "tipo": "Débito",
    "nro": "5678"
  }
  ```
- **Ejemplo de Respuesta**:
  ```json
  {
    "id": "2",
    "tipo": "Débito",
    "nro": "5678"
  }
  ```

---

## Respuestas de Error
La API devuelve códigos de error en situaciones problemáticas:
- **404 Not Found**: El recurso solicitado no existe.
- **400 Bad Request**: La solicitud tiene un formato incorrecto o faltan datos necesarios.
- **500 Internal Server Error**: Error del servidor.

### Ejemplo de respuesta de error
```json
{
  "timestamp": "2024-01-22T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Recurso no encontrado",
  "path": "/usuarios/find/999"
}
```

---

Esta documentación cubre los principales endpoints de la API, lo que te permite realizar operaciones CRUD sobre las entidades principales de la aplicación y manejar los recursos del sistema.
