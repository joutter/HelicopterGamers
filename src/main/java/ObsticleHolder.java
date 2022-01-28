import java.io.IOException;
import java.util.ArrayList;
import com.googlecode.lanterna.terminal.Terminal;

public class ObsticleHolder {
    private Terminal terminal;
    ArrayList<Obsticle> obsticles = new ArrayList<>();
    //ArrayList<Obsticle>

    public ObsticleHolder(Terminal terminal) {
        this.terminal = terminal;
        this.obsticles = obsticles;
    }

    public ArrayList<Obsticle> getObsticles() {
        return obsticles;
    }

    public void setObsticles(ArrayList<Obsticle> obsticles) {
        this.obsticles = obsticles;
    }

    // add new object to the array of objects.
    void addObsticle(int width, int height){
        obsticles.add(new Obsticle(width, height));
    }
    // draw objects and make the move closer.
    void drawObsticle() throws IOException {
            for (Obsticle obsticle: obsticles){
                    obsticle.oldx = obsticle.x;
                    obsticle.x -= 1;
                    terminal.setCursorPosition(obsticle.x,obsticle.y);
                    terminal.putCharacter(obsticle.marker);
                    terminal.setCursorPosition(obsticle.oldx,obsticle.y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                }
    }

    void addGround(int width){ obsticles.add(new Obsticle(width));}


    // move the objects closer to the player
    void moveObsticle(){
        for (Obsticle obsticle : obsticles){
            obsticle.oldx = obsticle.x;
            obsticle.x -= 1;
        }

    }
    //Check for collision
    boolean checkObsticle(int width, int height, int index){
        for (Obsticle obsticle : obsticles){
            for (int i = 0; i<3;i++){
                if ((obsticle.x == width+i) && (obsticle.y == height)){
                    System.out.println("GAME OVER");
                    System.out.println("You got " + index + " points!");
                    System.exit(0);
                    return false;
            }
                if ((obsticle.x == width) && (obsticle.y == height+i)) {
                    System.out.println("GAME OVER");
                    System.out.println("You got " + index + " points!");
                    System.exit(0);
                    return false;
                }
                    if ((obsticle.x == width+i) && (obsticle.y == height+i)) {
                        System.out.println("GAME OVER");
                        System.out.println("You got " + index + " points!");
                        System.exit(0);

                        return false;
                    }
            }
        }
        return true;
    }
}
