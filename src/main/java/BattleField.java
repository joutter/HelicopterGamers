import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BattleField {
    Terminal terminal;
    private final int COLUMNS = 130;
    private final int ROWS = 35;
    private Player player;

    public BattleField(){

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(COLUMNS,ROWS));
        try {
            terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);
        }catch (IOException e){
            System.out.println(e);
        }
        player = new Player(1,terminal,ROWS);
        handleKeyStrokes();
   }

   private void handleKeyStrokes(){

       KeyStroke keyStroke = null;
       boolean continueGame = true;
       try {
           while(continueGame) {
               do {
                   Thread.sleep(5);
                   keyStroke = terminal.pollInput();

               }while (keyStroke == null);

               if (keyStroke.getCharacter() != null) {
                    if (keyStroke.getCharacter() == 'q' || keyStroke.getKeyType().equals(KeyType.EOF)) {
                       continueGame = false;
                       terminal.close();
                   }
               }
               switch (keyStroke.getKeyType()){
                   case ArrowUp -> player.move(-1);
                   case ArrowDown -> player.move(1);
               }
           }
       }catch (Exception e) {
           System.out.println(e);
       }
   }
}
