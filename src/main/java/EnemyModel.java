public class EnemyModel {

    int[][] coordinates;
    private final char icon = '#';
    private int HEIGHT;
    private int WIDTH;


    public EnemyModel(){
        coordinates = new int[][]{
                {0, 0},{0,2},
                {1,1},
                {2, 0},{2,2}};
        HEIGHT = 3;
        WIDTH = 3;
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
