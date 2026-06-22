# TP Integrador Programación II - Food Store

## Integrantes

* Franco Pagés
* Joel Alvarez
* Marcos Bermejo

# Descripción

Aplicación de consola desarrollada en Java para la gestión de un local gastronómico ("Food Store").

El sistema permite administrar:

* Categorías
* Productos
* Usuarios
* Pedidos

Implementando los conceptos de Programación Orientada a Objetos vistos durante la cursada.


# Tecnologías Utilizadas

* Java 17 (o la versión utilizada)
* IntelliJ IDEA
* Colecciones (`ArrayList`)
* Programación Orientada a Objetos

# Funcionalidades

## Categorías

* Crear categoría
* Listar categorías
* Modificar categoría
* Eliminar categoría (baja lógica)

## Productos

* Crear producto
* Listar productos
* Modificar producto
* Eliminar producto (baja lógica)
* Asociación con categorías

## Usuarios

* Crear usuario
* Listar usuarios
* Modificar usuario
* Eliminar usuario (baja lógica)
* Validación de correo electrónico único

## Pedidos

* Crear pedido
* Agregar productos al pedido
* Calcular total automáticamente
* Actualizar estado del pedido
* Eliminar pedido (baja lógica)
* Control de stock

---

# Estructura del Proyecto

```text
src/
└── integrado/prog2/
    ├── Main.java
    ├── config/
    ├── entities/
    │   ├── Base.java
    │   ├── Categoria.java
    │   ├── Producto.java
    │   ├── Usuario.java
    │   ├── Pedido.java
    │   └── DetallePedido.java
    │
    ├── enums/
    │   ├── Estado.java
    │   ├── FormaPago.java
    │   └── Rol.java
    │
    ├── exception/
    │   ├── EntidadNoEncontradaException.java
    │   ├── MailDuplicadoException.java
    │   ├── StockInvalidoException.java
    │   └── StockInsuficienteException.java
    │
    ├── interfaces/
    │   └── Calculable.java
    │
    └── services/
        ├── CategoriaService.java
        ├── ProductoService.java
        ├── UsuarioService.java
        └── PedidoService.java
```

# Principios Aplicados

## Herencia

Todas las entidades heredan de la clase base:

```java
Base
```

que contiene:

```java
id
eliminado
```

## Composición

Relación entre:

```java
Pedido
```

y

```java
DetallePedido
```

Un pedido contiene múltiples detalles.

## Enumeraciones

Se utilizaron los siguientes enums:

```java
Estado
FormaPago
Rol
```

## Interfaces

Implementación de:

```java
Calculable
```

para el cálculo automático del total de pedidos.

## Manejo de Excepciones

Se desarrollaron excepciones personalizadas para controlar errores de negocio:

* EntidadNoEncontradaException
* MailDuplicadoException
* StockInvalidoException
* StockInsuficienteException

# Reglas de Negocio Implementadas

* No se permiten categorías duplicadas.
* No se permiten correos electrónicos repetidos.
* El precio de un producto debe ser mayor o igual a cero.
* El stock de un producto debe ser mayor o igual a cero.
* La cantidad solicitada en un pedido debe ser mayor a cero.
* No es posible vender más unidades que las disponibles en stock.
* El total del pedido se calcula automáticamente.

# Persistencia

Para esta versión del proyecto no se utilizó base de datos.

La información se almacena temporalmente en memoria utilizando:

```java
ArrayList
```

durante la ejecución del programa.

# Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Ejecutar la clase:

```java
Main.java
```

3. Utilizar el menú principal para gestionar categorías, productos, usuarios y pedidos.

# Enlace del video

