/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.test;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;

/**
 *
 * @author Diego
 */
public class RosenbrockTest extends BaseMethodTest {

    @Override
    protected double getExpectedmin() {
        return 0;
    }

    @Override
    protected ExpressionFunction getFunction() {
        return new ExpressionFunction("(1-x1)^2+100*(x2-x1)^2");
    }

    @Override
    protected double getAcceptablediff() {
        return 0.00001;
    }

    @Override
    protected double[] getExpectedsolution() {
        return new double[] {1,1};
    }

    @Override
    protected double[] getInitialpoint() {
        return new double[] {-2,1};
    }

}