package ru.stqa.pft.sandboxx;

public class Equation {

    private final double a;
    private final double b;
    private final double c;

    private int n;

    public Equation(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;

        double d = b*b - 4*a*c;

//        if (a == 0) {
//            if (b == 0) {
//                if (c == 0) {
//                    n = -1;
//                } else {
//                    n = 0;
//                }
//            } else {
//                n = 1;
//            }
//
//        } else {
//            if (d > 0) {
//                n = 2;
//            } else if (d == 0) {
//                n = 1;
//            } else {
//                n = 0;
//            }
//        }
//Если нужно наоборот поменять блоки местами внизу
//        if (a != 0) {
//            if (d > 0) {
//                n = 2;
//            } else if (d == 0) {
//                n = 1;
//            } else {
//                n = 0;
//            }
//
//        } else if (b == 0) {
//          if (c == 0) {
//              n = -1;
//          } else {
//              n = 0;
//          }
//        } else {
//          n = 1;
//        }
// О5 меняем блоки местами внизу
//        if (a != 0) {
//            if (d > 0) {
//                n = 2;
//            } else if (d == 0) {
//                n = 1;
//            } else {
//                n = 0;
//            }
//
//        } else if (b != 0) {
//          n = 1;
//
//        } else if (c == 0) {
//            n = -1;
//
//        } else {
//            n = 0;
//        }
//  Меняем снова блоки внизу. Такой код более понятно читать, понимать чем варианты выше.
        if (a != 0) {
            if (d > 0) {
                n = 2;
            } else if (d == 0) {
                n = 1;
            } else {
                n = 0;
            }

        } else if (b != 0) {
          n = 1;

        } else if (c != 0) {
            n = 0;

        } else {
            n = -1;
        }


    }

//        if (d > 0) {
//            n = 2;
//        } else {
//            if (d == 0) {
//                n = 1;
//            } else {
//                n = 0;
//            }
// внизу тоже самое и нижний вариант более правильный
//        if (d > 0) {
//            n = 2;
//        } else if (d == 0) {
//          n = 1;
//        } else {
//          n = 0;
//        }
//    }
//Внизу тоже правильно и будет работать. Все три условия будут обязательно проверяться, даже если в первом if условие было соблюдено и будут проведиться проверки во всех "if".
//При наличии блока else, лишние проверки не будут выполняться
//    if (d > 0) {
//        n = 2;
//    }
//
//    if (d == 0) {
//        n = 1;
//    }
//
//    if (d < 0) {
//          n = 0;
//        }
//    }

    ///
    ///Итоги if и else. Можно выбирать любые проверки выше.
    ///
    public int rootNumber() {
        return n;
    }
}
