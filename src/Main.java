import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	char field[][] = createField();
	printField(field);
	for (int i=0; i<5;i++) {
        handlePlayer(field);
        printField(field);
    }
    }

    static char[][] createField() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер поля");
        int fieldSize = 1;
        do {
            fieldSize = sc.nextInt();
            if (fieldSize <= 0) {
                System.out.println("Размер поля должен быть больше 0");
            }
        }while (fieldSize<=0);
        char [][]field = new char[fieldSize][fieldSize];
        for (int i=0; i<field.length;i++){
            for (int j=0;j<field.length; j++){
                field[i][j] = '-';
            }
        }
        return field;
    }


    static void printField (char [][]field){
        for (int i=0; i<field.length;i++){
            for (int j=0;j<field.length; j++){
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
    }

    static void handlePlayer (char [][]field){
        System.out.println("Введите координаты ячейки");
        int x, y;
        do{
        x = inCoordinate(field, 'x');
        y = inCoordinate(field, 'y');
        if (field[x][y]!='-') {
            System.out.println("Указанная ячейка занята");
            }
        }while (field[x][y]!='-');
        field [x][y] = 'x';
    }


    static int inCoordinate (char [][]field, char axis){
        Scanner sc = new Scanner(System.in);
        int coordinate = 0;
        boolean outRange;
        do {
            System.out.println("Введите " + axis + " координату ячейки от 1 до " + (field.length + 1));
            coordinate = sc.nextInt() - 1;
            outRange =  coordinate>(field.length - 1) || coordinate<0;
            if (outRange==true) System.out.println("Введенные координаты не входят в диапазон допустимых значений");
        } while (outRange);
        return coordinate;
    }


}
