package ru.ssau.tk.GussiRep.labOOP.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import javax.swing.*;

public class ShowChart extends JDialog {
    private TabulatedFunction function;

    public ShowChart(TabulatedFunction function, CreateChart menu, String s, boolean modal) {
        super(menu, s, modal);
        this.function = function;
        createXY();
        setVisible(false);
        setSize(500, 500);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void createXY() {
        XYSeries series = new XYSeries("график");
        for (int i = 0; i < function.getCount(); i++) {
            series.add(function.getX(i), function.getY(i));
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("Табулированная функция", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        getContentPane().add(new ChartPanel(chart));
    }

}
