package windsdon.pacman;

import javax.swing.JFrame;

/**
 *
 * @author Windsdon
 */
public class PacManLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("PacMan by Windsdon");
        PacMan pacMan = new PacMan(args, window);
        
    }
}
