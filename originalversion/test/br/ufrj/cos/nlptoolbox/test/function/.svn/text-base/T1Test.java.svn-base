/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test.function;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.functions.R1;
import br.ufrj.cos.nlptoolbox.functions.R2;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.barrier.BarrierCompositeFunction;
import br.ufrj.cos.nlptoolbox.restrictions.barrier.FractionBarrierFunction;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego
 */
public class T1Test {

    public T1Test() {
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

        Float64 mi = Float64.valueOf(0.05);

        R2 r2 = new R2(mi.doubleValue());

        Float64 ix1 = Float64.valueOf(1.005);
        Vector<Float64> iv = DenseVector.valueOf(ix1);

        Float64 feval = r2.evaluate(iv);

        Vector<Float64> grad = r2.gradient(iv);

        Matrix<Float64> hessian = r2.hessian(iv);

        ExpressionFunction f1 = new ExpressionFunction("x1^2");
        f1.setNumberfactory(new Float64NumberFactory());

        ExpressionFunction h1 = new ExpressionFunction("x1-1");
        h1.setNumberfactory(new Float64NumberFactory());

        List<Function> fs = new java.util.Vector();
        fs.add(h1);

        BarrierCompositeFunction bcf = null;// new BarrierCompositeFunction(f1,fs,mi,new Float64NumberFactory());

        assertTrue(bcf.evaluate(iv).equals(feval));

        assertTrue(bcf.gradient(iv).equals(grad));

        //assertTrue(bcf.hessian(iv).equals(hessian));
        
        Float64 tr = (Float64) bcf.hessian(iv).minus(hessian).trace();

        assertTrue(Math.abs(tr.doubleValue()) < 0.10);
        

    }
}