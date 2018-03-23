# mars
Projeto para controle de robôs

### Como executar a aplicação

```
mvn spring-boot:run
```
### Como rodar os testes

```
mvn verify
```
### Como debugar a aplicação

```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```

### API

Exemplo de chamada da API com sequencia de comandos válidos:

```
curl -s --request POST http://localhost:8080/rest/mars/MML -v
```

Exemplo de resposta:
```
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /rest/mars/MML HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 9
< Date: Fri, 23 Mar 2018 21:12:08 GMT
< 
* Connection #0 to host localhost left intact
(0, 2, W)
```

Exemplo de chamada da API com sequencia de comandos válidos:

```
curl -s --request POST http://localhost:8080/rest/mars/AAA -v
```

Exemplo de resposta:
```
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /rest/mars/AAA HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
> 
< HTTP/1.1 400 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 0
< Date: Fri, 23 Mar 2018 21:11:46 GMT
< Connection: close
< 
* Closing connection 0
```

