import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Bullet {

    private Player player;
    private int xPos,yPos,oldX,oldY, playerWidth, playerHight;
    private Terminal terminal;
    private char bullet ='*';

    public Bullet(int x, int y, int width, int hight, Terminal terminal) {
        this.xPos = x;
        this.yPos = y;
        this.terminal = terminal;
        this.playerWidth = width;
        this.playerHight = hight;
        drawBullet();
        move();
    }

    public void move(){
        int move = 1;

        xPos += move;
        drawBullet();
    }

    public void drawBullet() {
        try {
                terminal.setCursorPosition(xPos+playerWidth ,yPos+playerHight/2);
                terminal.putCharacter(bullet);
                terminal.flush();

        }catch (IOException e){
            System.out.println(e);
        }


    }
}
