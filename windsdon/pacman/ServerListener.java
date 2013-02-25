package windsdon.pacman;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windsdon
 */
class ServerListener implements Runnable {

    private PacManClientListener listener;
    private Socket conn;
    private boolean stopped = false;

    public ServerListener(PacManClientListener client) {
        listener = client;
    }

    public boolean start(String host, int port) {
        try {
            conn = new Socket(host, port);
        } catch (IOException ex) {
            listener.failedToConnect(ex);
            return false;
        }
        new Thread(this).start();
        return true;
    }

    @Override
    public void run() {

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while (!stopped) {
                String line = br.readLine();
                listener.updateReceived(line);
            }
        } catch (IOException ex) {
            listener.failedToConnect(ex);
            stopped = true;
        }

    }
}
