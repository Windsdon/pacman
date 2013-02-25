package windsdon.pacman;

/**
 *
 * @author Windsdon
 */
abstract class Entity {

    private int xPos;
    private int yPos;

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
}
