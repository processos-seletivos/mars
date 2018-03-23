package com.contaazul.mars.planner;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.map.CartesianMap;
import com.contaazul.mars.orientation.Orientation;
import com.contaazul.mars.robot.MarsRobot;
import com.contaazul.mars.robot.Robot;
import com.contaazul.mars.robot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarsRobotPlanner extends AbstractRobotPlanner{

    private static final Logger logger = LoggerFactory.getLogger(MarsRobotPlanner.class);

    public MarsRobotPlanner(CartesianMap map) {
        super(map);
    }

    public MarsRobotPlanner() {
        this(new CartesianMap());
    }

    public void sendCommandListToRobot(String code, List<Command> decodedCommands) throws InvalidPositionException, InvalidTransformationTypeException, RobotNotRegisteredException {
        Robot robot = robots.get(code);
        if (robot == null) {
            logger.error("Robô não gerenciado. Abortando envio de comandos");
            throw new RobotNotRegisteredException("Robô não gerenciado. Abortando envio de comandos");
        }


        if (!pathIsSecure(decodedCommands, robot)) {
            throw new InvalidPositionException("Posição inválida");
        }
        executeCommands(decodedCommands, robot);
    }

    private void executeCommands(List<Command> decodedCommands, Robot robot) {
        decodedCommands.stream().forEach((Command command) -> {
            robot.executeCommand(command);
        });
    }

    /**
     * Dada uma sequência de comandos, avalia se é possível para o robô trilhar o caminho, antes de efetivamente
     * repassar os comandos para ele
     * @param decodedCommands Lista de comandos
     * @param robot Robô
     * @return Sim, caso seja possível para o robô trilhar o caminho, e não caso contrário
     */
    Boolean pathIsSecure(List<Command> decodedCommands, Robot robot) throws InvalidTransformationTypeException {
        CartesianCoordinate robotCoordinate = (CartesianCoordinate)robot.getCoordinate();
        Orientation robotOrientation = robot.getOrientation();
        Boolean pathIsSecure = true;
        for (Command command : decodedCommands){
            CartesianCoordinate newRobotCoordinate = (CartesianCoordinate)command.getNewCoordinate(robotCoordinate,robotOrientation);
            Orientation newRobotOrientation = command.getNewOrientation(robotOrientation);
            if(!this.validPosition(newRobotCoordinate, robot)){
                pathIsSecure = false;
                //System.out.println("Posição (" + newRobotCoordinate.getX() + ", " + newRobotCoordinate.getY() + ", " + newRobotOrientation.getValue() + ") é inválida.");
                break;
            }
            robotCoordinate = newRobotCoordinate;
            robotOrientation = newRobotOrientation;
        }
        return  pathIsSecure;
    }

}
