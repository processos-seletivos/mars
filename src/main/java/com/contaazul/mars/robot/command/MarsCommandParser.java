package com.contaazul.mars.robot.command;

/**
 * Parser utilizado para decodificar os comandos dos robôs em Marte
 */
public class MarsCommandParser extends AbstractCommandParser{
    public MarsCommandParser() {
        this.validCommands.put("M", MarchCommand.class);
        this.validCommands.put("L", LeftCommand.class);
        this.validCommands.put("R", RightCommand.class);
    }
}
