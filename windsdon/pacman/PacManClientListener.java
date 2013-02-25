package windsdon.pacman;

import java.io.IOException;

/**
 *
 * @author Windsdon
 */
interface PacManClientListener {
    public void updateReceived(String line);
    public void failedToConnect(IOException ex);

}
