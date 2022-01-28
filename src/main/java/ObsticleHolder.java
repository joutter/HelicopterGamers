import java.util.ArrayList;

public class ObsticleHolder {
    ArrayList<Obsticle> obsticles = new ArrayList<>();

    void addObsticle(int width, int height){
        obsticles.add(new Obsticle(width, height));
    }

    void moveObsticle(){
        for (Obsticle obsticle : obsticles){
            obsticle.x -= 1;
        }
    }
    boolean checkObsticle(int width, int height){
        for (Obsticle obsticle : obsticles){
            if ((obsticle.x == width) && (obsticle.y == height)){
                return true;
            }
        }
        return false;
    }
}
