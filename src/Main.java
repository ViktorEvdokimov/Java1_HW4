import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int isNewGame;
        do {
            char field[][] = createField();
            boolean end;
            do {
                handlePlayer(field);
                printField(field);
                end = chekEnd(field, "Player", 'x');
                handleComputer(field);
                printField(field);
                end = end || chekEnd(field, "Computer", '0');
            } while (end == false);
            System.out.println("Хотите сиграть еще раз? Если нет введите 0, если да любое другое целое число");
            isNewGame = sc.nextInt();
        }while (isNewGame!=0);
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
            System.out.println("Введите " + axis + " координату ячейки от 1 до " + (field.length));
            coordinate = sc.nextInt() - 1;
            outRange =  coordinate>(field.length - 1) || coordinate<0;
            if (outRange==true) System.out.println("Введенные координаты не входят в диапазон допустимых значений");
        } while (outRange);
        return coordinate;
    }

    static void handleComputer (char [][]field){
        int x, y;
        int vertical = field.length;
        int horizontal = field.length;
        int mainDiagonal = field.length;
        int sideDiagonal = field.length;
        for (int i=0; i< field.length; i++){
            vertical = field.length;
            horizontal = field.length;
            for (int j=0; j< field[i].length; j++){
                if (field[i][j]=='x') vertical--;
                if (field[j][i]=='x') horizontal--;
            }
            if (vertical==1){
                for(int j=0; j< field.length; j++){
                    if (field[i][j]=='-'){
                        field[i][j]='0';
                        return;
                    }
                }
            }
            if (horizontal==1){
                for(int j=0; j< field.length; j++){
                    if (field[j][i]=='-') {
                        field[j][i]='0';
                        return;
                    }
                }
            }
            if (field[i][i]=='x') mainDiagonal--;
            if (field[i][field.length-i-1]=='x') sideDiagonal--;
        }
        if (mainDiagonal==1){
            for(int j=0; j< field.length; j++){
                if (field[j][j]=='-') {
                    field[j][j] = '0';
                    return;
                }
            }
        }
        if (sideDiagonal==1){
            for(int j=0; j< field.length; j++){
                if (field[j][field.length-j-1]!='x') {
                    field[j][field.length - j - 1] = '0';
                    return;
                }
            }
        }
        do{
            x = (int) (Math.random()*field.length);
            y = (int) (Math.random()*field.length);
        } while (field[x][y]!='-');
        field [x][y]='0';
    }

    static boolean chekWin (char [][]field, String winner, char handleSymbol){
        boolean vertical = true;
        boolean horizontal = true;
        boolean mainDiagonal = true;
        boolean sideDiagonal = true;
        for (int i=0; i< field.length; i++){
            vertical = true;
            horizontal = true;
            for (int j=0; j< field[i].length; j++){
                if (field[i][j]!=handleSymbol) vertical = false;
                if (field[j][i]!=handleSymbol) horizontal = false;
            }
            if (vertical || horizontal) break;
            if (field[i][i]!=handleSymbol) mainDiagonal = false;
            if (field[i][field.length-i-1]!=handleSymbol) sideDiagonal = false;
        }
        boolean isWin = vertical || horizontal || mainDiagonal || sideDiagonal;
        if (isWin) System.out.println("Выйграл " +winner);
        return isWin;
    }

    static boolean chekEnd (char [][]field, String lastHandle, char handleSymbol){
        if (chekWin(field, lastHandle, handleSymbol)) return true;
        boolean end = true;
        for (int i=0; i< field.length; i++){
            for (int j=0; j<field[i].length; j++){
                if (field[i][j] == '-') end = false;
            }
        }
        if (end) System.out.println("Все ячейкы заняты. Ничья");
        return end;
    }


}
