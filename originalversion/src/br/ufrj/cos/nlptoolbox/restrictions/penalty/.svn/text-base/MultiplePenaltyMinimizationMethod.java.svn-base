/*
 *  Copyright (c) 2008, Diego Lages
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package br.ufrj.cos.nlptoolbox.restrictions.penalty;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.exceptions.InitialPointNotFeasableException;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.ConstrainedMinimizationMethod;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.barrier.ParMultipleFractionBarrierCompositeFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.ParMultiplePlaneBarrierCompositeFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.ExpV3PlaneBarrierRestriction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.PlaneBarrierRestriction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.PlusFunction;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.type.Complex;

/**
 *
 * @author Diego
 */
public class MultiplePenaltyMinimizationMethod<F extends Field<F>> implements ConstrainedMinimizationMethod<F> {

    protected Vector<F> initialpoint = null;
    protected F[] parameters = null;
    protected F precision = null;
    protected F lambda = null;
    protected long maxiterations = -1;
    protected Norm<F> norm = null;
    protected NumberFactory<F> numberfactory = null;//new Float64NumberFactory();
    protected MinimizationMethod minimizationmethod = null;
    private F cfactor = null;
    private F initialc = null;
    F zero = null;
    List<PlaneBarrierRestriction> functions = null;
    Comparator comparator = new Comparator();

    Float64 reterr = null;

    public MultiplePenaltyMinimizationMethod(MinimizationMethod mm, List<PlaneBarrierRestriction> fs) {
        this.minimizationmethod = mm;
        functions = fs;
    }

    public F getCfactor() {
        return cfactor;
    }

    public void setCfactor(F c) {
        this.cfactor = c;
    }

    public F getInitialc() {
        return initialc;
    }

    public void setInitialc(F initialc) {
        this.initialc = initialc;
    }

    @Override
    public Vector minimize(Function f) throws Exception {

        long nits = 0;

        if (initialc == null) {
            initialc = numberfactory.makeNumber(1);
        }
        if (cfactor == null) {
            cfactor = numberfactory.makeNumber(2);//Float64.valueOf(2);
        }

        F mi = initialc;

        if (!checkRestrictions(initialpoint)) {
            throw new InitialPointNotFeasableException("Initial Point is not feasable");
        }
        
        

        boolean haspassedonce = false;

        zero = numberfactory.makeNumber(0);//(Float64) Float64.valueOf(0);

        Vector ret = null;

        boolean comparatorcondition = true;

        Vector r2 = null;
        Vector oldr2 = null;


        do {

            Field ff = (Field)mi;
            Float64 f64 = (Float64)ff;

            //err = norm.norm(xk.minus(oldxk));


            oldr2 = r2;

            minimizationmethod.resetIterationscount();

            PlusFunction pf = new PlusFunction(
                    new ParMultipleFractionBarrierCompositeFunction(f, initialpoint.getDimension(), functions, f64.doubleValue(), numberfactory),
                    new ParMultiplePenaltyCompositeFunction(f, initialpoint.getDimension(), functions, f64.doubleValue(), numberfactory)
                    );


           // ParMultiplePenaltyCompositeFunction pf = new ParMultiplePenaltyCompositeFunction(f, initialpoint.getDimension(), functions, f64.doubleValue(), numberfactory);

             r2 = minimizationmethod.minimize(pf);

            if (!hasNan(r2)) {

                //r2 = approximateInteger(r2);

                if (checkRestrictions(r2)) {
                    haspassedonce = true;
                    minimizationmethod.setInitialPoint(r2);
                    ret = r2;
                    reterr = (Float64)minimizationmethod.getErr();
                    comparatorcondition = comparator.compare(getErr(), precision) > 0;
                    //comparatorcondition = true;
                } else {
                    comparatorcondition = true;
                }
            } else {
                if (ret != null) {
                    comparatorcondition = false;
                } else {
                    ret = oldr2;
                    comparatorcondition = false;
                }
            }

            mi = mi.times(cfactor);

            nits++;
        } while (comparatorcondition && nits < maxiterations);

//        if (!haspassedonce) {
//            throw new Exception("Minimization did not find any feasible point " + r2);
//        }

        return ret;
    }
//
//    private Vector<Float64> approximateInteger(Vector<Float64> vx) {
//
//        String xvar = variable.substring(1);
//
//        int n = Integer.parseInt(xvar)-1;
//
//        Float64 x = vx.get(n);
//
//        List<Float64> list = new java.util.Vector();
//
//        if (numberfactory.checkInteger(x, precision)) {
//            int rounded = numberfactory.round(x);
//
//            for (int i=0;i<vx.getDimension();i++) {
//                list.add(vx.get(i));
//            }
//
//            list.set(n, numberfactory.makeNumber(rounded));
//
//        } else {
//            return vx;
//        }
//
//        return DenseVector.valueOf(list);
//
//    }

