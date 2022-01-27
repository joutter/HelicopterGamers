import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {
    private int lives,xPos,yPos,oldX,oldY,points,speed, terminalRows, xMargin;
    private HelicopterModel heli;
    private int[][] heliCoordinates;
    private Terminal terminal;

    public Player(int lives, Terminal terminal, int terminalRows){
        this.lives = lives;
        this.terminal = terminal;
        this.terminalRows = terminalRows;
        heli = new HelicopterModel();
        heliCoordinates = heli.getCoordinates();
        points = 0;
        speed = 1;
        xMargin = 10;

        restart();
    }
    public void move(int i){
        int move = i * speed;
            oldY = yPos;
            oldX = xPos;
            yPos += move;
        if(inBound(yPos)) {
            drawHeli(false);
            drawHeli(true);
        }
        else{
            yPos = oldY;
        }

    }
    public void restart(){
        xPos = xMargin;
        yPos = (terminalRows/2) - (heli.getHEIGHT() / 2);
        drawHeli(true);
    }
    private boolean inBound(int y){
        if(yPos<=terminalRows-(heli.getHEIGHT()) && yPos >= 0) {
            return true;
        }
        return false;
    }
    private void drawHeli(boolean draw){

        char icon;
        int x,y;
        if(draw){
            icon = heli.getIcon();
            x = xPos;
            y= yPos;
        }
        else{
            icon = ' ';
            x = oldX;
            y = oldY;
        }

        try {
            for (int[] coordinate: heliCoordinates){
                terminal.setCursorPosition(x+coordinate[1],y+coordinate[0]);
                terminal.putCharacter(icon);
            }
            terminal.flush();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
