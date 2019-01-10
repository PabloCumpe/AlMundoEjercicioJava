
-El Proyecto fue realizado usando java 8 y maven 3.5.3.

-Cuando se compila el proyecto con maven se crea un ".jar" y una carpeta "libs" con las dependencias usadas.

-El programa recibe 3 parametros: cantidade de hilos a usar para consumir las llamadas, la cantidad de llamadas 
a procesar, y la cantidad de empleados que se quieren poner a atender esas llamadas.

-Cada hilo ejecuta el metodo "dispatchCall" de la clase "Dispatcher".

-El metodo "dispatchCall" conciste en tomar una llamada de la cola de llamadas y procesarla por un empleado que se 
encuentre en la cola de empleados disponibles.

-Si no hay llamadas, el hilo queda esperando a que aparezca una llamada, es decir cada hilo procesa siempre una llamada.

-Para la queue de empleados disponibles se utilizo una cola de prioridad para obtener primero los empleados operadores,
 luego los supervisores, y por ultimo los directores.
 
-En caso de que no haya empleados disponibles para procesar la llamada, se creo un metodo recursivo en el 
cual si no hay empleados disponibles, se esperan 5 segundos y luego se vuelve verificar si aprecio un empleado nuevo.
 
-La clase "Dispatcher" tiene la capacidad de ser consumida por 10 hilos concurrentemente.
