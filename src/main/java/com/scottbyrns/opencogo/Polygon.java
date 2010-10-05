package com.scottbyrns.opencogo;

import com.scottbyrns.opencogo.point.Point;
import com.scottbyrns.opencogo.point.PointList;

/**
 * Created by IntelliJ IDEA.
 * User: scott
 * Date: Oct 4, 2010
 * Time: 5:23:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Polygon {

    PointList points = null;

    public Polygon () {
        this.points = new PointList();
    }

    public Polygon (PointList points) {
        this.points = points;
    }

    public void addPoint (float x, float y, float z) {
        addPoint(new Point(x, y, z));
    }

    public void addPoint (float x, float y) {
        addPoint(x, y, 0.0f);
    }

    public void addPoint (Point point) {
        points.addPoint(point);
    }





    public Rectangle2D getBounds () {
        float maxX, maxY, minX, minY;

        minX = Float.MAX_VALUE;
        minY = Float.MAX_VALUE;

        maxX = Float.MIN_VALUE;
        maxY = Float.MIN_VALUE;

        for (Point point : points.points) {
            if (point.getX() > maxX) {
                maxX = point.getX();
            }

            if (point.getY() > maxY) {
                maxY = point.getY();
            }

            if (point.getX() < minX) {
                minX = point.getX();
            }

            if (point.getY() < minY) {
                minY = point.getY();
            }
        }

        return new Rectangle2D(maxX - minX, maxY - minY, minX, minY);

    }

    /**
     * Calculate the area of the 2D point attributes.
     * @todo Fix this so that it will return the correct value when a
     * polygon vertex crosses a side.
     * @return
     */
    public float getArea2D () {

        float denominator = 0;
        float numerator = 2;

        Point[] pointsArray = points.toArray();

        for (int i = 0; i < pointsArray.length; i += 1) {
            if (i == 0) {
                denominator += (pointsArray[pointsArray.length - 1].getX() * pointsArray[i].getY()) - (pointsArray[pointsArray.length - 1].getY() * pointsArray[i].getX());
            }
            else {
                denominator += (pointsArray[i - 1].getX() * pointsArray[i].getY()) - (pointsArray[i - 1].getY() * pointsArray[i].getX());
            }

        }

        return Math.abs(denominator / numerator);

    }

}
