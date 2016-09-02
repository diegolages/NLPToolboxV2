/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

/**
 *
 * @author lages
 */
public class PlaneBarrierRestriction_1 {


    private String functionstr;
    private double limit;
    private boolean lowerbound;

    public String toString() {
        return functionstr + (lowerbound ? ">=" : "<=") + limit;
    }

    public PlaneBarrierRestriction_1(String s, double l, boolean b) {
        functionstr = s;
        limit = l;
        lowerbound = b;
    }

    /**
     * @return the functionstr
     */
    public String getFunctionstr() {
        return functionstr;
    }

    /**
     * @param functionstr the functionstr to set
     */
    public void setFunctionstr(String functionstr) {
        this.functionstr = functionstr;
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

    /**
     * @return the limit
     */
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
