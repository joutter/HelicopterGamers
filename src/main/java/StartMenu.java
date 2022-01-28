import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class StartMenu {
    //Startscreen with menu
        Terminal terminal;
        //private final int COLUMNS = 130;
        //private final int ROWS = 35;

        public StartMenu(Terminal terminal){
            this.terminal = terminal;
        }

    public static void startMenu() throws Exception {
        final int COLUMNS = 130;
        final int ROWS = 35;
        Terminal terminal;
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(COLUMNS,ROWS));
        terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        //menu
        String welcome = "WELCOME TO HELICOPTERGAMERS";
        for (int i = 0; i < welcome.length(); i++) {
            terminal.setCursorPosition(i+58,15);
            terminal.putCharacter(welcome.charAt(i));
        }
        String question = "Start A New Game";
        for (int i = 0; i < question.length(); i++) {
            terminal.setCursorPosition( i+61, 16);
            terminal.putCharacter(question.charAt(i));
        }
        //Options
        String option = "Y / N";
        for (int i = 0; i < option.length(); i++) {
            terminal.setCursorPosition( i+67, 17);
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
            if(c == Character.valueOf('Y')){
                System.out.println("Starting game...");
                new BattleField();
            }
            //If user wants to quit
            else if(c == Character.valueOf('N')){
                continueReadingInput = false;
                System.out.println("Quitting...");
                terminal.close();
            }
            terminal.flush();
        }
    }
}
