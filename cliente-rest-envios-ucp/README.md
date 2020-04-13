# Cliente Rest Envios UCP --

Sistema que permite gestionar un cliente con servicios rest desplegando por docker

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

## Requisistos Instalacion
- Instalar docker

## Instalación 
- Descargar el Docker de la siguiente pagina https://www.docker.com/

## Ejecutando las pruebas
- Se verifica la creación de la imagen: docker images
- Se verifica ejecución de la aplicación cliente corriendo desde el contenedor por la url localhost:8080/cliente/init

## Despliegue
- Ejecutar Docker
- Se construye el contenedor a partir del dockerfile mediante comando docker build -t clienterestspringboot:0.0.1 .
- Se ejecuta la imagen para crear un contenedor mediante el comando docker ps -a

## Autores
- Diego Salinas 
- Hector Ramirez
