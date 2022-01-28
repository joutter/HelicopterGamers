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
    protected  ObsticleHolder obsticleHolder;
    public BattleField(){

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(COLUMNS,ROWS));
        try {
            terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);
        }catch (IOException e){
            System.out.println(e);
        }
        new StartMenu();
        player = new Player(1,terminal,ROWS);
        obsticleHolder = new ObsticleHolder(terminal);
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


                    if (index % 500==0){
                        obsticleHolder.addObsticle(COLUMNS, ROWS);
                        obsticleHolder.drawObsticle();
                        obsticleHolder.addGround(COLUMNS);
                    }

                   index++;
                   if (index % 10 == 0) {
                       player.move(mover);
                       }

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
