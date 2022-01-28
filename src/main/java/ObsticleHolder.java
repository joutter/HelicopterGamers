import java.util.ArrayList;

public class ObsticleHolder {
    ArrayList<Obsticle> obsticles = new ArrayList<>();

    // add new object to the array of objects.
    void addObsticle(int width, int height){
        obsticles.add(new Obsticle(width, height));
    }
    // move the objects closer to the player
    void moveObsticle(){
        for (Obsticle obsticle : obsticles){
            obsticle.x -= 1;
        }
    }
    //Check for collision
    boolean checkObsticle(int width, int height){
        for (Obsticle obsticle : obsticles){
            if ((obsticle.x == width) && (obsticle.y == height)){
                return true;
            }
        }
        return false;
    }
}
