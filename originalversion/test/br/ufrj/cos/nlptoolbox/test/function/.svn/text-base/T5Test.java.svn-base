/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test.function;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV2;
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
public class T5Test {

    public T5Test() {
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

        Float64 v1 = Float64.valueOf(0.05);
        Float64 v2 = Float64.valueOf(1);

        ExpressionFunctionV2<Float64> f1 = new ExpressionFunctionV2("x1 + x2");
        f1.setNumberfactory(new Float64NumberFactory());

        DenseVector<Float64> dv = DenseVector.valueOf(v1,v2);

        Float64 r = f1.evaluate(dv);

        System.out.println("r: " + r);

        System.out.println("r: " + f1.gradient(dv));

        System.out.println("r: " + f1.hessian(dv));
        //System.out.println("gradient:" + f1.getGradientString());

        

        

    }
}