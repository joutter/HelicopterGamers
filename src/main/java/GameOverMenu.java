import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

public class GameOverMenu {
    Terminal terminal;
    public GameOverMenu(Terminal terminal){
        this.terminal = terminal;
    }
    public void gameOverMenu() throws Exception{
        //menu
        String welcome = "GAME OVER";
        for (int i = 0; i < welcome.length(); i++) {
            terminal.setCursorPosition(i+51,15);
            terminal.putCharacter(welcome.charAt(i));
        }
        String totalPoints = "Points: ";
        for (int i = 0; i < totalPoints.length(); i++) {
            terminal.setCursorPosition( i+56, 16);
            terminal.putCharacter(totalPoints.charAt(i));
        }
        String question = "Play A New Game?";
        for (int i = 0; i < question.length(); i++) {
            terminal.setCursorPosition( i+56, 17);
            terminal.putCharacter(question.charAt(i));
        }
        //Options
        String option = "y / n";
        for (int i = 0; i < option.length(); i++) {
            terminal.setCursorPosition( i+62, 18);
            terminal.putCharacter(option.charAt(i));
        }
        KeyStroke keyStroke = terminal.pollInput();
        keyStroke = null;
        boolean continueReadingInput = true;
        while (continueReadingInput){
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();
            if(c == Character.valueOf('y') || c == Character.valueOf('Y')){
                System.out.println("Starting a new game...");
                terminal.clearScreen();
                new BattleField(terminal);
            }
            //If user wants to quit
            else if(c == Character.valueOf('n') || c == Character.valueOf('N')){
                continueReadingInput = false;
                System.out.println("Quitting...");
                terminal.close();
            }
            terminal.flush();
        }
    }
}
