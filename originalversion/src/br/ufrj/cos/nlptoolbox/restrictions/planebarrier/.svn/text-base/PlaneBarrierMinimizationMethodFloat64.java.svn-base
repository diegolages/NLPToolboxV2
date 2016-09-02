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
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PlaneBarrierMinimizationMethodFloat64 implements MinimizationMethod {

    protected Vector<Float64>          initialpoint = null;
    protected Float64[]                parameters = null;
    protected Float64                  precision = null;
    protected long               maxiterations = -1;
    protected Norm<Float64>            norm = null;
    protected NumberFactory<Float64>   numberfactory = new Float64NumberFactory();

    protected MinimizationMethod minimizationmethod = null;

    String  variable=null;
    double     limit;
    boolean lowerbound;

    private Float64 cfactor=null;
    private Float64 initialc = null;
    Float64 zero = null;
    
    Comparator comparator = new Comparator();
    
    public PlaneBarrierMinimizationMethodFloat64(MinimizationMethod mm, String _variable, double _limit, boolean _lowerbound) {
        this.minimizationmethod = mm;
        variable = _variable;
        limit = _limit;
        lowerbound = _lowerbound;
    }

    public Float64 getCfactor() {
        return cfactor;
    }

    public void setCfactor(Float64 c) {
        this.cfactor = c;
    }

    public Float64 getInitialc() {
        return initialc;
    }

    public void setInitialc(Float64 initialc) {
        this.initialc = initialc;
    }

    @Override
    public Vector minimize(Function f) throws Exception {
        
        long nits = 0;

        if (initialc == null) {
            initialc = Float64.valueOf(1);
        }
        if (cfactor == null) {
            cfactor = Float64.valueOf(2);
        }
        
        Float64 mi = initialc;

        boolean haspassedonce=false;

        zero = (Float64) Float64.valueOf(0);

        Vector ret = null;

        boolean comparatorcondition = true;
        
        do {

            //err = norm.norm(xk.minus(oldxk));
            
            Vector r2 = minimizationmethod.minimize(new PlaneBarrierCompositeFunctionFloat64(f, initialpoint.getDimension(), variable, limit, lowerbound, mi));

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
            throw new Exception("Minimization did not find any feasible point (" + variable + ")");
        }

        return ret;
    }

    private Vector<Float64> approximateInteger(Vector<Float64> vx) {

        String xvar = variable.substring(1);

        int n = Integer.parseInt(xvar)-1;

        Float64 x = vx.get(n);

        List<Float64> list = new java.util.Vector();

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


    private boolean checkRestrictions(Vector<Float64> vx) throws Exception {

        boolean ok = true;

        String xvar = variable.substring(1);

        int n = Integer.parseInt(xvar)-1;

        Float64 x = vx.get(n);

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

    private boolean hasNan(Vector<Float64> r2) {

        boolean hasnan = false;

        for (int i=0;i<r2.getDimension();i++) {
            Float64 f = r2.get(i);
            if (f instanceof Number) {
                Number _f = (Number)f;
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
        parameters = (Float64[]) p;
        minimizationmethod.setParameters(p);
    }


    public void setPrecision(Field f) {
        precision = (Float64) f;
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
        return (Float64) minimizationmethod.getErr();
    }

    @Override
    public void setLinesearch(LineSearch linesearch) {
        minimizationmethod.setLinesearch(linesearch);
    }

@Override
    public void resetIterationscount() {

    }

}
