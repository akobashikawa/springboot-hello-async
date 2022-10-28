# Hello Async

Usando métodos asíncronos en Spring boot.

## Idea
- Permite ejecutar varios hilos en paralelo

## Implementación
- `@EnableAsync` en la clase de la aplicación
- `@Async` en el método asíncrono
- El método asíncrono puede devolver un resultado `Future`
- Al parecer, el `future` devuelto permanece en estado pendiente, sin interrumpir la ejecución, hasta que se le extrae con `.get()`
    - Sería similar al uso de `await` con las `Promises` de javascript

## Referencias
- [Formación Spring: Programación así­ncrona con Spring](https://www.albertcoronado.com/2016/04/25/formacion-spring-programacion-asincrona-con-spring/)
- [Métodos Asíncronos (Spring Boot)](http://blog.ricardo.studio/2018/04/27/metodos-asincronos-springboot/)