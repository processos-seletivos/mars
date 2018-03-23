package com.contaazul.mars.robot.command;

public class InvalidCommandException extends Exception{
    InvalidCommandException(String mensagem) {
        super(mensagem);
    }
}
