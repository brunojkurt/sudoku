package sudoku;

/**
 * Class to run the application
 * @author Bruno Jungberck
 */
public class main {
    public static void main(String[] args) {
        Console console = new Console();
        console.printLn("Choose the difficulty level: ");
        console.difficultyOptions();
        int difficulty = console.readInt();
        Match game = new Match(difficulty);
        game.play();
    }
}
