package windsdon.pacman;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author Windsdon
 */
class PacManServer implements Runnable {
    private int serverPort = 22109;
    private ServerSocket serverSocket;
    private boolean serverClosed;
    private ArrayList<Player> players;
    
    public PacManServer() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException ex) {
            System.exit(0);
        }
    }
    
    public boolean start(){
        new Thread(this).start();
        return true;
    }

    @Override
    public void run() {
        while(!serverClosed){
            
        }
    }
    
}
