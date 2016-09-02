/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test.function;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Diego
 */
public class T2Test {

    public T2Test() {
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

        ExpressionFunction r1 = new ExpressionFunction("x1-1");
        r1.setNumberfactory(new Float64NumberFactory());

        DenseVector<Float64> iv = DenseVector.valueOf(Float64.valueOf(1.0));

        r1.evaluate(iv);
    }

    @Test
    public void test2() throws Exception {

        ExpressionFunction r1 = new ExpressionFunction("0.00000000000000000000000000000000001*x1-1");
        r1.setNumberfactory(new Float64NumberFactory());

        DenseVector<Float64> iv = DenseVector.valueOf(Float64.valueOf(1.0));

        r1.evaluate(iv);
    }
}