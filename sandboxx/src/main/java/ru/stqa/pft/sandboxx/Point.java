package ru.stqa.pft.sandboxx;

public class Point {

//    public double x1;
//    public double x2;
//    public double y1;
//    public double y2;
//
//    public Point(double x1, double x2, double y1, double y2) {
//        this.x1 = x1;
//        this.x2 = x2;
//        this.y1 = y1;
//        this.y2 = y2;
//    }
//
//    public double distance() {
//        return Math.sqrt(this.x2 - this.x1) * (this.x2 - this.x1) + (this.y2 - this.y1) * (this.y2 - this.y1);
//    }
  //
//  Предыдущая моя версия, чтобы не потерять её


    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }
}
