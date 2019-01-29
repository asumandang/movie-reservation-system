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
public class ValidEndDateException extends Exception {

    public ValidEndDateException() {
        super("Date collision occured but end date is available.Please try again.");
    }

    public ValidEndDateException(String message) {
        super(message);
    }
    
}
