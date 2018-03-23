# mars
Projeto para controle de robôs, por meio de envio de sequencias de caracteres, em que cada caracter corresponde a um determinado comando, conforme a tabela a seguir:

| Comando        | Significado                                                 |
|----------------|-------------------------------------------------------------|
| M              | Move o robô 1 unidade para frente na direção em que estiver |
| L              | Vira o robô 90 graus para a esquerda                        |
| R              | Vira o robô 90 graus para a direita                         |
| Qualquer outro | Comando inválido                                            |


### Considerações

Foi permitida a possibilidade de gerenciar mais de um robô. A classe responsável por gerenciar robôs é a classe MarsRobotPlanner. Basta criar um robô e chamar o método manageRobot desse modo:

```java
marsRobotPlanner.manageRobot(marsRobot);
```
Essa classe avalia se a sequencia de comandos enviada para o robô é válida, antes de efetivamente repassar os comandos para o robô. Ela verifica se a coordenada é válida, além de verificar se existe algum robô no caminho do robô gerenciado.

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

