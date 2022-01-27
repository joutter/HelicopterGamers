import java.util.ArrayList;
import java.util.List;

public class HelicopterModel {
    private int[][] coordinates;
    private final char icon = '+';
    private final int HEIGHT;
    private final int WIDTH;

    public HelicopterModel(){
        coordinates = new int[][]{
                                  {0, 0},{0,1},{0,2},{0,3},
                                  {1, 0},{1,1},
                                  {2, 0},{2,1},{2,2},{2,3}};
        HEIGHT = 3;
        WIDTH = 4;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }
    public char getIcon(){
        return icon;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
    public int getWIDTH() {
        return WIDTH;
    }
}
