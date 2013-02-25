package windsdon.pacman;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Windsdon
 */
class PacMan extends Canvas implements WindowListener, Runnable, PacManClientListener {

    private Dimension compSize;
    private int w = 800;
    private int h = 600;
    private int scale = 1;
    private boolean running = false;
    private BufferedImage screen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    private JFrame window;
    private int tempFPS;
    private long lastFPStime;
    private int fpsCount;
    private Renderer r;
    //////////
    private String serverAdress;
    private int serverPort;
    private ServerListener serverListener;
    //////////
    private boolean isServer;
    private PacManServer pacServer;

    public PacMan(String[] args, JFrame frame) {
        compSize = new Dimension(w * scale, h * scale);
        resize();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.add(this);
        frame.addWindowListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        window = frame;
        isServer = false;
        serverAdress = "one.windson.tk";
        serverPort = 22109;

        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (string.equals("host")) {
                isServer = true;
                serverAdress = "localhost";
                pacServer = new PacManServer();
            }
        }

        start();
    }

    private void start() {
        running = true;
        new Thread(this).start();
    }

    private void stop() {
        running = false;
    }

    @Override
    public void run() {
        r = new Renderer();
        if (isServer) {
            pacServer.start();
        }
        serverListener = new ServerListener(this);
        while (running) {
            runStep();
        }

        window.dispose();
        System.exit(0);
    }

    private void resize() {
        setPreferredSize(compSize);
    }

    private void runStep() {
        render();
    }

    public void render() {
        fps();
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) screen.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, w, h);
        r.render(g);
        g.dispose();

        Graphics2D ctx = (Graphics2D) bs.getDrawGraphics();
        ctx.drawImage(screen, 0, 0, w * scale, h * scale, null);
        ctx.dispose();
        bs.show();
    }

    private void fps() {
        long now = System.currentTimeMillis();
        tempFPS++;
        if (now - lastFPStime >= 1000) {
            lastFPStime = now;
            fpsCount = tempFPS;
            tempFPS = 0;
            System.out.println(fpsCount);
        }
    }

    @Override
    public void updateReceived(String line) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void failedToConnect(IOException ex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //<editor-fold defaultstate="collapsed" desc="WindowListener crap...">
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        stop();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    //</editor-fold>
}
