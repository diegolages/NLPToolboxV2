/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3Factory;

/**
 *
 * @author lages
 */
public class ExpV3PlaneBarrierRestriction extends PlaneBarrierRestriction {

    private String functionstr;

    public String toString() {
        return functionstr + (isLowerbound() ? ">=" : "<=") + getLimit();
    }

    public ExpV3PlaneBarrierRestriction() {}

    public ExpV3PlaneBarrierRestriction(int _d, String s, double l, boolean b) throws Exception {
        dimension = _d;
        functionstr = s;
        setLimit(l);
        setLowerbound(b);
        setFunction(ExpressionFunctionV3Factory.makeExpressionFunctionV3(functionstr,dimension));
    }

    /**
     * @param functionstr the functionstr to set
     */
    public void setFunctionstr(String functionstr) {
        this.functionstr = functionstr;
    }

}
