/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.test;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;

/**
 *
 * @author Diego
 */
public class BigRosenbrockTest extends BaseMethodTest {

//    @Override
//    protected int getMaxiterations() {
//        return 10000;
//    }
//
//    @Override
//    protected int getMaxlineiterations() {
//        return 20;
//    }

    int N = 1;

    @Override
    protected double getExpectedmin() {
        return 0;
    }

    @Override
    protected ExpressionFunction getFunction() {

        String exp = "";

        for (int i=1;i<N;i++) {
            if (i > 1) {
                exp = exp + " + ";
            }

            exp = exp + "(1-x" + i + ")^2+100*(x" + (i+1) + "-x" + i + ")^2";

        }

        return new ExpressionFunction(exp);

//        return new ExpressionFunction("(1-x1)^2+100*(x2-x1)^2 +" +
//                                      "(1-x2)^2+100*(x3-x2)^2 +" +
//                                      "(1-x3)^2+100*(x4-x3)^2 +" +
//                                      "(1-x4)^2+100*(x5-x4)^2 +" +
//                                      "(1-x5)^2+100*(x6-x5)^2 +" +
//                                      "(1-x6)^2+100*(x7-x6)^2 +" +
//                                      "(1-x7)^2+100*(x8-x7)^2 +" +
//                                      "(1-x8)^2+100*(x9-x8)^2 +" +
//                                      "(1-x9)^2+100*(x10-x9)^2"
//                                      );
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

        double ret[] = new double[N];
        ret[0] = -2;

        for (int i=1;i<N;i++) {
            ret[i] = 1;
        }

        return ret;
        //return new double[] {-2,1,1,1,1,1,1,1,1,1};
    }

}