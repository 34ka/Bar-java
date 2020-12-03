package ru.stqa.pft.sandboxx;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Alexei");

		Square s = new Square(5);
		//s.l = 5;
        // длина 5 будет передаваться в качестве параметров в конструктор и заполнять эти атрибуты отдельно уже не нажно
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        //r.a = 4; длина стороны a = 4 будет передаваться в качестве параметров в конструктор и заполнять эти атрибуты отдельно уже не нажно
        //r.b = 4; длина стороны b = 6 будет передаваться в качестве параметров в конструктор и заполнять эти атрибуты отдельно уже не нажно
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

//        Point p = new Point(1,2,3,4);
//        //p.x1 = 1; заполнять уже не нужно
//        //p.x2 = 2; заполнять уже не нужно
//        //p.y1 = 3; заполнять уже не нужно
//        //p.y2 = 4; заполнять уже не нужно
//
//        System.out.println("Расстояние между точками " + "= " + p.distance());

        //Предыдущая моя версия, чтобы не потерять

        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);

        System.out.println("Расстояние между точками " + "= " + p1.distance(p1, p2));

}
	public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}