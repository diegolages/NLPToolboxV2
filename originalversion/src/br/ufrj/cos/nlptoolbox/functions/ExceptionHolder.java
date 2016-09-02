/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.functions;

/**
 *
 * @author lages
 */
public class ExceptionHolder {

    Exception ex = null;

    public ExceptionHolder() {
    }

    public void setException(Exception e) {
        ex = e;
    }

    public Exception getException() {
        return ex;
    }
}
