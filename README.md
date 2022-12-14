# AgenciaViajes
Obligatorio 2 DDA- Implenteación Spring - Expo 

Este proyecto se divide en dos proyectos diferentes.
El BackEnd en Spring utilizando Java y el FrontEnd en Expo utilizando ReactNative.

Para ejecutarlo primero hay que crear una base de datos en MySQL con el nombre db_ageciaviajes. (Hay una copia con datos pre cargados en la carpeta extra)
Luego se deben colocar los datos del usuario de MySQL en el archivo application.properties que se encuentra en AgenciaViajes\AgenciaViajesSpring\src\main\resources
Los datos a cambiar son:
spring.datasource.username=root
spring.datasource.password=123456
Teniendo todo configurado ejecutamos el archivo AgenciaViajesSpringApplication.java que se encuentra en AgenciaViajes\AgenciaViajesSpring\src\main\java\ctc\obligatorio\AgenciaViajesSpring.
Ahora podemos pasar al FrontEnd.

En el ordenador.

Luego de descargar el proyecto debes ejecutar en consola el comando "npm install" estando parado en AgenciaViajes\AgecniaViajesExpo
Cuando termine de instalar los paquetes debes ejecutar el comando "expo start" en el mismo directorio y se abrirá una página en el navegador.

En el celular. (El celular debe estar conectado a la misma red donde se estan ejecutando los proyectos.)

Para Android:
Busca en Play Store "Expo go" e instala la aplicación Expo de Expo Project.
Abrir la applicación, seleccionar la opcion Scan QR code y escanear el QR que aparece en el navegador.
Para iOS.
Busca en App Store "Expo go" e instala la aplicación Expo Go.
Abrir la camara apuntar al codigo QR que aparece en el navegador

Una ves abierta la app colocamos la ip del ordenador donde se estan ejecutnado los proyectos y el pueto con el que esté trabajando Spring.

