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
public class FreudensteinRothTest extends BaseMethodTest {

    @Override
    protected double getExpectedmin() {
        return 0;
    }

    @Override
    protected ExpressionFunction getFunction() {
        //return new ExpressionFunction("(-13 + x1 + ((5 - x2)*x2 - 2)*x2)^2");

        return new ExpressionFunction("(-13 + x1 + ((5 - x2)*x2 - 2)*x2)^2 + (-29 + x1 + ((x2 + 1)*x2 - 14)*x2)^2");
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
        return new double[] {0.5, -2};
    }

}