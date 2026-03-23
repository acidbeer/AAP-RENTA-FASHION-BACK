package com.rentfashion.rentappfashion.application.service.command;

public class CommandInvoker {
    public <T> T ejecutar(Command<T> command) {
        return command.execute();
    }
}
