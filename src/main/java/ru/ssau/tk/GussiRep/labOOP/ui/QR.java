package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import java.awt.*;

public class QR extends JDialog {
    public QR(Menu menu, String name, boolean modal) {
        super(menu, name, modal);
        drawQR();
        setVisible(false);
        setSize(526, 556);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void drawQR() {
        Image image = new ImageIcon("235544_2.jpg").getImage();
        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        });
    }
}
