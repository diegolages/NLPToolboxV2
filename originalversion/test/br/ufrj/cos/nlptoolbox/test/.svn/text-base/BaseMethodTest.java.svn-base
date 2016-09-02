/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.test;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.QuadraticNorm;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.functions.NumberFactoryableFunction;
import br.ufrj.cos.nlptoolbox.linesearch.NewtonRaphson;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.methods.Newton;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.ConjugateDescendent;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.DaiYuan;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.Daniel;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.FletcherReeves;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.HagerZhang;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.HestenesStiefel;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.LiuStorey;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.PolakRibiere;
import br.ufrj.cos.nlptoolbox.methods.quasinewton.BFGS;
import br.ufrj.cos.nlptoolbox.methods.quasinewton.Broyden;
import br.ufrj.cos.nlptoolbox.methods.quasinewton.DFP;
import br.ufrj.cos.nlptoolbox.numberfactories.ComplexNumberFactory;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.numberfactories.FloatingPointNumberFactory;
import br.ufrj.cos.nlptoolbox.numberfactories.RationalNumberFactory;
import br.ufrj.cos.nlptoolbox.numberfactories.RealNumberFactory;
import java.util.List;
import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.number.FloatingPoint;
import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.number.Real;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
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
public class BaseMethodTest {

    public BaseMethodTest() {
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

    protected double[] getExpectedsolution() {
        return null;
    }

    protected int getMaxlineiterations() {
        return 20;
    }


    private Vector _getExpectedsolution(NumberFactory numberfactory) {
        return convertToVector(numberfactory,getExpectedsolution());
    }

    protected double getExpectedmin() {
        return 0;
    }


    private Field _getExpectedmin(NumberFactory numberfactory) {
        return numberfactory.makeNumber(getExpectedmin());
    }

    

    private Vector convertToVector(NumberFactory numberfactory, double[] expectedsolution) {

        java.util.Vector v = new java.util.Vector();
        for (double d : expectedsolution) {
            v.add(numberfactory.makeNumber(d));
        }

        return DenseVector.valueOf(v);

    }

    protected NumberFactoryableFunction getFunction() throws Exception {
        return null;
    }

    protected double[] getInitialpoint() {
        return null;
    }

    private Vector _getInitialpoint(NumberFactory numberfactory) {
        return convertToVector(numberfactory,getInitialpoint());
    }


    protected int getMaxiterations() {
        return 10000;
    }

    protected void setParameters(MinimizationMethod mm) {

    }

    protected double getPrecision() {
        return 0.000001;
    }

    private Field _getPrecision(NumberFactory numberfactory) {
        return numberfactory.makeNumber(getPrecision());
    }

    protected double getAcceptablediff() {
        return 0;
    }

    private Field _getAcceptablediff(NumberFactory numberfactory) {
        return numberfactory.makeNumber(getAcceptablediff());
    }

    private List<Class> mmtoevaluate() {
        java.util.Vector r = new java.util.Vector();
        r.add(Newton.class);
//        r.add(HestenesStiefel.class);
//        r.add(PolakRibiere.class);
//        r.add(Daniel.class);
        r.add(FletcherReeves.class);
//        r.add(ConjugateDescendent.class);
//        r.add(LiuStorey.class);
//        r.add(DaiYuan.class);
//        r.add(HagerZhang.class);
        r.add(BFGS.class);
//        r.add(DFP.class);
//        r.add(Broyden.class);
        return r;
    }

    private List<Class> fieldstoevaluate() {
        java.util.Vector r = new java.util.Vector();
//        r.add(Complex.class);
//        r.add(Float64.class);
//        r.add(FloatingPoint.class);
       // r.add(Rational.class);
        r.add(Real.class);
        return r;
    }

    
    private boolean eval(MinimizationMethod method ,NumberFactory numberfactory) throws Exception {

        if (!mmtoevaluate().contains(method.getClass())) {
            return true;
        }

        if (!fieldstoevaluate().contains(numberfactory.makeNumber(0.0).getClass())) {
            return true;
        }

        NumberFactoryableFunction function = getFunction();
        if (function == null) {
            return true;
        }
        function.setNumberfactory(numberfactory);

        Vector initialpoint = _getInitialpoint(numberfactory);

        QuadraticNorm norm = new QuadraticNorm();

        //MinimizationMethod method = getMethod();
        method.setNumberfactory(numberfactory);
        NewtonRaphson linesearch = new NewtonRaphson();
        linesearch.setMaxiterations(getMaxlineiterations());
        linesearch.setPrecision(numberfactory.makeNumber(getPrecision()));
        method.setLinesearch(linesearch);
        method.setInitialPoint(initialpoint);
        method.setMaxiterations(getMaxiterations());
        method.setNorm(norm);
        setParameters(method);
        method.setPrecision(_getPrecision(numberfactory));

        long begin = System.currentTimeMillis();

        Vector solutionfound = method.minimize(function);

        long end = System.currentTimeMillis();

        Field solutionmin = function.evaluate(solutionfound);

        if (solutionmin instanceof Float64) {
            assertFalse(solutionmin.equals(Float64.valueOf(Double.NaN)));
        }
        if (solutionmin instanceof Real) {
            assertFalse(solutionmin.equals(Real.valueOf(Double.NaN)));
        }
        //Vector expectedsolution = _getExpectedsolution(numberfactory);

        Field expectedmin = _getExpectedmin(numberfactory);


        //System.out.println("solution found: " + solutionmin.toString());
        //System.out.println("expected solution: " + expectedmin.toString());

        //Field acceptablediff = expectedmin.times(expectedmin);

        Field a = (Field) expectedmin.times(expectedmin);
        Field b = (Field) solutionmin.times(solutionmin);

        Field c = (Field) a.plus(b);

        Field acceptablediff = c;


        Comparator comparator = new Comparator();

        System.out.println("Function: " + getClass().toString() + "Method: " + method.getClass().toString() + ",NF: " + numberfactory.getClass().toString() + ", iter: " + method.getIterationscount() + ",milis: " + (end-begin));

        assertTrue("expected min: " + expectedmin.toString() + ", foundmin = " + solutionmin.toString(), comparator.compare(acceptablediff, _getAcceptablediff(numberfactory)) == -1);

        

        return true;


    }


    private Newton makeNewton(NumberFactory numberfactory) {
        Newton newton = new Newton();
        newton.setParameters(numberfactory.makeNumber(0.9));
        return newton;
    }

    private MinimizationMethod makeHestenesStiefel(NumberFactory complexNumberFactory) {
        return new HestenesStiefel();
    }

    private MinimizationMethod makePolakRibiere(NumberFactory complexNumberFactory) {
        return new PolakRibiere();
    }

    private MinimizationMethod makeDaniel(NumberFactory complexNumberFactory) {
        return new Daniel();
    }
    
    private MinimizationMethod makeFletcherReeves(NumberFactory complexNumberFactory) {
        return new FletcherReeves();
    }
    private MinimizationMethod makeConjugateDescendent(NumberFactory complexNumberFactory) {
        return new ConjugateDescendent();
    }
    private MinimizationMethod makeLiuStorey(NumberFactory complexNumberFactory) {
        return new LiuStorey();
    }
    private MinimizationMethod makeDaiYuan(NumberFactory complexNumberFactory) {
        return new DaiYuan();
    }
    private MinimizationMethod makeHagerZhang(NumberFactory complexNumberFactory) {
        return new HagerZhang();
    }
    private MinimizationMethod makeBFGS(NumberFactory complexNumberFactory) {
        return new BFGS();
    }
    private MinimizationMethod makeBroyden(NumberFactory complexNumberFactory) {
        return new Broyden();
    }

    private MinimizationMethod makeDFP(NumberFactory realNumberFactory) {
        return new DFP();
    }

    @Test
    public void newton_complex_test() throws Exception {
          assertTrue(eval(makeNewton(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void newton_float64_test() throws Exception {
          assertTrue(eval(makeNewton(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void newton_floatingpoint_test() throws Exception {
          assertTrue(eval(makeNewton(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void newton_rational_test() throws Exception {
          assertTrue(eval(makeNewton(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void newton_real_test() throws Exception {
          assertTrue(eval(makeNewton(new RealNumberFactory()),new RealNumberFactory()));
    }

    // HestenesStiefel

    @Test
    public void HestenesStiefel_complex_test() throws Exception {
          assertTrue(eval(makeHestenesStiefel(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void HestenesStiefel_float64_test() throws Exception {
          assertTrue(eval(makeHestenesStiefel(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void HestenesStiefel_floatingpoint_test() throws Exception {
          assertTrue(eval(makeHestenesStiefel(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void HestenesStiefel_rational_test() throws Exception {
          assertTrue(eval(makeHestenesStiefel(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void HestenesStiefel_real_test() throws Exception {
          assertTrue(eval(makeHestenesStiefel(new RealNumberFactory()),new RealNumberFactory()));
    }

    // PolakRibiere

    @Test
    public void PolakRibiere_complex_test() throws Exception {
          assertTrue(eval(makePolakRibiere(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void PolakRibiere_float64_test() throws Exception {
          assertTrue(eval(makePolakRibiere(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void PolakRibiere_floatingpoint_test() throws Exception {
          assertTrue(eval(makePolakRibiere(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void PolakRibiere_rational_test() throws Exception {
          assertTrue(eval(makePolakRibiere(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void PolakRibiere_real_test() throws Exception {
          assertTrue(eval(makePolakRibiere(new RealNumberFactory()),new RealNumberFactory()));
    }

    // Daniel

    @Test
    public void Daniel_complex_test() throws Exception {
          assertTrue(eval(makeDaniel(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void Daniel_float64_test() throws Exception {
          assertTrue(eval(makeDaniel(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void Daniel_floatingpoint_test() throws Exception {
          assertTrue(eval(makeDaniel(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void Daniel_rational_test() throws Exception {
          assertTrue(eval(makeDaniel(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void Daniel_real_test() throws Exception {
          assertTrue(eval(makeDaniel(new RealNumberFactory()),new RealNumberFactory()));
    }

    // FletcherReeves

    @Test
    public void FletcherReeves_complex_test() throws Exception {
          assertTrue(eval(makeFletcherReeves(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void FletcherReeves_float64_test() throws Exception {
          assertTrue(eval(makeFletcherReeves(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void FletcherReeves_floatingpoint_test() throws Exception {
          assertTrue(eval(makeFletcherReeves(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void FletcherReeves_rational_test() throws Exception {
          assertTrue(eval(makeFletcherReeves(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void FletcherReeves_real_test() throws Exception {
          assertTrue(eval(makeFletcherReeves(new RealNumberFactory()),new RealNumberFactory()));
    }

    // ConjugateDescendent

    @Test
    public void ConjugateDescendent_complex_test() throws Exception {
          assertTrue(eval(makeConjugateDescendent(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void ConjugateDescendent_float64_test() throws Exception {
          assertTrue(eval(makeConjugateDescendent(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void ConjugateDescendent_floatingpoint_test() throws Exception {
          assertTrue(eval(makeConjugateDescendent(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void ConjugateDescendent_rational_test() throws Exception {
          assertTrue(eval(makeConjugateDescendent(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void ConjugateDescendent_real_test() throws Exception {
          assertTrue(eval(makeConjugateDescendent(new RealNumberFactory()),new RealNumberFactory()));
    }

    // LiuStorey

    @Test
    public void LiuStorey_complex_test() throws Exception {
          assertTrue(eval(makeLiuStorey(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void LiuStorey_float64_test() throws Exception {
          assertTrue(eval(makeLiuStorey(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void LiuStorey_floatingpoint_test() throws Exception {
          assertTrue(eval(makeLiuStorey(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void LiuStorey_rational_test() throws Exception {
          assertTrue(eval(makeLiuStorey(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void LiuStorey_real_test() throws Exception {
          assertTrue(eval(makeLiuStorey(new RealNumberFactory()),new RealNumberFactory()));
    }

    // DaiYuan

    @Test
    public void DaiYuan_complex_test() throws Exception {
          assertTrue(eval(makeDaiYuan(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void DaiYuan_float64_test() throws Exception {
          assertTrue(eval(makeDaiYuan(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void DaiYuan_floatingpoint_test() throws Exception {
          assertTrue(eval(makeDaiYuan(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void DaiYuan_rational_test() throws Exception {
          assertTrue(eval(makeDaiYuan(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void DaiYuan_real_test() throws Exception {
          assertTrue(eval(makeDaiYuan(new RealNumberFactory()),new RealNumberFactory()));
    }

    // HagerZhang

    @Test
    public void HagerZhang_complex_test() throws Exception {
          assertTrue(eval(makeHagerZhang(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void HagerZhang_float64_test() throws Exception {
          assertTrue(eval(makeHagerZhang(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void HagerZhang_floatingpoint_test() throws Exception {
          assertTrue(eval(makeHagerZhang(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void HagerZhang_rational_test() throws Exception {
          assertTrue(eval(makeHagerZhang(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void HagerZhang_real_test() throws Exception {
          assertTrue(eval(makeHagerZhang(new RealNumberFactory()),new RealNumberFactory()));
    }

    // BFGS

    @Test
    public void BFGS_complex_test() throws Exception {
          assertTrue(eval(makeBFGS(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void BFGS_float64_test() throws Exception {
          assertTrue(eval(makeBFGS(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void BFGS_floatingpoint_test() throws Exception {
          assertTrue(eval(makeBFGS(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void BFGS_rational_test() throws Exception {
          assertTrue(eval(makeBFGS(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void BFGS_real_test() throws Exception {
          assertTrue(eval(makeBFGS(new RealNumberFactory()),new RealNumberFactory()));
    }

    // DFP

    @Test
    public void DFP_complex_test() throws Exception {
          assertTrue(eval(makeDFP(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void DFP_float64_test() throws Exception {
          assertTrue(eval(makeDFP(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void DFP_floatingpoint_test() throws Exception {
          assertTrue(eval(makeDFP(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void DFP_rational_test() throws Exception {
          assertTrue(eval(makeDFP(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void DFP_real_test() throws Exception {
          assertTrue(eval(makeDFP(new RealNumberFactory()),new RealNumberFactory()));
    }

    // Broyden

    @Test
    public void Broyden_complex_test() throws Exception {
          assertTrue(eval(makeBroyden(new ComplexNumberFactory()),new ComplexNumberFactory()));
    }

    @Test
    public void Broyden_float64_test() throws Exception {
          assertTrue(eval(makeBroyden(new Float64NumberFactory()),new Float64NumberFactory()));
    }

    @Test
    public void Broyden_floatingpoint_test() throws Exception {
          assertTrue(eval(makeBroyden(new FloatingPointNumberFactory()),new FloatingPointNumberFactory()));
    }

    @Test
    public void Broyden_rational_test() throws Exception {
          assertTrue(eval(makeBroyden(new RationalNumberFactory()),new RationalNumberFactory()));
    }

    @Test
    public void Broyden_real_test() throws Exception {
          assertTrue(eval(makeBroyden(new RealNumberFactory()),new RealNumberFactory()));
    }

    
    
}