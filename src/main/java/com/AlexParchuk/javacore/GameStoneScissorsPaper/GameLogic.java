package com.AlexParchuk.javacore.GameStoneScissorsPaper;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    private final Field field = new Field();
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private int userWin = 0, aiWin = 0, gamesPlayed = 0;
    private String GREETING_MESSAGE = "Hello my friend!";
    private String INCORRECT_ANSWER_MESSAGE = "...incorrect answer! repeat please...";
    private String ENTER_CHOICE_MESSAGE = "Please, enter your choice (1-Stone/2-Scissors/3-Paper/9-Exit):";
    private String GOODBYE_MESSAGE = "Goodbye, my friend)";
    private Figure userFigure, aiFigure;
    private int result;

    public void startGame() {

        System.out.println(GREETING_MESSAGE);

        boolean playOn = showMainMenu();

        while (playOn) {

            System.out.println(ENTER_CHOICE_MESSAGE);

            scanner.reset();

            try {
                int userValue = scanner.nextInt();
                if (userValue >= 1 & userValue <= 3) {
                    field.setF1(Figure.getByValue(userValue));
                    field.setF2(getRandomFigure());
                } else if (userValue == 9)
                    break;
                else {
                    System.out.println(INCORRECT_ANSWER_MESSAGE);
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println(INCORRECT_ANSWER_MESSAGE);
                continue;
            }

            result = calculateWinner(field);

            System.out.print(field.toString() + ": ");

            if (result == 0)
                System.out.println("Drow!");
            else if (result == 2) {
                System.out.println("ai win.");
                aiWin++;
            }
            else {
                System.out.println("Human win.");
                userWin++;
            }
            gamesPlayed++;
        }

        if (gamesPlayed > 0) {

            System.out.println("Total games played: " + gamesPlayed);
            System.out.println("Total score: ai win = " + aiWin + ", user win " + userWin);

            if (aiWin > userWin) System.out.println("Sorry, AI win)");
            else if (aiWin < userWin) System.out.println("Congratulation, user win!");
            else System.out.println("The game ended in a draw!)");

        }

        System.out.println(GOODBYE_MESSAGE);
    }

    private void showRules() {
        System.out.println("Rules---------------------------------");
        System.out.println("Stone      - 1");
        System.out.println("Scissors   - 2");
        System.out.println("Paper      - 3");
        System.out.println("Stone(1) - Scissors(2) = Stone(1) win");
        System.out.println("Stone(1) - Paper(3)    = Paper(3) win");
        System.out.println("Scissors(2) - Paper(3) = Scissors(2) win");
    }

    private boolean showMainMenu() {

        while (true) {

            System.out.println("Main menu:...select a menu item...");
            System.out.println("Show rules - 0");
            System.out.println("Play       - 1");
            System.out.println("Exit       - 9");

            scanner.reset();

            try {
                int answear = scanner.nextInt();
                if (answear == 0) showRules();
                else if (answear == 1) return true;
                else if (answear == 9) return false;
                else System.out.println(INCORRECT_ANSWER_MESSAGE);
            } catch (InputMismatchException e) {
                System.out.println(INCORRECT_ANSWER_MESSAGE);
            }
        }
    }

    private Figure getRandomFigure() {
        return Figure.getByValue(random.nextInt(1, 4));
    }

    private int calculateWinner(Field field) {

        int val1 = field.getF1().getChoice(); //user
        int val2 = field.getF2().getChoice(); //ai

        if (val1 == val2)
            return 0;
        else if ((val1 == 1 & val2 == 2) | (val1 == 2 & val2 == 3) | (val1 == 3 & val2 == 1))
            return 1;
        else
            return 2;
    }
}
