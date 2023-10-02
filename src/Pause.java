import java.awt.*;

public class Pause {

    public Rectangle pauseScreen = new Rectangle(300, 50, 250, 500);

    public Rectangle resumeButton = new Rectangle(350, 100, 150, 50);
    public Rectangle restartButton = new Rectangle(350, 200, 150, 50);
    public Rectangle mainMenuButton = new Rectangle(350, 300, 150, 50);
    public Rectangle exitButton = new Rectangle(350, 400, 150, 50);

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.gray);
        g.fillRect(300, 50, 250, 500);

        g.setColor(Color.white);
        Font buttonFont = new Font("Arial", Font.BOLD, 28);
        drawCenteredString(g, "Resume", resumeButton, buttonFont);
        drawCenteredString(g, "Restart", restartButton, buttonFont);
        drawCenteredString(g, "Main Menu", mainMenuButton, buttonFont);
        drawCenteredString(g, "Exit", exitButton, buttonFont);

        g2d.draw(pauseScreen);
        g2d.draw(resumeButton);
        g2d.draw(restartButton);
        g2d.draw(mainMenuButton);
        g2d.draw(exitButton);
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
