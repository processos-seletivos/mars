package com.contaazul.mars.robot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Interface com os métodos comuns aos Comand Parsers.
 */
public interface CommandParser {

    HashMap<String, Class> validCommands = new HashMap<>();

    /**
     * Dada uma cadeia de caracteres, transforma em uma lista de comandos reconhecidos pelo Parser
     * @param commandString Cadeia de caracteres com comandos para o robô
     * @return Lista de comandos reconhecidos
     * @throws InvalidCommandException Exceção lançada caso haja um comando não reconhecido pelo Parser
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    default List<Command> decodeCommandString(String commandString) throws InvalidCommandException, IllegalAccessException, InstantiationException {
        List<Command> decodedCommands = new ArrayList<>();
        if (commandString != null){
            List<String> words = Arrays.asList(commandString.split(""));
            for (String word : words){

                if (!validCommands.containsKey(word)){
                    throw new InvalidCommandException("Comando não existente");
                }
                Command decodedCommand = (Command)validCommands.get(word).newInstance();
                decodedCommands.add(decodedCommand);
            }
        }
        return decodedCommands;

    }


}
