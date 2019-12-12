package com.gojek.parkinglot.service;

/**
 * The type Command
 *
 * @author Mohd Nadeem
 */
public interface CommandHandler {

    /**
     * Executes the respective command
     * @param args the variable length arguments
     * @return returns the response of command
     */
    String execute(String[] args);

}
