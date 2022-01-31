import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Enemy {

    protected int x,y,oldX,oldY;
    private Terminal terminal;
    private char enemy = '@';
    private EnemyModel enemymodel ;
    private int[][] enemyCoordinates;


    public Enemy(int x, int y, Terminal terminal) {
        this.x = x;
        this.y = y;
        this.terminal = terminal;
        this.enemy = enemy;
        enemymodel = new EnemyModel();
        enemyCoordinates = enemymodel.getCoordinates();
        drawEnemy();
    }


    public int move(){
        int move = 1;
        oldX = x;
        x -= move;
        eraseEnemy();
        drawEnemy();
        return x;
    }

    void drawEnemy() {
        char icon = enemymodel.getIcon();
        try {
            terminal.setForegroundColor(TextColor.ANSI.MAGENTA);
            for (int[] coordinate: enemyCoordinates){
                terminal.setCursorPosition(x - 1 +coordinate[1],y+coordinate[0]);
                terminal.putCharacter(icon);

            }
            terminal.setForegroundColor(TextColor.ANSI.DEFAULT);
            terminal.flush();

        }catch (IOException e){
            System.out.println(e);
        }
    }

    void eraseEnemy(){
        try {
            for (int[] coordinate: enemyCoordinates){
                terminal.setCursorPosition(x+coordinate[1],y+coordinate[0]);
                terminal.putCharacter(' ');

            }
            terminal.flush();

        }catch (IOException e){
            System.out.println(e);
        }



    }


    }
