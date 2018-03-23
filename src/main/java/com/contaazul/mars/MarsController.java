package com.contaazul.mars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
/**
 * Controller utilizado para a comunicação com os robôs
 */
public class MarsController {

    private static final Logger logger = LoggerFactory.getLogger(MarsController.class);
    @Autowired
    MarsRobotService marsRobotService;

    /**
     * Método utilizado para movimentar o robo de acordo com a sequencia de comandos passada na URL, e que retorna
     * a posição do robô
     * @param response Resposta HTTP, utilizada para definir o status code
     * @param commands Sequencia de comandos que deve ser passada para o robô. Caso seja nula, nenhuma ação será executada
     *                 pelo robô, somente retornando sua posição atual
     * @return  Posição atual do robô após os comandos, no formato (x, y, o), onde x é a posição no eixo x, y é a posição
     * no eixo y e o é a orientação (N, S, E, W) ou vazio caso não seja possível executar a sequencia de comandos.
     */
    @PostMapping(value={ "/rest/mars", "/rest/mars/{commands}"})
    public String mars(
            HttpServletResponse response,
            @PathVariable(required = false) String commands, @RequestParam("robotCode") String code)  {
        String retorno = "";
        try {
            String robotCode = (code!=null)?code:"robot1";
            logger.debug("Atendendo essa sequencia de comandos: " + commands);
            retorno = marsRobotService.sendCommands(robotCode, commands);
            logger.debug("Sequencia de comandos atendida com sucesso!");
        }
        catch (Exception e) {
            logger.debug("Não foi possível atender a sequencia de comandos devido esse erro: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return retorno;
    }
}
