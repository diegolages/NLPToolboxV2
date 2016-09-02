/*
Created 21-Jul-2006 - Richard Morris
*/
package org.lsmp.jepgraph;

import java.awt.geom.Point2D;

public class CartesianDomain implements GraphDomainI {
	double xmin, xmax, ymin, ymax;
	public CartesianDomain(double xmin,double xmax,double ymin,double ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}

	public double getXmin() {return xmin;}
	public double getXmax() {return xmax;}
	public double getYmin() {return ymin;}
	public double getYmax() {return ymax;}

	public boolean isPlottablePoint(Point2D point) {
		double x = point.getX();
		double y = point.getX();
		return ( x>= xmin && x <= xmax && y>= ymin && y>=ymax);
	}

	public Point2D sourceToTarget(Point2D src) {
		return src;
	}

	public Point2D targetToSource(Point2D src) {
		return src;
	}

	public boolean isLegal(Point2D p) {
		return true;
	}

	public Point2D firstDerivative(Point2D vec) {
		return vec;
	}
}
