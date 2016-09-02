/*
Created 21-Jul-2006 - Richard Morris
*/
package org.lsmp.jepgraph;

import java.awt.geom.Point2D;

public class PolarDomain implements GraphDomainI {
	double rmax;
	public PolarDomain(double rmax) {
		this.rmax = rmax;
	}

	public double getXmin() {return 0;}
	public double getXmax() {return rmax;}
	public double getYmin() {return -Math.PI;}
	public double getYmax() {return Math.PI;}

	public boolean isPlottablePoint(Point2D point) {
		double x = point.getX();
		double y = point.getX();
		double r = Math.hypot(x,y);
		return ( r>= 0.0 && r <= rmax);
	}

	public Point2D sourceToTarget(Point2D src) {
		double r = src.getX();
		double th = src.getY();
		double x = r * Math.cos(th);
		double y = r * Math.sin(th);
		return new Point2D.Double(x,y);
	}

	public Point2D targetToSource(Point2D tgt) {
		double x = tgt.getX();
		double y = tgt.getY();
		double r = Math.hypot(x,y);
		double th =Math.atan2(y,x);
		return new Point2D.Double(r,th);
	}

	public boolean isLegal(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		double r = Math.hypot(x,y);
		return r>=0.0;
	}

	public Point2D firstDerivative(Point2D vec) {
		return vec;
	}
}
