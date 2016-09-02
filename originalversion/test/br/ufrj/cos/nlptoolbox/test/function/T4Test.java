/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test.function;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Diego
 */
public class T4Test {

    public T4Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1() throws Exception {

        String f = " - 0.9984350081 * x1^2 - 0.8669307092 * x2^2 - 1.016395892 * x3^2";

        ExpressionFunction f2 = new ExpressionFunction(f);
        f2.setNumberfactory(new Float64NumberFactory());

        Vector<Float64> iv = DenseVector.valueOf(new Float64[] {
            Float64.valueOf(2), Float64.valueOf(2), Float64.valueOf(2)
        });

        Float64 ret = (Float64) f2.evaluate(iv);

        System.out.println(ret.doubleValue());

    }

}