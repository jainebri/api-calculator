# api-calculator

Repositorio para al API Calculator que es una prueba técnica pata Sanitas.


Pasos para Generar y Ejecutar el API.

Los siguientes son los prerrequisitos para poder instalar y ejecutar el API:

- Instalar y Configurar la JDK 8.
- Instalar y Configurar Maven. Solo se requiere el repositorio Central de MAVEN.

1. Instalación de las Librerías Trace.

Puesto que las librerías de trace suministradas como parte de la prueba, no están instaladas en ningún reportorio de Maven, las requerimos instalar en el repositorio local. Esto se logra ejecutando el siguiente comando:
mvn install:install-file -Dfile=tracer-1.0.0.jar -DgroupId=io.corp -DartifactId=calculator -Dversion=1.0.0 -Dpackaging=jar -DgeneratedPom=true

2. Descargar el Repositorio.

Clonar el repositorio bien utilizando el comando de git:

git clone https://github.com/jainebri/api-calculator.git

3. Compilar el API

Para compilar el API se debe ejecutar el siguiente comando en el directorio raíz del proyecto.

mvn clean install

![Compilar API](https://github.com/jainebri/api-calculator/blob/develop/images/01-Compliar.png)

4. Ejecutar el API

Para ejecutar el API se debe ejecutar el siguiente comando en el directorio raíz del proyecto:

java -jar target\calculator-0.0.1-SNAPSHOT.jar

![Compilar API](https://github.com/jainebri/api-calculator/blob/develop/images/02-Ejecutar.png)

5. Visualización de Documentación:

La documentación Swagger del API estará disponible en la siguiente ruta:

http://localhost:8080/api-calculator/swagger-ui.html

6. Consumo de la Operación

La operación para calcular operaciones aritméticas esta diseñada como un POST cuyo parámetro de entrada es el objeto ArithmeticOperation.java. Este tiene un grupo, que representa una categoría de operaciones, por el momento Aritméticas, pero en el futuro podrían existir más operaciones soportadas en el API como las Trigonométricas.

![Compilar API](https://github.com/jainebri/api-calculator/blob/develop/images/03-Postman.png)

Para soportar la fácil extensión del API en cuanto sus operaciones, se implementó un patrón Factoría Desacoplada, la cual permite desacoplar los diversos servicios de cálculo como se puede observar en el siguiente diagrama.

![Decoupled Factory](https://github.com/jainebri/api-calculator/blob/develop/images/05-Decoupled-Factory.png)

Extender cálculos trigonométricos requerirá solo la creación de un servicio que herede de la clase IOperationsService.

7. Gestión Global de Errores y Excepciones

A partir de la experiencia con la Apificación en Spring Boot, es importante un modelo estándar de gestión de errores y excepciones en el API. Por lo tanto, se ha incluido un sistema global de gestión de errores y excepciones basado en la característica Controller Advice de Spring.

![Gestión Global de Errores y Excepciones](https://github.com/jainebri/api-calculator/blob/develop/images/04-Gestion-Global-Errores-Excepciones-Global%20Exceptions.png)

Esto tiene la ventaja de homogenizar las excepciones que los programadores deben lanzar en el API.
La gestión esta centralizada por Spring y los errores retornados los clientes del API poseen la misma estructura siempre.
A nivel del atributo de calidad de software, mantenibilidad, las tareas de desarrollo asociadas a la gestión de incidencias y defectos se facilita mucho debido a que las excepciones son lanzadas desde cada capa de arquitectura del API, y por tanto el origen de los errores son más fáciles de localizar en el código del API.
Debido al poco tiempo de la prueba, no se incluye el diagrama de Arquitectura del API, no hay pruebas unitarias, ni pruebas de integración. Tampoco se programaron las validaciones de los parámetros de entrada, pero el API esta preparado a nivel de Arquitectura para soportar el modelo de validaciones de Spring (@InitBinder a nivel de Controllers). Y a nivel de Servicios es fácilmente incluible. 