    private boolean checkRestrictions(Vector vx) throws Exception {

        boolean ok = true;

        for (PlaneBarrierRestriction pbr : functions) {

//            DJep jep = new DJep();
//
//            jep.addStandardConstants();
//            jep.addStandardFunctions();
//            jep.addComplex();
//            jep.setAllowUndeclared(true);
//            jep.setAllowAssignment(true);
//            jep.setImplicitMul(true);
//
//            // Sets up standard rules for differentiating sin(x) etc.
//            jep.addStandardDiffRules();
//
//
//            for (int i = 0; i < initialpoint.getDimension(); i++) {
//                Float64 f64 = (Float64) vx.get(i);
//                jep.addVariable("x" + (i + 1), f64.doubleValue());
//            }
//
//            Node n = jep.parse(pbr.getFunctionstr());

//            Double obj = (Double) jep.evaluate(n);

            Float64 f64 = (Float64)pbr.getFunction().evaluate(vx);
            double obj = f64.doubleValue();


            if (pbr.isLowerbound()) {

                if (obj < pbr.getLimit()) {
                    ok = false;
                }

            } else {

                if (obj > pbr.getLimit()) {
                    ok = false;
                }

            }

        }

        return ok;
    }

    private boolean hasNan(Vector<Float64> r2) {

        boolean hasnan = false;

        for (int i = 0; i < r2.getDimension(); i++) {
            Float64 f = r2.get(i);
            if (f instanceof Number) {
                Number _f = (Number) f;
                if (Double.isNaN(_f.doubleValue())) {
                    hasnan = true;
                }
            }
        }

        return hasnan;
    }

    public void setInitialPoint(Vector variables) {
        initialpoint = variables;
        minimizationmethod.setInitialPoint(variables);
    }

    public void setParameters(Field... p) {
        parameters = (F[]) p;
        minimizationmethod.setParameters(p);
    }

    public void setPrecision(Field f) {
        precision = (F) f;
        minimizationmethod.setPrecision(f);
    }

    public void setMaxiterations(long i) {
        maxiterations = i;
        minimizationmethod.setMaxiterations(i);
    }

    public void setNorm(Norm n) {
        norm = n;
        minimizationmethod.setNorm(n);
    }

    public void setNumberfactory(NumberFactory numberfactory) {
        this.numberfactory = numberfactory;
        minimizationmethod.setNumberfactory(numberfactory);
    }

    public long getIterationscount() {
        return minimizationmethod.getIterationscount();
    }

    public Float64 getErr() {
        if (reterr == null) {
            return Float64.valueOf(Double.NaN);
        }
        return reterr; //(Float64) minimizationmethod.getErr();
    }

    @Override
    public void setLinesearch(LineSearch linesearch) {
        minimizationmethod.setLinesearch(linesearch);
    }

    @Override
    public F getLambda() {
        return lambda;
    }

    @Override
    public void setLambda(F _l) {
        lambda = _l;
    }

    @Override
    public void resetIterationscount() {
        
    }

}
