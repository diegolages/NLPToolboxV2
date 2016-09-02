/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

import br.ufrj.cos.nlptoolbox.Function;

/**
 *
 * @author lages
 */
public class PlaneBarrierRestriction  {

    Function function;
    private double limit;
    private boolean lowerbound;
    int dimension = -1;

    public String toString() {
        return function.toString() + (lowerbound ? ">=" : "<=") + limit;
    }

    public PlaneBarrierRestriction() {}

    public PlaneBarrierRestriction(int _d, Function f, double l, boolean b) {
        dimension = _d;
        function = f;
        limit = l;
        lowerbound = b;
    }

    public Function getFunction() throws Exception {
        return function;
    }


    /**
     * @param functionstr the functionstr to set
     */
    public void setFunction(Function f) {
        this.function = f;
    }

    /**
     * @return the lowerbound
     */
    public boolean isLowerbound() {
        return lowerbound;
    }

    /**
     * @param lowerbound the lowerbound to set
     */
    public void setLowerbound(boolean lowerbound) {
        this.lowerbound = lowerbound;
    }

//    /**
//     * @return the limit
//     */
    public double getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

}
