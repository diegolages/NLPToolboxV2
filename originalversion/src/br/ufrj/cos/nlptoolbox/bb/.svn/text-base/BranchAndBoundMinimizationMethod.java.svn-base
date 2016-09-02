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


package br.ufrj.cos.nlptoolbox.bb;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.PlaneBarrierMinimizationMethod;
import java.util.LinkedList;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class BranchAndBoundMinimizationMethod<F extends Field<F>> implements MinimizationMethod<F> {

    protected Vector<F>          initialpoint = null;
    protected F[]                parameters = null;
    protected F                  precision = null;
    protected long               maxiterations = -1;
    protected Norm<F>            norm = null;
    protected NumberFactory<F>   numberfactory = null;
    private LineSearch linesearch=null;

    protected MinimizationMethod<F> minimizationmethod = null;
    F zero = null;

    List<String> integervariables = new java.util.Vector();

    int [] intvariables=null;
    
    Comparator comparator = new Comparator();

    LinkedList<MinimizationMethod<F>> problemlist = new LinkedList();
    
    public BranchAndBoundMinimizationMethod(MinimizationMethod<F> mm, List<String> _integervariables) {

        minimizationmethod = mm;
        integervariables = _integervariables;

        intvariables = new int[integervariables.size()];

        int idx=0;
        for (String s : integervariables) {
            String x = s.substring(1);
            
            int n = Integer.parseInt(x);

            intvariables[idx] = n-1;

            idx++;
        }


    }


    @Override
    public Vector<F> minimize(Function<F> f) throws Exception {
        
        long nits = 0;
        
        boolean haspassedonce=false;

        zero = (F) numberfactory.makeNumber(0);

        boolean comparatorcondition = true;

        Vector<F> ret = null;

        //do {

        ret = minimizationmethod.minimize(f);


        comparatorcondition = checkInteger(ret);

        if (!comparatorcondition) {

            String s = integervariables.get(0);

            Vector<F> initialret = ret.copy();

            PlaneBarrierMinimizationMethod pbmm_less = new PlaneBarrierMinimizationMethod(minimizationmethod, s, 2, true);
            pbmm_less.setInitialPoint(initialret);
            pbmm_less.setLinesearch(this.linesearch);
            pbmm_less.setMaxiterations(maxiterations);
            pbmm_less.setNorm(norm);
            pbmm_less.setNumberfactory(numberfactory);
            pbmm_less.setParameters(parameters);
            pbmm_less.setPrecision(precision);

            problemlist.add(pbmm_less);

            //ret = pbmm_less.minimize(f);

            PlaneBarrierMinimizationMethod pbmm_more = new PlaneBarrierMinimizationMethod(minimizationmethod, s, 1, false);
            pbmm_more.setInitialPoint(initialret);
            pbmm_more.setLinesearch(this.linesearch);
            pbmm_more.setMaxiterations(maxiterations);
            pbmm_more.setNorm(norm);
            pbmm_more.setNumberfactory(numberfactory);
            pbmm_more.setParameters(parameters);
            pbmm_more.setPrecision(precision);

            problemlist.add(pbmm_more);

            //ret = pbmm_more.minimize(f);

        }

        for (MinimizationMethod<F> mm : problemlist) {

            for (MinimizationMethod<F> mm2 : problemlist) {

                

            }

        }
            
            //nits++;
        //} while (comparatorcondition && nits < maxiterations);

        return ret;
    }

    private boolean checkInteger(Vector<F> ret) {
        for (int n : intvariables) {
            F x = ret.get(n);
            boolean b = numberfactory.checkInteger(x,precision);
            if (!b) {
                return false;
            }
        }
        return true;
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

    /**
     * @return the linesearch
     */
    
    public LineSearch getLinesearch() {
        return linesearch;
    }

    /**
     * @param linesearch the linesearch to set
     */
    @Override
    public void setLinesearch(LineSearch linesearch) {
        minimizationmethod.setLinesearch(linesearch);
        this.linesearch = linesearch;
    }

//
//    @Override
//    public void setLinesearch(LineSearch linesearch) {
//        minimizationmethod.setLinesearch(linesearch);
//    }

    @Override
    public void resetIterationscount() {

    }

}
