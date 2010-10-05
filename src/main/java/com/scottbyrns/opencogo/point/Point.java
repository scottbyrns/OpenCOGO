package com.scottbyrns.opencogo.point;

/**
 * This class creates an object that stores the X, Y, and Z (optional)
 * coordinates of a point.
 * @author Scott Byrns
 * @created 2010-08-10
 * @version 1.0
 */
public class Point {
	private float[] coordinates;
	/**
	 * Create a new point object with X and Y coordinates.
	 */
	public Point (float x, float y) {
		coordinates = new float[] {x, y, 0.0f};
	}
	/**
	 * Create a new point object with X, Y, and Z coordinates.
	 */
	public Point (float x, float y, float z) {
		coordinates = new float[] {x, y, z};
	}
    /**
     * Create a new point from an existing point.
     */
    public Point (Point point) {
        coordinates = new float[] {point.getX(), point.getY(), point.getZ()};
    }
	/**
	 * Return the X coordinate of our point.
	 */
	public float getX () {
		return coordinates[0];
	}
	/**
	 * Return the Y coordinate of our point.
	 */
	public float getY () {
		return coordinates[1];
	}
	/**
	 * Return the Z coordinate of our point.
	 * If Z has not been set 0.0f is returned.
	 */
	public float getZ () {
		return coordinates[2];
	}
	/**
	 * Set the X coordinate of our point.
	 */
	public void setX (float x) {
		coordinates[0] = x;
	}
	/**
	 * Set the Y coordinate of our point.
	 */
	public void setY (float y) {
		coordinates[1] = y;
	}
	/**
	 * Set the Z coordinate of our point.
	 */
	public void setZ (float z) {
		coordinates[2] = z;
	}
	/**
	 * Return a float[] representation of the point.
	 */
	public float[] toArray () {
		return coordinates;
	}
	/**
	 * Set the coordinates of the point with values
	 * from a float[] in order from X to Z.
	 */
	public void fromArray (float[] coordinates) {
		int len;
		if (coordinates.length >= 3) {
			len = 3;
		}
		else {
			len = coordinates.length;
		}
		for (int i = 0; i < len; i += 1) {
			this.coordinates[i] = coordinates[i];
		}
	}
}