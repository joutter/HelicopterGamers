import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BattleField {
    Terminal terminal;
    //private final int COLUMNS = 130;
    private Player player;

    public BattleField(Terminal terminal){
        this.terminal = terminal;

        int ROWS = 0;
        try {
            //terminal = terminalFactory.createTerminal();
           ROWS = terminal.getTerminalSize().getRows();
            terminal.setCursorVisible(false);
        }catch (IOException e){
            System.out.println(e);
        }

        player = new Player(1,terminal,ROWS);
        handleKeyStrokes();
   }

   private void handleKeyStrokes(){

        int index = 0;
        int mover = 0;
        KeyStroke keyStroke = null;
       boolean continueGame = true;
       try {
           while(continueGame) {
               do {

                   index++;
                   if (index % 10 == 0) {
                       player.move(mover);
                       }

                   Thread.sleep(5);
                   keyStroke = terminal.pollInput();

               }while (keyStroke == null);

               if (keyStroke.getCharacter() != null) {
                    if (keyStroke.getCharacter() == 'q') {
                       continueGame = false;
                       StartMenu.startMenu();
                   }
               }
               switch (keyStroke.getKeyType()){
                   case ArrowUp -> {
                       //player.move(-1);
                       mover=-1;
                   }
                   case ArrowDown -> {
                       //player.move(1);
                       mover = 1;
                   }
               }
           }
       }catch (Exception e) {
           System.out.println(e);
       }
   }
}
