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


package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PlaneBarrierMinimizationMethod<F extends Field<F>> implements MinimizationMethod<F> {

    protected Vector<F>          initialpoint = null;
    protected F[]                parameters = null;
    protected F                  precision = null;
    protected long               maxiterations = -1;
    protected Norm<F>            norm = null;
    protected NumberFactory<F>   numberfactory = null;

    protected MinimizationMethod<F> minimizationmethod = null;

    String  variable=null;
    int     limit;
    boolean lowerbound;

    private F cfactor=null;
    private F initialc = null;
    F zero = null;
    
    Comparator comparator = new Comparator();
    
    public PlaneBarrierMinimizationMethod(MinimizationMethod<F> mm, String _variable, int _limit, boolean _lowerbound) {
        this.minimizationmethod = mm;
        variable = _variable;
        limit = _limit;
        lowerbound = _lowerbound;
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
            cfactor = numberfactory.makeNumber(2);
        }
        
        F mi = initialc;

        boolean haspassedonce=false;

        zero = (F) numberfactory.makeNumber(0);

        Vector ret = null;

        boolean comparatorcondition = true;
        
        do {

            //err = norm.norm(xk.minus(oldxk));
            
            Vector r2 = minimizationmethod.minimize(new PlaneBarrierCompositeFunction(f, initialpoint.getDimension(), variable, limit, lowerbound, mi, numberfactory));

            if (!hasNan(r2)) {

                r2 = approximateInteger(r2);

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
            throw new Exception("Minimization did not find any feasible point");
        }

        return ret;
    }

    private Vector<F> approximateInteger(Vector<F> vx) {

        String xvar = variable.substring(1);

        int n = Integer.parseInt(xvar)-1;

        F x = vx.get(n);

        List<F> list = new java.util.Vector();

        if (numberfactory.checkInteger(x, precision)) {
            int rounded = numberfactory.round(x);

            for (int i=0;i<vx.getDimension();i++) {
                list.add(vx.get(i));
            }

            list.set(n, numberfactory.makeNumber(rounded));
            
        } else {
            return vx;
        }

        return DenseVector.valueOf(list);

    }


    private boolean checkRestrictions(Vector<F> vx) throws Exception {

        boolean ok = true;

        String xvar = variable.substring(1);

        int n = Integer.parseInt(xvar)-1;

        F x = vx.get(n);

        int lb = 1;
        if (lowerbound) {
            lb = -1;
        }

        int c = comparator.compare(numberfactory.makeNumber(limit), x);

        if (c != lb && c != 0) {
            ok = false;
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

    @Override
    public void setInitialPoint(Vector<F> variables) {
        initialpoint = variables;
        minimizationmethod.setInitialPoint(variables);
    }

    @Override
    public void setParameters(F... p) {
        parameters = p;
        minimizationmethod.setParameters(p);
    }

    @Override
    public void setPrecision(F f) {
        precision = f;
        minimizationmethod.setPrecision(f);
    }

    @Override
    public void setMaxiterations(long i) {
        maxiterations = i;
        minimizationmethod.setMaxiterations(i);
    }

    @Override
    public void setNorm(Norm<F> n) {
        norm = n;
        minimizationmethod.setNorm(n);
    }

    @Override
    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
        minimizationmethod.setNumberfactory(numberfactory);
    }

    @Override
    public long getIterationscount() {
        return minimizationmethod.getIterationscount();
    }

    @Override
    public F getErr() {
        return minimizationmethod.getErr();
    }

    @Override
    public void setLinesearch(LineSearch linesearch) {
        minimizationmethod.setLinesearch(linesearch);
    }

    @Override
    public void resetIterationscount() {

    }

}
