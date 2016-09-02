/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.exceptions;

/**
 *
 * @author lages
 */
public class NoMinFoundException extends Exception {

    /**
     * Creates a new instance of <code>NoMinFoundException</code> without detail message.
     */
    public NoMinFoundException() {
    }


    /**
     * Constructs an instance of <code>NoMinFoundException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NoMinFoundException(String msg) {
        super(msg);
    }
}
