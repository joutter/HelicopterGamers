import java.util.Random;

public class Obsticle {
int x;
int y;

// obsticle constructer
Obsticle(int width, int height){
    Random r = new Random();
    x = r.nextInt(width);
    y = r.nextInt(height);
}

// ground constructor
public Obsticle (int width){
    this.x = width;
    this.y = 1;
}

}
