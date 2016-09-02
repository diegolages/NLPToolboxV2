/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test.function;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV2;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3Factory;
import br.ufrj.cos.nlptoolbox.functions.QuadraticFunction;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Diego
 */
public class QuadraticFunctionTest {

    public QuadraticFunctionTest() {
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

//    @Test
//    public void test1() throws Exception {
//
//        double [][] sigma = new double[][] {
//            { 1.5,  0.5},
//            { 0.5,  1.5}
//        };
//
//        run(sigma);
//    }

    @Test
    public void test2() throws Exception {

        double [][] sigma = new double[][] {
            { 1.5,  0.5, 9000},
            { 0.5,  2.5, 18000},
            { 9000,  18000, 0.00009},
            
        };

        run(sigma);
    }

    
    
    private void run(double [][] sigma) throws Exception {


        String objective = "";

        for (int i = 0; i < sigma.length; i++) {
            for (int j = 0; j < sigma.length; j++) {
                objective = objective + " + " + sigma[i][j] + " * (x" + (i + 1) + ") * (x" + (j + 1) + ")";
            }
        }

        Function f = ExpressionFunctionV3Factory.makeExpressionFunctionV3(objective);

        QuadraticFunction qf = new QuadraticFunction(sigma);

        DenseVector variables = DenseVector.valueOf(Float64.valueOf(0.8), Float64.valueOf(0.1), Float64.valueOf(0.1));

        Float64 eval1 = (Float64) f.evaluate(variables);
        Float64 eval2 = qf.evaluate(variables);

        Vector geval1 = f.gradient(variables);
        Vector geval2 = qf.gradient(variables);

        Matrix heval1 = f.hessian(variables);
        Matrix heval2 = qf.hessian(variables);

        assertTrue(eval1.times(100000).round() == eval2.times(100000).round());

        assertTrue(geval1.equals(geval2));

        assertTrue(heval1.equals(heval2));


    }
}