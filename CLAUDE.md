# ILCBIntranetKmp - Guía de Estilo y Arquitectura

## 🏗 Arquitectura: Clean Architecture + MVVM
Este proyecto sigue una separación estricta de responsabilidades para garantizar mantenibilidad y escalabilidad.
- **Módulo Common**: Toda la lógica reside en `composeApp/src/commonMain`.
- **Capa de Data**: Implementaciones de Repositorios, DTOs (Data Transfer Objects) y servicios de **Ktor**.
- **Capa de Domain**: Modelos de negocio (Entities) e interfaces de Repositorios (Interfaces puras de Kotlin).
- **Capa de Presentación**: ViewModels que heredan de `androidx.lifecycle.ViewModel`.
- **Capa de UI**: Pantallas y componentes de **Compose Multiplatform**.

## 🛠 Principios de Ingeniería (Obligatorios)
- **SOLID**:
    - **Single Responsibility**: Un componente, una sola razón para cambiar.
    - **Dependency Inversion**: Los ViewModels dependen de interfaces (Repository), no de implementaciones.
- **Clean Architecture**: Las dependencias siempre apuntan hacia adentro (hacia el Domain). El Domain no conoce ni a Ktor ni a SQLDelight.
- **DRY (Don't Repeat Yourself)**: Centralizar lógica de validación y componentes visuales.
- **Inyección de Dependencias**: Uso obligatorio de **Koin** para proveer dependencias y facilitar Unit Tests.

## 💻 Estándares de Código
- **Estado de UI**: Uso de `ResourceUiState<T>` (Sealed Interface) con estados: `Empty`, `Loading`, `Success(data)` y `Error(message)`.
- **ViewModels**:
    - Manejar estados inmutables con `MutableStateFlow` privado y `asStateFlow` público.
    - Exponer la función `resetUiState()` para limpiar estados al navegar.
    - **Evitar Side Effect Hell**: Usar canales de eventos (Channels/Flows) para acciones únicas como navegación o alertas.
- **UI (Compose)**:
    - Observar estados con `collectAsStateWithLifecycle()`.
    - Separación entre **Stateful Composables** (manejan lógica de VM) y **Stateless Composables** (puros y reutilizables).
    - El bloque `when(uiState)` debe usarse para controlar efectos secundarios fuera de la recomposición principal.

## 🚀 Stack Tecnológico
- **DI**: Koin.
- **Network**: Ktor Client (ContentNegotiation + Serialization).
- **Storage**: SettingsStorage (implementación expect/actual para persistencia local).
- **Resources**: Sistema nativo de KMP (`composeResources`) para imágenes, strings y fuentes.

## 📝 Reglas de Interacción con el Desarrollador
1. **Validación previa**: Antes de crear archivos o carpetas, proponer la estructura al usuario.
2. **Respeto al Estilo**: Seguir el patrón de código proporcionado en los ejemplos del usuario (manejo de validaciones `flag_val`, etc.).
3. **Concisión**: Explicaciones técnicas "al hueso" (directas al punto).
