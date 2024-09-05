# technical-test-2024-2

# Aplicación Spring Boot

## Descripción

Esta es una aplicación Spring Boot para gestionar actividades. Permite crear, consultar y actualizar registros de actividades, con funcionalidades para filtrar por diferentes criterios y marcar actividades como aprobadas o rechazadas.

## Funcionalidades

1. **Crear una Actividad**:
    - Identificador único para la actividad.
    - Nombre y número de documento del auxiliar.
    - Fecha y hora de inicio y fin de la actividad.
    - Descripción de la actividad.
    - Estado de la actividad (aprobado/rechazado/en espera).

2. **Obtener Actividades**:
    - Todas las actividades.
    - Actividades por fecha específica.
    - Actividades por identificación.
    - Actividades por cédula del auxiliar.

3. **Actualizar Estado de Actividad**:
    - Marcar una actividad como aprobada o rechazada.

## Documentación de la API

La documentación de la API está disponible a través de Swagger UI. Puedes explorar todos los endpoints y probar las funcionalidades directamente desde la interfaz web.

**Enlace a la Documentación de la API:**

[Swagger UI - Documentación de la API](http://localhost:8080/swagger-ui/index.html)

## Configuración

Asegúrate de que la aplicación esté corriendo en el puerto predeterminado (`8080`) para acceder a la documentación en el enlace proporcionado.

## Ejecución

Para ejecutar la aplicación:

1. Clona el repositorio.
2. Usa el comando: mvn install
3. usa el comando: mvnw spring-boot:run
