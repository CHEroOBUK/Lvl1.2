package GB;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 1");
        byte[] arrayTask1 = {0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1};                                        //Задание 1
        replace01(arrayTask1);                                                                              //Задание 1

        System.out.println("Задание 2");
        int[] arrayTask2 = new int[8];                                                                      //Задание 2
        increaseBy3(arrayTask2);                                                                            //Задание 2

        System.out.println("Задание 3");
        int[] arrayTask3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};                                           //Задание 3
        multiplyLow6by2(arrayTask3);                                                                        //Задание 3

        System.out.println("Задание 4");
        int[][] arrayTask4 = new int[6][6];                                                                 //Задание 4
        fillDiagonalWith1(arrayTask4);                                                                      //Задание 4

        System.out.println("Задание 5");
        int[] arrayTask5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 0};                                           //Задание 5
        findMinMax(arrayTask5);                                                                             //Задание 5

        System.out.println("Задание 6");
        int[] arrayTask6 = {2, 2, 2, 1, 2, 2, 0, 10, 1};                                                    //Задание 6
        System.out.println("Массив " + (hasBalance(arrayTask6)?"имеет симметрию":"не имеет симметрии"));    //Задание 6

        System.out.println("Задание 7");
        int[] arrayTask7 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};                                                  //Задание 7
        int x = -42;                                                                                        //Задание 7
        shiftArray(arrayTask7, x);                                                                          //Задание 7
    }

    static void replace01(byte[] a) {
        for (byte i = 0; i < a.length; i++) {   //Предполагается использование массива до 128 элементов
            switch (a[i]) {
                case 1:
                    a[i] = 0;
                    break;
                case 0:
                    a[i] = 1;
                    break;
            }
        }
        for (byte x : a)
            System.out.print(x + "\t");
        System.out.println();
    }

    static void increaseBy3(int[] a){
        for (int i = 0; i < a.length; i++) {
            a[i] = i*3;
        }
        for (int x : a)
            System.out.print(x + "\t");
        System.out.println();
    }

    static void multiplyLow6by2(int[] a){
        for (int i = 0; i < a.length; i++) {
            if (a[i]<6)
                a[i] *= 2;
        }
        for (int x : a)
            System.out.print(x + "\t");
        System.out.println();
    }

    static void fillDiagonalWith1(int[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++){
                if (i==j)
                    a[i][j] = 1;
            }
        }
        for (int[] x : a) {
            for (int y : x){
                System.out.print(y + "\t");
            }
            System.out.println();
        }
    }

    static void findMinMax(int[] a){
        int min = a[0], max = a[0];
        for (int x : a) {
            if (x < min)
                min = x;
            else if (x > max)
                max = x;
        }
        System.out.println("Минимальное значение = " + min + "\t" + "Максимальное значение = " + max);
    }

    static boolean hasBalance(int[] a){
        int before = 0, after = 0;
        boolean capture = false;
        for (int x : a)
            after = after + x;
        for (int x : a) {
            before += x;
            after -= x;
            if (before == after) {
                capture = true;
                break;
            }
        }
        return capture;
    }

    static void shiftArray(int[] a, int b){
        int i = 0, iStep = 0;               //iStep - решение проблемы охвата всего массива
        int value1 = a[0], value2;          //хранение замещающей и замещаемой переменных соответственно
        int shiftPos, count = 0;            //count - счетчик действий, shiftPos - адрес сдвига
        b = (b - b / a.length * a.length);  //вычисляем упрощенную величину сдвига в пределах длины массива
        do {                                //начинаем сдвиг с 0 элемента
            if ((b <= a.length - 1 - i && b >= 0) || (-b <= i && b < 0)){   //если сдвиг без захода за границу массива
                shiftPos = i + b;           //расчет позиции сдвига
                value2 = a[shiftPos];       //сохраняем замещаемый элемент массива
                a[shiftPos]=value1;         //производим замену из "замещающей переменной"
                value1 = value2;            //перебрасываем замещенный элемент в "замещающую переменную"
            }
            else if (-b > i && b < 0){      //если слева не хватает места
                shiftPos = a.length + b + i;
                value2 = a[shiftPos];
                a[shiftPos]=value1;
                value1 = value2;
            }
            else {                          //если справа не хватает места
                shiftPos = i - a.length + b;
                value2 = a[shiftPos];
                a[shiftPos]=value1;
                value1 = value2;
            }
            i = shiftPos;                   //Для начала следующей замены с места текущей замены
            count ++;                       //увеличение счетчика
            if (i == iStep && count != a.length) {          //если вернулись в уже замещаемый ранее элемент
                ++iStep;    //берем за начало проверки возврата индекс элемента, который стоит сразу за текущим
                i = iStep;                  //сдвигаем ход цикла на 1 позицию
                value1 = a[i];              //сохраняем новый элемент в "замещающую переменную"
            }
        } while (count != a.length);        //проверка охвата элементов массива

            for (int x : a)
                System.out.print(x + "\t");
            System.out.println();
    }
}
