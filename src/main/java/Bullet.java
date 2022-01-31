import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Bullet {

    private Player player;
    public int xPos,yPos,oldX,oldY, playerWidth, playerHeight, terminalWidth;
    private Terminal terminal;
    private char bullet ='*';
    private final int bulletSize = 3;

    public Bullet(int x, int y, int width, int height, Terminal terminal) {
        this.xPos = x;
        this.yPos = y;
        this.terminal = terminal;
        this.playerWidth = width;
        this.playerHeight = height;
        try {
            terminalWidth = terminal.getTerminalSize().getColumns();
        }catch (IOException e){
            System.out.println(e);
        }
        drawBullet();
    }

    public int[] move(){
            int move = 1;
            oldX = xPos;
            xPos += move;
            eraseBullet();
            drawBullet();

        return new int[]{xPos+playerWidth,yPos+playerHeight/2};
    }

    private void drawBullet() {

        try {
                terminal.setCursorPosition(xPos+playerWidth ,yPos+playerHeight/2);
                terminal.putCharacter(bullet);
                terminal.flush();

        }catch (IOException e){
            System.out.println(e);
        }
    }
    private void eraseBullet(){

            try {
                terminal.setCursorPosition(oldX + playerWidth - bulletSize, yPos + playerHeight / 2);
                terminal.putCharacter(' ');
            } catch (IOException e) {
               e.printStackTrace();
            }
    }
    public void killBullet(){
        try {
            for (int i=0;i<=bulletSize;i++){
                terminal.setCursorPosition(xPos+playerWidth-i, yPos + playerHeight / 2);
                terminal.putCharacter(' ');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
