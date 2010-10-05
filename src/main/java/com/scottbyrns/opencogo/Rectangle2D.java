package com.scottbyrns.opencogo;

import com.scottbyrns.opencogo.point.Point;

/**
 * Created by IntelliJ IDEA.
 * User: scott
 * Date: Oct 4, 2010
 * Time: 5:32:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rectangle2D {

    public float height = 0;
    public float width = 0;
    public Point origin = null;


    public Rectangle2D () {
        origin = new Point(0, 0);
    }

    public Rectangle2D (float height, float width, float x, float y) {
        origin = new Point(x, y);
        this.height = height;
        this.width = width;
    }

    public Rectangle2D (float height, float width, Point origin) {
        this.origin = new Point(origin);
        this.height = height;
        this.width = width;
    }



    public float getHeight () {
        return this.height;
    }

    public float getWidth () {
        return this.width;
    }

    public float getX () {
        return this.origin.getX();
    }

    public float getY () {
        return this.origin.getY();
    }

    public Point getOrigin () {
        return this.origin;
    }
    

}
