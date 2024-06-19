# Grupo-28-OO2-2024

## Instrucciones para la ejecución del proyecto

1- Crear la base de datos grupo28sistema

2- Descargarse el proyecto y, en la consola, moverse hasta la carpeta donde se encuentra y ejecutar el comando "mvn clean install"

3- Configurar el archivo application.yml con el user y la contraseña de su workbench. Luego de la primer ejecucion del proyecto, cambiar la linea "ddl-auto: create" a "ddl-auto: update".

4- Ejecutar el proyecto (El admin y user se insertan al ejecutar). 

6- Ahora si, ejecutar el proyecto

Las usuarios y contraseñas son:

admin -> admin

user -> user

# Sistema de Stock

Un sistema de stock, también conocido como sistema de inventario, es una estructura organizada
para gestionar y controlar los productos disponibles en un negocio. Podemos definir los siguiente
puntos importantes:

### Registro inicial: 
El proceso comienza con el registro inicial de todos los productos que la empresa tiene en su inventario. Esto implica identificar cada artículo, 
asignarle un código único y registrar información relevante como la descripción del producto, el costo, el precio de venta, etc.
### Entrada de productos: 
Cada vez que llega un nuevo lote de productos al almacén o lugar de almacenamiento, se registra en el sistema. 
Esto incluye la cantidad recibida, la fecha de recepción, el proveedor, el precio de compra, entre otros detalles.
### Salida de productos en stock: 
Cuando se vende un producto, se registra la salida en el sistema.
Esto implica actualizar la cantidad disponible en inventario y ajustar el registro de stock en función de
la venta.
### Control de niveles de stock: El sistema monitorea constantemente los niveles de stock de cada
producto. Cuando un artículo alcanza un nivel de stock mínimo predefinido, se puede generar una
alerta para el reabastecimiento del producto. Esto ayuda a prevenir la falta de productos populares y
optimizar la gestión de inventario.
### Reabastecimiento: 
Cuando se activa una alerta de nivel bajo de stock, se inicia el proceso de reabastecimiento. Esto puede implicar la emisión de 
órdenes de compra a proveedores o la transferencia de productos desde otros lugares si la empresa tiene múltiples puntos de almacenamiento.
### Seguimiento y análisis: 
Además de controlar los niveles de stock, el sistema puede proporcionar informes y análisis detallados 
sobre el rendimiento del inventario. Esto incluye datos como la rotación de inventario, el tiempo de almacenamiento de productos, 
los productos más vendidos, etc. Estos análisis ayudan a optimizar la gestión de inventario y tomar decisiones estratégicas.

Se espera que el TP cuente con los siguientes puntos mínimos:
  1. Usuario Administrador
    a. Alta, baja y modificación de productos de la organización.
    b. Alta de nuevo lote de stock de un producto de la organización.
    c. Vista de informes de estado de productos en relación a su stock.
    d. Alta de pedido de aprovisionamiento de stock de un producto.

  2. Usuario Cliente:
    a. Generar una nueva compra de productos, lo cual genera una baja en el stock (si corresponde).

  4. Usuarios en general:
    a. Login.
    b. Logout.

## **Diagrama de Clases**

![DiagramaTPOO2](https://github.com/AgustinToloza/grupo-28-OO2-2024/assets/112873958/39f2ab58-f27c-4275-81a4-9d101f76bb80)


