# Sistema de Alquiler de Prendas

Aplicación backend desarrollada en **Java 17 + Spring Boot + MySQL**, orientada a la administración de un negocio de alquiler de prendas.  
El proyecto implementa una **arquitectura hexagonal (Ports and Adapters)** para mantener separada la lógica del negocio de la infraestructura técnica.

---

## Tabla de contenido

- [Descripción](#descripción)
- [Funcionalidades](#funcionalidades)
- [Arquitectura utilizada](#arquitectura-utilizada)
- [Patrones de diseño aplicados](#patrones-de-diseño-aplicados)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Modelo de base de datos](#modelo-de-base-de-datos)
- [Requisitos previos](#requisitos-previos)
- [Configuración del proyecto](#configuración-del-proyecto)
- [Ejecución del proyecto](#ejecución-del-proyecto)
- [Endpoints principales](#endpoints-principales)
- [Pruebas con Postman](#pruebas-con-postman)
- [Reglas de negocio implementadas](#reglas-de-negocio-implementadas)
- [Posibles mejoras](#posibles-mejoras)
- [Autor](#autor)

---

## Descripción

Este sistema permite gestionar el registro de:

- clientes
- empleados
- prendas
- servicios de alquiler
- proceso de lavandería

La solución fue construida con enfoque en buenas prácticas de diseño de software, uso de capas limpias y persistencia en una base de datos relacional usando **MySQL**.

---

## Funcionalidades

La aplicación soporta las siguientes operaciones:

### Gestión de clientes
- Registro de clientes
- Validación de datos obligatorios

### Gestión de empleados
- Registro de empleados
- Validación de datos obligatorios

### Gestión de prendas
- Registro de prendas por tipo:
  - vestido de dama
  - traje de caballero
  - disfraz
- Consulta de prendas por talla
- Clasificación por tipo

### Gestión de servicios de alquiler
- Registro de servicio de alquiler
- Consulta de servicio por número
- Consulta de servicios vigentes por cliente
- Consulta de servicios por fecha de alquiler

### Gestión de lavandería
- Registro de prenda para lavandería
- Visualización de prendas pendientes por enviar
- Envío de prendas a lavandería por cantidad

---

## Arquitectura utilizada

Se implementó una **arquitectura hexagonal**, también conocida como **Ports and Adapters**.

### ¿Por qué se eligió esta arquitectura?
Porque permite:

- separar la lógica del negocio de la tecnología
- desacoplar la persistencia de la lógica principal
- facilitar pruebas unitarias
- mejorar mantenibilidad y escalabilidad
- cambiar la base de datos o la capa web con menor impacto

### Capas principales

#### `domain`
Contiene el núcleo del negocio:
- entidades del dominio
- excepciones del dominio
- factorías

#### `application`
Contiene:
- casos de uso
- puertos de entrada
- puertos de salida
- servicios de aplicación
- políticas de negocio

#### `infrastructure`
Contiene:
- controladores REST
- adaptadores JPA
- entidades JPA
- repositorios Spring Data
- configuración de Spring

---

## Patrones de diseño aplicados

### 1. Arquitectura Hexagonal
Separa el núcleo del negocio de la infraestructura.

### 2. Repository Pattern
Abstrae el acceso a datos mediante interfaces de repositorio.

### 3. Adapter Pattern
Conecta los puertos de salida con implementaciones concretas usando JPA/MySQL.

### 4. Factory Pattern
Se utiliza en `PrendaFactory` para construir distintos tipos de prendas.

### 5. Mapper Pattern
Convierte entre objetos del dominio y entidades JPA.

### 6. Policy Pattern
Permite encapsular reglas específicas como la disponibilidad de prendas.

---

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **MySQL Workbench**
- **Maven**
- **Postman**

---

## Estructura del proyecto

```text
com.tuempresa.alquiler
 ├─ AlquilerApplication.java
 ├─ domain
 │   ├─ model
 │   ├─ factory
 │   └─ exception
 │
 ├─ application
 │   ├─ port
 │   │   ├─ in
 │   │   └─ out
 │   └─ service
 │
 ├─ infrastructure
 │   ├─ adapter
 │   │   ├─ in
 │   │   │   └─ web
 │   │   └─ out
 │   │       └─ persistence
 │   │           └─ jpa
 │   │               ├─ entity
 │   │               ├─ repository
 │   │               ├─ mapper
 │   │               └─ config
 │   └─ config

 AUTOR: MIGUEL ANGEL TAPIERO PUENTES
