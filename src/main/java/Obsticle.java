import java.util.Random;

public class Obsticle {
    protected int x;
    protected int y;
  protected int[][] obsticleCordinates = new int[][]{
        {-1, -1},{-1,-1},{-1,-1},{-1,-1}};
    int[][] OldobsticleCordinates = new int[][]{
            {-1, -1},{-1,-1},{-1,-1},{-1,-1}};

int oldx;
protected final char marker = '\u2588';

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getObsticleCordinates() {
        return obsticleCordinates;
    }

    public int getOldx() {
        return oldx;
    }

    public char getMarker() {
        return marker;
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

    public void setOldx(int oldx) {
        this.oldx = oldx;
    }

    // obsticle constructer
Obsticle(int width, int height){
    Random r = new Random();
    setX(width);
    setY(r.nextInt(height));
    int i=0;
    for (int[] obsticle : obsticleCordinates){
        obsticle[0] = x;
        obsticle[1] = y+i;
        i++;
    }
}



// ground constructor
public Obsticle(int width){
    this.x = width;
    this.y = 34;
    int i =0;
    for (int[] obsticle : obsticleCordinates){
        obsticle[0] = x;
        obsticle[1] = y;
        i++;
    }
}


}
