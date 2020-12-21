package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import java.awt.*;

public class OperationsWithFunctions extends JDialog {
    public OperationsWithFunctions (Menu menu, String s, Boolean modal){
        super(menu,s, modal);

        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(500,500));
    }
}
