package ru.stqa.pft.sandboxx;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance1() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(-199, -100);
        Point p2 = new Point(300, 400);
        Assert.assertEquals(p1.distance(p2), 706.4000283125703);
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(56, 27);
        Point p2 = new Point(-36, -44);
        Assert.assertEquals(p1.distance(p2), 116.21101496846157);
    }
}
