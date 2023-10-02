import java.awt.*;

public class GameMode {

    public Rectangle pvpButton = new Rectangle(350, 250, 150, 50);
    public Rectangle pveButton = new Rectangle(350, 400, 150, 50);

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 630);

        Font font = new Font("Arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString(g, "GAME MODE", new Rectangle(25, 100, 800, 50), font);

        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        drawCenteredString(g, "PVP", pvpButton, buttonFont);
        drawCenteredString(g, "PVE", pveButton, buttonFont);

        g2d.draw(pvpButton);
        g2d.draw(pveButton);
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
