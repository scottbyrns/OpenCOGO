package com.scottbyrns.opencogo.point;

import java.util.Vector;

public class PointList {
	
	public Vector<Point> points = null;
	/**
	 * Create a PointList from a Point[]
	 */
	public PointList (Point[] points) {
		createPointsVector();
		for (int i = 0; i < points.length; i += 1) {
			this.points.addElement(points[i]);
		}
	}
	/**
	 * Create an emptry PointList
	 */
	public PointList () {
		createPointsVector();
	}
	/**
	 * Add a point to the point list.
	 */
	public void addPoint (Point point) {
		this.points.addElement(point);
	}
	/**
	 * Returns the number of points in the PointList
	 */
	public int size () {
		return this.points.size();
	}
	/**
	 * Return the point at the given index.
	 */
	public Point getPoint(int index) {
		return this.points.elementAt(index);
	}
	
	public Point[] toArray () {
        Point[] outputPoints = new Point[size()];
        for (int i = 0; i < size(); i += 1) {
            outputPoints[i] = getPoint(i);
        }
        return outputPoints;
//		return (Point[])points.toArray();
	}
	/**
	 * Create an instance of Vector<Point> and assign
	 * it to this.points.
	 */
	private void createPointsVector () {
		this.points = new Vector<Point>();
	}
}