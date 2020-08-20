import java.util.Scanner;


public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        char[][] field = new char[3][3];

        for (int i = 0; i < 9; i++) {
            field[i / 3][i % 3] = ' ';
        }

        printField(field);

        int turn = 1;
        while (true) {


            // The loop asks for valid coordinates for the next symbol and then updates the field
            while (true) {
                System.out.println("Enter the coordinates: ");

                String userInput = scanner.nextLine();
                String[] userInputArr = userInput.split(" ");

                int xCoordinate;
                int yCoordinate;

                try {
                    xCoordinate = Integer.parseInt(userInputArr[0]);
                    yCoordinate = Integer.parseInt(userInputArr[1]);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }


                if (xCoordinate < 1 || xCoordinate > 3
                        || yCoordinate < 1 || yCoordinate > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }


                if (field[Math.abs(yCoordinate - 1 - 2)][xCoordinate - 1] == 'X'
                        || field[Math.abs(yCoordinate - 1 - 2)][xCoordinate - 1] == 'O') {

                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                if (turn % 2 == 1) {
                    field[Math.abs(yCoordinate - 1 - 2)][xCoordinate - 1] = 'X';
                } else if (turn % 2 == 0) {
                    field[Math.abs(yCoordinate - 1 - 2)][xCoordinate - 1] = 'O';
                }

                turn++;
                break;
            }

            printField(field);


            // Break conditions
            if (countWinningXLines(field) > 0) {
                System.out.println("X wins");
                break;
            } else if (countWinningOLines(field) > 0) {
                System.out.println("O wins");
                break;
            } else if (findEmptyCell(field) == false) {
                System.out.println("Draw");
                break;
            }
        }
    }

    static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.println(String.format("| %c %c %c |",
                    field[i][0],
                    field[i][1],
                    field[i][2]));
        }
        System.out.println("---------");
    }

    static int countWinningXLines(char[][] field) {
        int totalWinningXLines = 0;

        int descDiagonalCharSum = 0;
        int ascDiagonalCharSum = 0;

        for (int i = 0; i < field.length; i++) {

            int currentRowCharSum = 0;
            int currentColCharSum = 0;

            for (int j = 0; j < field[0].length; j++) {
                currentRowCharSum += field[i][j];
                currentColCharSum += field[j][i];
            }

            if (currentRowCharSum == 264) {
                totalWinningXLines++;
            }

            if (currentColCharSum == 264) {
                totalWinningXLines++;
            }

            descDiagonalCharSum += field[i][i];
            ascDiagonalCharSum += field[i][Math.abs(i - 2)];
        }

        if (descDiagonalCharSum == 264) {
            totalWinningXLines++;
        }

        if (ascDiagonalCharSum == 264) {
            totalWinningXLines++;
        }

        return totalWinningXLines;
    }

    static int countWinningOLines(char[][] field) {
        int totalWinningOLines = 0;

        int descDiagonalCharSum = 0;
        int ascDiagonalCharSum = 0;

        for (int i = 0; i < field.length; i++) {

            int currentRowCharSum = 0;
            int currentColCharSum = 0;

            for (int j = 0; j < field[0].length; j++) {
                currentRowCharSum += field[i][j];
                currentColCharSum += field[j][i];
            }

            if (currentRowCharSum == 237) {
                totalWinningOLines++;
            }

            if (currentColCharSum == 237) {
                totalWinningOLines++;
            }

            descDiagonalCharSum += field[i][i];
            ascDiagonalCharSum += field[i][Math.abs(i - 2)];
        }

        if (descDiagonalCharSum == 237) {
            totalWinningOLines++;
        }

        if (ascDiagonalCharSum == 237) {
            totalWinningOLines++;
        }

        return totalWinningOLines;
    }

    static boolean findEmptyCell(char[][] field) {
        boolean hasEmptyCell = false;

        outerloop:
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == ' ') {
                    hasEmptyCell = true;
                    break outerloop;
                }
            }
        }

        return hasEmptyCell;
    }
}
