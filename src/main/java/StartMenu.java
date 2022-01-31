import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class StartMenu {
        public StartMenu(){
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
            terminal.setCursorPosition(i+51,15);
            terminal.putCharacter(welcome.charAt(i));
        }
        String question = "Start A New Game";
        for (int i = 0; i < question.length(); i++) {
            terminal.setCursorPosition( i+57, 16);
            terminal.putCharacter(question.charAt(i));
        }

        //Options
        String option = "y / n";
        for (int i = 0; i < option.length(); i++) {
            terminal.setCursorPosition( i+62, 17);
            terminal.putCharacter(option.charAt(i));
        }
        terminal.flush();
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
                System.out.println("Starting game...");
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
