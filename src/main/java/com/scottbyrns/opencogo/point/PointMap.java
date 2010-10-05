package com.scottbyrns.opencogo.point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: scott
 * Date: Oct 4, 2010
 * Time: 9:20:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointMap {

    public HashMap pointMap = null;
    public PointList points = null;

    public PointMap () {
        pointMap = new HashMap();
        points = new PointList();
    }

    public void addPoint (String key, Point point) {
        this.points.addPoint(point);
        this.pointMap.put(key, this.points.size() - 1);
    }

    public Point getPoint (String key) {
        return this.points.getPoint((Integer)this.pointMap.get(key));
    }
}
