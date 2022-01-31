import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Random;

public class Obsticle {
    protected int x;
    protected int y;
  protected int[][] obsticleCordinates = new int[][]{
        {-1, -1},{-1,-1},{-1,-1},{-1,-1}};

    public int[][] getObsticleCordinates() {
        return obsticleCordinates;
    }

    protected final char marker = '\u2588';

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setObsticleCordinates(int[][] obsticleCordinates) {
        this.obsticleCordinates = obsticleCordinates;
    }

    // obsticle constructer
Obsticle(int width, int height){
    Random r = new Random();
    setX(width);
    setY(r.nextInt(height));
    int i=0;
    for (int[] obsticle : obsticleCordinates){
        obsticle[0] = getX();
        obsticle[1] = getY()+1+i;
        i++;
    }
}

// ground constructor
public Obsticle(int width, boolean ground){
        if (ground){

    this.x = width;
    this.y = 34;
    for (int[] obsticle : obsticleCordinates){
        obsticle[0] = x;
        obsticle[1] = y;
    }
        }

        else{
            this.x = width;
            this.y = 1;
            for (int[] obsticle : obsticleCordinates){
                obsticle[0] = x;
                obsticle[1] = y;
            }
        }
}

    public void removeObsticle(Terminal terminal) throws IOException {
        for (int[]obsticle : obsticleCordinates){
                terminal.setCursorPosition(obsticle[0],obsticle[1]);
                terminal.putCharacter(' ');
                terminal.flush();

            }
        }
    }

