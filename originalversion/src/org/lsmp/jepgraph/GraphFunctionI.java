/*
Created 21-Jul-2006 - Richard Morris
*/
package org.lsmp.jepgraph;

import java.awt.geom.Point2D;

public interface GraphFunctionI {
	double y(double x);
	Point2D yAsPoint(double x);
	double dydx(double x);
}
