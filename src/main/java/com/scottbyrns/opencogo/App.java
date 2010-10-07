package com.scottbyrns.opencogo;

import com.scottbyrns.opencogo.point.Point;
import com.scottbyrns.opencogo.point.PointFile;
import com.scottbyrns.opencogo.point.PointMap;

/**
 * Created by IntelliJ IDEA.
 * User: scott
 * Date: Oct 4, 2010
 * Time: 9:02:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main (String[] args) {

        Polygon myPolygon = new Polygon();

        myPolygon.addPoint(9, 0);
        myPolygon.addPoint(9, 7);
        myPolygon.addPoint(11, 2);
        myPolygon.addPoint(2, 2);

        System.out.println(myPolygon.getArea2D());

        PointMap myPointMap = new PointMap();
        Point testPoint = new Point(1, 1);
        for (int i = 0; i < 100000; i += 1) {
            myPointMap.addPoint("P" + i, testPoint);
        }

        for (int i = 0; i < 100000; i += 1) {
            myPointMap.getPoint("P" + i);
        }

        PointFile file = new PointFile("/Users/scott/Workspace/OpenCOGO/testData.json");
        file.savePointFile("/tmp/test.json");

    }

}
