import java.util.Random;

public class Obsticle {
public int x;
public int y;
int oldx;
public final char marker = '\u2588';

// obsticle constructer
Obsticle(int width, int height){
    Random r = new Random();
    x = width;
    y = r.nextInt(height)+1;
}

// ground constructor
public Obsticle(int width){
    this.x = width;
    this.y = 34;
}

}
