<a name="top"></a>
<div align="center">
  <h1>🎨 API REST Taller Cultural</h1>
  <p><b>Motor de gestión modular para talleres culturales, organizadores y participantes</b></p>
</div>

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=openapi-initiative&logoColor=black)](https://swagger.io/)
[![Postman](https://img.shields.io/badge/Postman-Tests-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)

---

## 📋 Índice
- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Arquitectura y Validaciones](#-arquitectura-y-validaciones)
- [Garantía de Calidad y Pruebas](#-garantía-de-calidad-y-pruebas)
- [Cómo Compilar y Ejecutar](#-cómo-compilar-y-ejecutar)
- [Datos Preconfigurados (DataSeed)](#-datos-preconfigurados-dataseed)
- [Licencia](#-licencia)
- [Contacto](#-contacto)
---

## 🚀 Acerca del Proyecto

**API REST Taller Cultural** es una solución backend robusta para la gestión del flujo operativo de eventos y talleres. Diseñado bajo exigentes patrones de diseño de software, el ecosistema desacopla la lógica de negocio mediante una arquitectura por capas, facilitando la mantenibilidad, escalabilidad y la prevención de errores de persistencia.

### Beneficios Arquitectónicos Principales:
- **Modelo de Datos Optimizado**: Implementación de relaciones bidireccionales complejas ($M:N$ y $1:N$) gestionadas mediante colecciones `Set` y métodos helper para garantizar un rendimiento óptimo en JPA y evitar bucles de recursividad (StackOverflow).
- **Patrón DTO (Data Transfer Object)**: Desacoplamiento total entre los controladores HTTP y el modelo de dominio. Filtrado de datos preciso para evitar la sobreexposición de información y resolver ciclos de serialización JSON.
- **Documentación Interactiva**: Integración nativa con OpenAPI 3 y Swagger UI para la exploración y prueba de los endpoints en tiempo real.

---

## 🔒 Arquitectura y Validaciones

El sistema cuenta con un control estricto de las reglas de negocio implementado directamente en la capa de servicios, asegurando la integridad de los datos antes de cualquier transacción con la base de datos.

- **Validación de Fechas**: Reglas estrictas que impiden la creación o edición de talleres donde la fecha de fin sea anterior a la de inicio.
- **Control de Duplicados e Inscripciones**: Verificación *case-insensitive* para evitar talleres con nombres repetidos y bloqueos de seguridad que impiden que un mismo participante se inscriba dos veces en el mismo taller.
- **Manejo Centralizado de Excepciones**: Respuestas HTTP semánticas (`404 Not Found`, `400 Bad Request`, `201 Created`, `204 No Content`) gestionadas de forma global.

---

## 🎓 Garantía de Calidad y Pruebas

Siguiendo una estricta planificación de pruebas de integración, la aplicación ha superado con éxito la batería de peticiones diseñada, certificando su resiliencia operativa en el manejo de bases de datos relacionales.

El repositorio incluye una **Colección de Postman** (`TallerCultural_PostmanCollection.json`) con tests automáticos configurados. Todos los casos de prueba planificados han resultado en estado **PASSED**.

### Resumen de Cobertura Funcional

| Área Funcional | Descripción de la Verificación | Estado |
| :--- | :--- | :---: |
| **Operaciones CRUD** | Creación, lectura, actualización y borrado atómico de Talleres, Organizadores y Participantes. | **PASSED** |
| **Gestión de Inscripciones** | Flujos de adición de participantes a talleres y borrado de inscripciones manteniendo la integridad referencial. | **PASSED** |
| **Validaciones de Negocio** | Denegación de creación con fechas inconsistentes o IDs inexistentes (Generación correcta de errores 400 y 404). | **PASSED** |

---

## 📝 Cómo Compilar y Ejecutar

### Prerrequisitos
- Java JDK 17 o superior.
- Maven 3.x (o el wrapper `./mvnw` incluido).

Siga estos pasos para clonar, construir e iniciar la suite localmente:

```bash
# 1. Clonar el repositorio
git clone <URL_DE_TU_REPOSITORIO>

# 2. Abrir el proyecto en tu IDE de confianza (IntelliJ, Eclipse, VSCode).

# 3. Esperar a que Maven descargue todas las dependencias necesarias.

# 4. Ejecutar la aplicación Spring Boot
./mvnw spring-boot:run

# 5. Abrir el navegador web para ver la documentación interactiva Swagger:
http://localhost:8080/swagger-ui.html
```

---

## 👥 Datos Preconfigurados (DataSeed)

El proyecto incluye un mecanismo de persistencia inicial automatizado (a través del componente `DataSeed` y `@Transactional`) que puebla la base de datos en memoria (H2) al arrancar el servidor. Esto permite evaluar los flujos de la API, las relaciones M:N y las colecciones de Postman sin necesidad de crear registros manualmente.

Al iniciar la aplicación, tendrás disponibles los siguientes datos de prueba:

| Entidad | Registros Cargados | Detalles Relevantes |
| :--- | :--- | :--- |
| **Organizadores** | `ID: 1 (Luismi)`, `ID: 2 (Miguel)` | Asignados automáticamente como responsables de los talleres de prueba. |
| **Participantes** | `ID: 1 (Juan Pérez)`, `ID: 2 (María García)`, `ID: 3 (Carlos Gómez)` | Usuarios listos para ser inscritos o dados de baja de los talleres. |
| **Talleres** | `Taller Python`, `Taller Java` | Creados con fechas válidas e incluyen inscripciones pre-cargadas para probar los endpoints de lectura al instante. |

---

## 📃 Licencia

Este proyecto educativo está distribuido bajo licencia de Código Abierto (Open Source). Libre para su uso, modificación y distribución académica.

---

## 📝 Contacto

Si deseas conocer más detalles sobre el diseño técnico del proyecto o necesitas soporte para la instalación del entorno, no dudes en contactar:

- **Desarrollador:** Germán Díaz Castro
- **Contacto:** diaz.cager25@triana.salesianos.edu  
- **Repositorio Principal:** https://github.com/GermaanDiaaz/ProyectoTallerCultural.git