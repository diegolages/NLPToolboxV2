/*
Created 21-Jul-2006 - Richard Morris
*/
package org.lsmp.jepgraph;

import java.awt.geom.Point2D;

/**
 * Defines the space where a graph is plotted
 * @author Richard Morris
 *
 */
public interface GraphSpaceI {
	/** Convert a source point to target points */
	public Point2D sourceToTarget(Point2D src);
	/** Convert a source point to target points */
	public Point2D targetToSource(Point2D src);
	/** Tests whether a point in legal */
	public boolean isLegal(Point2D p);
	/** finds the first derivative of a a vector */
	public Point2D firstDerivative(Point2D vec);
}
