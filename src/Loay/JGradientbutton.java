package Loay;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class JGradientbutton extends JButton {
    private float opacity = 1;
    public boolean en = true;

    public void setEn(boolean en) {
        this.en = en;
    }
//......
public void setOpacity(float opacity) {
    this.opacity = opacity;
}

public float getOpacity(){
    return this.opacity;
}


    public JGradientbutton() {

        super("Gradient Button");
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setOpacity(0.5f);
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setOpacity(1f);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                Color.decode("#1CB5E0"), 
                new Point(getWidth(), 0), 
                Color.decode("#000046")));
        if(en)g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}