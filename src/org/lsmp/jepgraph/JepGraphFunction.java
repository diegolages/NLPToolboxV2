/*
Created 21-Jul-2006 - Richard Morris
*/
package org.lsmp.jepgraph;

import java.awt.geom.Point2D;

import org.lsmp.djep.djep.DJep;
import org.lsmp.djep.djep.DVariable;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 * @author Richard Morris
 *
 */
public class JepGraphFunction implements GraphFunctionI {
	DJep j;
	Node expression;
	Node derivative;
	DVariable var;
	/**
	 * 
	 */
	public JepGraphFunction(DJep j,Node expression,DVariable var) throws ParseException {
		this.j = j;
		this.expression = expression;
		this.var = var;
		this.derivative = j.differentiate(expression,var.getName());
	}

	/* (non-Javadoc)
	 * @see org.lsmp.jepgraph.GraphFunctionI#y(double)
	 */
	public double y(double x) {
		var.setValue(new Double(x));
		double res;
		try {
			Object o = j.evaluate(expression);
			res = ((Double) o).doubleValue();
		}
		catch(ParseException e) { res = Double.NaN;}
		return res;
	}

	public Point2D yAsPoint(double x) {
		double y=y(x);
		return new Point2D.Double(x,y);
	}

	public double dydx(double x) {
		var.setValue(new Double(x));
		double res;
		try {
			Object o = j.evaluate(derivative);
			res = ((Double) o).doubleValue();
		}
		catch(ParseException e) { res = Double.NaN;}
		return res;
	}

}
