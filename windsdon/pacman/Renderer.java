package windsdon.pacman;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;

/**
 *
 * @author Windsdon
 */
class Renderer {

    private Font nameTagFont = new Font("Lucida Console", Font.PLAIN, 16);

    public void render(Graphics2D g) {
    }

    void drawNameTag(Graphics2D g, Player player) {
        Composite oldComposite = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        AttributedCharacterIterator textIterator = TextFormater.get(player.name, Color.white, nameTagFont).getIterator();
        TextLayout layout = new TextLayout(textIterator, g.getFontRenderContext());
        g.setColor(Color.black);
        g.fill(layout.getBounds());
        g.setComposite(oldComposite);
        layout.draw(g, player.getX() / 2f - ((float) layout.getBounds().getWidth()) / 2f, player.getY() - ((float) player.image.getHeight()) / 2f - layout.getDescent());
        
    }
}
