# Missions API
[https://mission-sergio-losada.cloud.okteto.net/swagger-ui.html]
## API REST para la gestión de misiones espaciales:

### campos:
 - uuid: identificador único de la misión
 - name: nombre de la misión
 - missionStartDate: fecha de inicio de la misión
 - missionEndDate: fecha de fin de la misión
 - duration: duración en horas de la misión
 - starship: nave que se va a usar en la misión
 - people: capitanes que van a ir a la misión
 - crew: cantidad de tripulantes adicionales para la misión
 - planets: planetas que tiene que recorrer la misión
 - reward: recompensa por completar la misión en €
 - ratio: ratio recompensa/horas

### Endpoints

Insertar misión espacial:
 ```
 POST /mission
 ```
 
Listar todas las misiones:
 ```
 GET /mission 
 ```

Listar todas las misiones en las que participan los capitanes seleccionados:
 ```
 GET /mission?captain=1,2...
 ```

Recomendador de misiones según el criterio X (reward o ratio reward/hours):
 ```
 GET /mission/recommend?criteria=X
 ``` 

### Documentación
La API Missions ha sido documentada, siguiendo el estándar OpenAPI, a través de la herramienta Swagger.
La interfaz gráfica de dicha herramienta se encuentra en el siguiente enlace: [https://mission-sergio-losada.cloud.okteto.net/swagger-ui.html].

### Despliegue
La interfaz de Swagger expone abiertamente los endpoints de conexión de la API, haciéndola accesible a cualquier usuario. Para lograr este acceso público, ha sido preciso el despliegue de la API en Okteto, una plataforma cloud gratuita basada en Kubernetes, la cual requirió adjuntar los ficheros Dockerfile y docker-compose.yml, incluidos en este repositorio.

### Base de Datos
Acorde a la tecnología cloud sobre la que se realizó el despliegue de la API, para el Servidor de Base de Datos se ha utilizado un esquema no relacional orientado a documentos, como es MongoDB. En concreto, se ha hecho uso de su servicio Atlas, que permite la creación de colecciones dentro de un cluster en la nube, también de forma gratuita.

### Comunicación
La traducción entre el servidor web Spring Boot y la Base de Datos MongoDB se realiza a través de objetos en formato JSON. Existen campos que calcula el servidor (como la missionEndDate en función de la duration), que por tanto no aparecen en el cuerpo de la petición pero sí deben hacerlo en el cuerpo de la respuesta. Es por ello que la capa de negocio del servidor incorpora un mapper que realiza esta traducción entre los objetos DTO (comunicación con el usuario) y los objetos DAO (comunicación con la base de datos).

### Diseño
Siguiendo las buenas prácticas de este framework de Java, el proyecto de Spring Boot ha sido dividido en 3 capas:
- Controlador: gestiona la recepción de peticiones y los códigos de estado del protocolo HTTP
- Servicio: implementa la lógica de negocio y ejecuta las reglas de validación propias de la aplicación
- Repositorio: efectúa la conexión con la base de datos

![Patrón de diseño de capas](https://drive.google.com/file/d/1oOjeJFVAv1CtqBn1PnrB6wJ1Z8A61TKQ/view?usp=sharing)

### Funcionamiento
La fuente de datos de esta API es la API de Star Wars [https://swapi.dev/documentation]. En la operación POST de inserción en base de datos, el servidor demanda las URL de los recursos (starships, people, planets) que queramos asociar a nuestra Misión. La operación GET se encarga de realizar las llamadas correpsondiente a SWAPI para devolver en el cuerpo de la respuesta los datos actualizados y con el formato especificado en el apartado 2.1 del enunciado.

Un ejemplo de cuerpo de una petición POST sería el siguiente:
```json
{
   "name": "mission1",
   "starship": "https://swapi.dev/api/starships/5/",
   "people": [
      "https://swapi.dev/api/people/1/"
   ],
   "planets": [
      "https://swapi.dev/api/planets/1/",
      "https://swapi.dev/api/planets/2/"
   ],
   "reward": 100,
   "crew": 50
}
```

Y el cuerpo de la respuesta devuelto sería el siguiente:
```json
{
   "uuid": "f4bed2d3-d6fd-4005-b69c-58dad48cd4ed",
   "name": "mission1",
   "missionStartDate": "2022-12-05T02:23:01.205+00:00",
   "missionEndDate": "2022-12-06T17:23:01.205+00:00",
   "duration": 39,
   "starship": {
      "url": "https://swapi.dev/api/starships/5/",
      "name": "Sentinel-class landing craft",
      "crew": "5",
      "passengers": "75",
      "pilots": []
   },
   "people": [
      {
         "url": "https://swapi.dev/api/people/1/",
         "name": "Luke Skywalker"
      }
   ],
   "crew": 50,
   "planets": [
      {
         "url": "https://swapi.dev/api/planets/1/",
         "name": "Tatooine",
         "diameter": 10465.0
      },
      {
         "url": "https://swapi.dev/api/planets/2/",
         "name": "Alderaan",
         "diameter": 12500.0
      }
   ],
   "reward": 100.0,
   "ratio": 2.56
}
```

### Detalles
Para el endpoint que realiza una recomendación de misiones, se han incorporado dos posibles criterios:
- GET /mission/recommend?criteria=reward: mayor recompensa
- GET /mission/recommend?criteria=ratio: mayor ratio recompensa/horas
Al inicio de la llamada, la cola estará llena con todas las misiones existentes. Cuando una de estas dos llamadas devuelve en orden todas las misiones, llamadas sucesivas devolverán un cuerpo de respuesta vacío. Para reencolar todas las misiones, es necesario realizar una petición a cualquier otra llamada que no sea el endpoint de recomendación (p.e., una llamada GET /mision).

### Pruebas
El proyecto Spring Boot adjunta pruebas unitarias tanto de controlador (librería WebTestClient que simula un cliente HTTP) como de servicio (librería mockito para crear un entorno mockeado), así como unas pruebas de integración que simulan el despliegue en un contenedor Docker con una imagen de MongoDB. En el repositorio se incluye también el fichero mission.postman_collection.json, una colección de peticiones de prueba para Postman.
