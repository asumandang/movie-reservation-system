/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.DateCollisionException;

/**
 *
 * @author mark.torres
 */
public interface FileHandler {
    public void save() throws DateCollisionException;
    public void delete();
}
