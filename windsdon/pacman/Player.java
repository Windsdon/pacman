package windsdon.pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Windsdon
 */
abstract class Player extends Entity {

    protected BufferedImage image;
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public abstract void render(Graphics2D g, Renderer r);
}

class PlayerPacMan extends Player {

    public PlayerPacMan(String name) {
        super(name);
    }

    public PlayerPacMan(String name, BufferedImage image) {
        super(name, image);
    }

    @Override
    public void render(Graphics2D g, Renderer r) {
        g.drawImage(image, getX() - image.getWidth() / 2, getY() - image.getHeight() / 2, null);
        r.drawNameTag(g, this);
    }
}

/*class PlayerGhost extends Player {

 @Override
 public void render(Graphics2D g, Renderer r) {
 throw new UnsupportedOperationException("Not supported yet.");
 }
 }*/
