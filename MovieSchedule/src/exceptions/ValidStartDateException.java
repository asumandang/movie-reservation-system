/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author mark.torres
 */
public class ValidStartDateException extends Exception{

    public ValidStartDateException() {
        super("Start date is available but the end date is not. Please try again.");
    }

    public ValidStartDateException(String message) {
        super(message);
    }
    
}
