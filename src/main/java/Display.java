import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Display {
    private Terminal terminal;
    private final int yPos = 0;
    private int xPos;
    private String live = "";
    private final String pointsString = "Points: ";
    private final String livesString = "Lives: ";

    public Display(Terminal terminal){
        this.terminal = terminal;
        drawDisplay();
    }
    public void reDrawDisplay(){
        drawDisplay();
        drawLives();
    }
    public void setLive(int lives){
        this.live = lives +"";
        drawDisplay();
        drawLives();
    }
    public void drawLives(){
        try {
            for(int i=0;i<live.length();i++) {
                terminal.setCursorPosition(xPos+livesString.length()+i, yPos);
                terminal.putCharacter(live.charAt(i));
            }
        }catch (IOException e){e.printStackTrace();}
    }
    public void setPoints(int points) {
        String point = points+"";
        try {
            for(int i=0;i<point.length();i++) {
                terminal.setCursorPosition(xPos+livesString.length()+pointsString.length()+2+i, yPos);
                terminal.putCharacter(point.charAt(i));
            }
        }catch (IOException e){e.printStackTrace();}
    }
    private void drawDisplay(){
        try {
            xPos = terminal.getTerminalSize().getColumns()-livesString.length()-pointsString.length()-15;

            for (int i = 0; i < livesString.length(); i++) {
                terminal.setCursorPosition(xPos+1+i,yPos);
                terminal.putCharacter(livesString.charAt(i));
            }
            for (int i = 0; i < pointsString.length(); i++) {
                terminal.setCursorPosition(xPos+livesString.length()+3+i,yPos);
                terminal.putCharacter(pointsString.charAt(i));
            }

            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
