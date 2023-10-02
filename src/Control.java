import java.awt.*;
import javax.swing.*;

public class Control extends JPanel {
    private ImageIcon keyUp;
    private ImageIcon keyDown;
    private ImageIcon keyLeft;
    private ImageIcon keyRight;
    private ImageIcon keyShoot1;
    private ImageIcon keyShoot2;
    private ImageIcon keyW;
    private ImageIcon keyA;
    private ImageIcon keyS;
    private ImageIcon keyD;
    private ImageIcon keyEsc;
    private ImageIcon keyBack;

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 630);

        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Player 1", 325, 75);
        g.drawString("Player 2", 575, 75);

        g.drawString("MOVE UP", 75, 130);
        g.drawString("MOVE DOWN", 75, 200);
        g.drawString("MOVE LEFT", 75, 270);
        g.drawString("MOVE RIGHT", 75, 340);
        g.drawString("SHOOT", 75, 410);
        g.drawString("PAUSE", 75, 480);

        keyUp = new ImageIcon("key_up.png");
        keyUp.paintIcon(this, g2d, 350, 100);

        keyDown = new ImageIcon("key_down.png");
        keyDown.paintIcon(this, g2d, 350, 170);

        keyLeft = new ImageIcon("key_left.png");
        keyLeft.paintIcon(this, g2d, 350, 240);

        keyRight = new ImageIcon("key_right.png");
        keyRight.paintIcon(this, g2d, 350, 310);

        keyShoot1 = new ImageIcon("key_u.png");
        keyShoot1.paintIcon(this, g2d, 350, 380);

        keyW = new ImageIcon("key_w.png");
        keyW.paintIcon(this, g2d, 600, 100);

        keyA = new ImageIcon("key_a.png");
        keyA.paintIcon(this, g2d, 600, 170);

        keyS = new ImageIcon("key_s.png");
        keyS.paintIcon(this, g2d, 600, 240);

        keyD = new ImageIcon("key_d.png");
        keyD.paintIcon(this, g2d, 600, 310);

        keyShoot2 = new ImageIcon("key_m.png");
        keyShoot2.paintIcon(this, g2d, 600, 380);

        keyEsc = new ImageIcon("key_esc.png");
        keyEsc.paintIcon(this, g2d, 450, 450);

        keyBack = new ImageIcon("keyBack.png");
        keyBack.paintIcon(this, g2d, 0, 0);
    }
}
