
import java.util.Scanner;
public class
Main {
    public static void main(String[] args) {
        SpaceInvadersConsole gameEngine = new SpaceInvadersConsole(20, 20);
        Thread userInputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            char userInput;
            while (true) {
                userInput = scanner.next().charAt(0);
                switch (Character.toUpperCase(userInput)) {
                    case 'A' -> gameEngine.moveLeft();
                    case 'D' -> gameEngine.moveRight();
                    case 'Q' -> {
                        System.out.println("Quitting game. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    }
                }
            }
        });
        userInputThread.start();
        while (true) {
            gameEngine.iteration();
            gameEngine.view();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

