package exceptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mark.torres
 */
public class DateCollisionException extends Exception{

    public DateCollisionException() {
        super("Schedule has the same dates. Please enter another date.");
    }

    public DateCollisionException(String message) {
        super(message);
    }
    
    
    
}
