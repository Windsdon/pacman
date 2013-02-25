package windsdon.pacman;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Windsdon
 */
public class TiledImage {

    private BufferedImage theImage;
    private int tileH;
    private int tileW;
    
    public TiledImage(BufferedImage image, int tileSize){
        tileW = tileSize;
        tileH = tileSize;
        theImage = image;
    }

    public Image getTile(int x, int y) {
        return theImage.getSubimage(x * tileW, y * tileH, tileW, tileH);
    }
}
