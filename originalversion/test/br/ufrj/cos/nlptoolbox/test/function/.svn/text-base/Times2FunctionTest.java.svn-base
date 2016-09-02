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
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.Times2Function;
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
public class Times2FunctionTest {

    public Times2FunctionTest() {
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
    public void test2() throws Exception {

        String exp1 = "x1 * x2 + x2*x2";

        String exp2 = "x1 / x2 + 2 * x1 + x2";



        Function f1 = ExpressionFunctionV3Factory.makeExpressionFunctionV3(exp1);
        Function f2 = ExpressionFunctionV3Factory.makeExpressionFunctionV3(exp2);

        Function fext = ExpressionFunctionV3Factory.makeExpressionFunctionV3("(" + exp1 + ")*(" + exp2 + ")");
        
        Function ft = new Times2Function(f1, f2);
        
        DenseVector variables = DenseVector.valueOf(Float64.valueOf(0.8), Float64.valueOf(0.2));


        Float64 eval1 = (Float64) fext.evaluate(variables);
        Float64 eval2 = (Float64) ft.evaluate(variables);

        Vector geval1 = fext.gradient(variables);
        Vector geval2 = ft.gradient(variables);

        Matrix heval1 = fext.hessian(variables);
        Matrix heval2 = ft.hessian(variables);

        assertTrue(eval1.times(100000).round() == eval2.times(100000).round());

        assertTrue(geval1.equals(geval2));

        assertTrue(heval1.equals(heval2));


    }
}