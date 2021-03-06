import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.*;

public class BattleField {
    private Terminal terminal;
    private Player player;
    protected ObsticleHolder obsticleHolder;
    private Obsticle obsticle;
    private List<Bullet> bulletList;
    private Bullet bullet;
    private int ROWS = 0;
    private int COLUMNS = 0;
    private int index = 0;
    private Display display;
    private int mover = 0;
    private Enemy enemy;
    private List<Enemy> enemyList;
    private boolean bulletAlive = false;

    private boolean continueGame = true;
    private  KeyStroke keyStroke = null;
    private boolean playerAlive = true;

    public BattleField(Terminal terminal){
        this.terminal = terminal;
        bulletList = new ArrayList<>();
        display = new Display(terminal);

        try {
           ROWS = terminal.getTerminalSize().getRows();
            COLUMNS = terminal.getTerminalSize().getColumns();
            terminal.setCursorVisible(false);
        }catch (IOException e){
            System.out.println(e);
        }
        // enemy = new Enemy(100, 15, terminal);
        player = new Player(terminal,ROWS);
        display.setLive(player.getLives());
        obsticleHolder = new ObsticleHolder(terminal);
        handleKeyStrokes();

   }

   private void handleKeyStrokes(){

       try {
           while(continueGame) {
               do {
                   if(playerAlive) {
                       //skicka index till display
                       display.setPoints(index);
                       if (index % 3 == 0) {
                           handleObsticle();
                           removeOldObsticle();
                       }
                       if (index > 1000) {
                           if (index % 3 == 0) {
                               handleObsticle();
                               removeOldObsticle();
                           }
                       }
                       if (index % 100 == 0){
                           obsticleHolder.addObsticle(terminal.getTerminalSize().getColumns(),terminal.getTerminalSize().getRows());
                       }
                       if (index > 1000){
                        if (index % 100 == 0){
                           obsticleHolder.addObsticle(terminal.getTerminalSize().getColumns(),terminal.getTerminalSize().getRows());
                       }
                       }
                       if (index % 10 == 0) {
                           player.move(mover);
                           checkCollision();
                       }
                       if (index % 3 == 0) {
                           if (!bulletList.isEmpty()) {
                               bulletHandler();
                           }
                       }

                       index++;
                       Thread.sleep(5);
                       keyStroke = terminal.pollInput();
                   }

               }while (keyStroke == null);
               if (keyStroke.getCharacter() != null) {
                   if (keyStroke.getCharacter() == 'q'){
                       continueGame = false;
                       terminal.close();
                       new StartMenu(true);
                   }
               }
               switch (keyStroke.getKeyType()) {
                   case ArrowUp -> {
                       mover = -1;
                   }
                   case ArrowDown -> {
                       mover = 1;
                   }
                   case Tab -> {
                       if (!bulletAlive) {
                           bulletList.add(new Bullet(player.xPos, player.yPos, player.getHeliWidth(), player.getHeliHight(), terminal));
                           bulletAlive = true;
                       }
                   }
               }
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
   }
   private void checkCollision() throws Exception {
       for(int j=0;j<obsticleHolder.getObsticles().size();j++) {
           for(int[] coordinate : obsticleHolder.getObsticles().get(j).getObsticleCordinates()) {
               if ((coordinate[0] <= player.xPos + player.getHeliWidth() && coordinate[0] /*+ obsticle.width()*/ >= player.xPos)
                       && (player.yPos + player.getHeliHight() >= coordinate[1] && player.yPos <= coordinate[1])) {
                   try {
                       terminal.clearScreen();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   obsticleHolder = new ObsticleHolder(terminal);
                   player.restart();
                   player.decreaseLive();

                   if (player.getLives() > 0) {
                       display.setLive(player.getLives());
                       //If lives = 0
                   } else if(player.getLives()==0) {
                           System.out.println("GAME OVER!");
                           playerAlive = false;
                           continueGame = false;
                           terminal.close();
                           new StartMenu(false, index);

                           terminal.flush();
                           //terminalen clear. spelaren & obsticles inte r??r sig && att visa po??ng.
                       }
                   }

               }
           }
       }

   private void handleObsticle() throws IOException{
       obsticleHolder.drawObsticle();
   }
   private void removeOldObsticle() throws IOException {
       for(int j=0;j<obsticleHolder.getObsticles().size();j++){
           obsticle = obsticleHolder.getObsticles().get(j);
           for(int[] coordinate : obsticle.getObsticleCordinates()) {
                if (coordinate[0] < -2){
                    obsticle.removeObsticle(terminal);
                    obsticleHolder.obsticles.remove(obsticle);
                }
            }
        }
   }

   private void bulletHandler() throws IOException {

            //G??r igenom alla bullets som lagt i listan vid TAB
           for (int i=0;i<bulletList.size();i++) {
               int[] bulletPosition=bulletList.get(i).move();

               // F??r varje bullet g?? igenom alla Obsticles och se om tr??ff
               for(int j=0;j<obsticleHolder.getObsticles().size();j++){
                   obsticle = obsticleHolder.getObsticles().get(j);

                   for(int[] coordinate : obsticle.getObsticleCordinates()) {
                       if (bulletPosition[0] >= coordinate[0] && bulletPosition[1] == coordinate[1]) {

                           // radera obsticle om tr??ff
                           obsticle.removeObsticle(terminal);
                           obsticleHolder.obsticles.remove(j);

                           //f??r ibland indexOutOfBounds s?? m??ste dubbelkolla
                           if (i < bulletList.size()) {
                               //radera och ta bort bullet fr??n listan om tr??ff
                               bulletList.get(i).killBullet();
                               bulletList.remove(i);
                               bulletAlive = false;
                           }
                       }
                   }
               }
               //Radera bullet om den ??ker utanf??r sk??rmen
               if ( bulletPosition[0] >= COLUMNS) {
                   bulletList.get(i).killBullet();
                   bulletList.remove(i);
                   bulletAlive = false;
               }
           }
       }
}
