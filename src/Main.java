import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	char field[][] = createField();
	printField(field);
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


}
