# 📚 Papelería CRUD - Spring Boot

Aplicación para gestionar ventas e inventario de una papelería. Desarrollada con Java 21, Spring Boot y MySQL.

## 🚀 Funcionalidades
- Registro de usuarios con contraseña cifrada
- Login básico
- CRUD de productos
- Registro de ventas
- Consulta de histórico de ventas

## 🛠️ Tecnologías
- Java 21
- Spring Boot
- Maven
- MySQL
- IntelliJ IDEA
- Postman

## 📦 Instalación
1. Clonar el repositorio
2. Crear la base de datos `papeleria_db`
3. Configurar `application.properties`
4. Ejecutar el proyecto desde IntelliJ

## 📮 Endpoints
Ver colección de Postman en el archivo 'crudPapeleria.postman_collection'

## 🔐 Autenticación
La app utiliza JWT para proteger los endpoints. Solo los siguientes están abiertos:

POST /usuarios → Registro de usuario

POST /usuarios/login → Login y generación de token

Todos los demás requieren el header:
Authorization: Bearer <token>

## Pruebas con Postman
Importa la colección postman/papeleria_collection.json

Ejecuta:

Registro de usuario

Login (guarda token como variable)

Acceso a productos y ventas con token

## Estructura del proyecto

crud/
├── src/                  # Código fuente
├── pom.xml               # Configuración Maven
├── README.md             # Documentación
├── scripts/              # SQL de inicialización
├── postman/              # Colección de pruebas



