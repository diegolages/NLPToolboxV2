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


package br.ufrj.cos.nlptoolbox.restrictions.barrier;

import br.ufrj.cos.nlptoolbox.restrictions.RestrictionMinimizationMethod;
import br.ufrj.cos.nlptoolbox.restrictions.EqualityConstraintsFunction;
import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.exceptions.NoMinFoundException;
import br.ufrj.cos.nlptoolbox.methods.ConstrainedMinimizationMethod;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenaltyCompositeFunction;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class BarrierMinimizationMethod<F extends Field<F>> extends RestrictionMinimizationMethod implements ConstrainedMinimizationMethod<F> {
    
    private F cfactor=null;
    private F initialc = null;
    private F lambda = null;
    F zero = null;
    
    Comparator comparator = new Comparator();
    
    public BarrierMinimizationMethod(MinimizationMethod<F> mm, Function[] inequalityrestrictions, Function<F>[] equalityrestrictions) {
        super(mm,inequalityrestrictions, equalityrestrictions);
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
        
        F mi = initialc;

        boolean haspassedonce=false;

        zero = (F) numberfactory.makeNumber(0);

        Vector ret = null;

        boolean comparatorcondition = true;
        
        do {

            

            //err = norm.norm(xk.minus(oldxk));
            
            Vector r2 = minimizationmethod.minimize(
                new EqualityConstraintsFunction(
                    new BarrierCompositeFunction(f,getInequalityrestrictions(), mi, numberfactory),
                    getEqualityrestrictions(),
                    lambda,
                    numberfactory
                    )
                 );

            if (!hasNan(r2)) {
                if (checkRestrictions(r2)) {
                    haspassedonce = true;
                    minimizationmethod.setInitialPoint(r2);
                    ret = r2;
                    comparatorcondition = comparator.compare(getErr(), precision) > 0;
                } else {
                    comparatorcondition = true;
                }
            } else {
                if (ret != null) {
                    comparatorcondition = false;
                }
            }

            mi = mi.times(cfactor);
            
            nits++;
        } while (comparatorcondition && nits < maxiterations);

        if (!haspassedonce) {
            throw new NoMinFoundException("Minimization did not find any feasible point");
        }

        return ret;
    }

    private boolean checkRestrictions(Vector<F> x) throws Exception {

        boolean ok = true;

        Function<F>[] l = getInequalityrestrictions();

        for (Function f : l) {
            Field y = f.evaluate(x);

            if (comparator.compare(zero, y) != 1) {
                ok = false;
            }
        }

        return ok;
    }

    private boolean hasNan(Vector<F> r2) {

        boolean hasnan = false;

        for (int i=0;i<r2.getDimension();i++) {
            F f = r2.get(i);
            if (f instanceof Number) {
                Number _f = (Number)f;
                if (Double.isNaN(_f.doubleValue())) {
                    hasnan = true;
                }
            }
        }

        return hasnan;
    }

    /**
     * @return the lambda
     */
    public F getLambda() {
        return lambda;
    }

    /**
     * @param lambda the lambda to set
     */
    public void setLambda(F lambda) {
        this.lambda = lambda;
    }


    
}
