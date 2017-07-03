/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listas_processamento_imagens;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Guilherme
 */
public class Lista2_Ex3 {

    public static void main(String args[]) {

        // int threshold = Integer.parseInt(JOptionPane.showInputDialog("Threshold: "));
        ImageIcon img1;

        URL url1 = Lista2_Ex3.class.getResource("../Imagens/yellow.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int r, g, b;
        int avg = 0;
        Color cor;
        double[] histogramaR = new double[256];
        double[] histogramaG = new double[256];
        double[] histogramaB = new double[256];

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                avg = (r + g + b) / 3;

                histogramaR[r]++;
                histogramaG[g]++;
                histogramaB[b]++;

                alterada.setRGB(i, j, new Color(avg, avg, avg).getRGB());
            }
        }

        JFrame frmOriginal = new JFrame("Imagem - Original");
        JPanel pan = new JPanel();
        JLabel lbl = new JLabel(new ImageIcon(original));
        pan.add(lbl);
        frmOriginal.getContentPane().add(pan);
        frmOriginal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOriginal.pack();
        frmOriginal.setVisible(true);

        JFreeChart barChart = ChartFactory.createBarChart("Histograma", "", "", createDataset(histogramaR, histogramaB, histogramaG), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cRed = new ChartPanel(barChart);

        JFrame frmHist = new JFrame("Histograma 1");
        frmHist.getContentPane().add(cRed);
        frmHist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHist.pack();
        frmHist.setVisible(true);

    }

    public static CategoryDataset createDataset(double[] histogramaR, double[] histogramaB, double[] histogramaG) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            dataset.addValue(histogramaR[i], "Red", "" + i);
            dataset.addValue(histogramaB[i], "Blue", "" + i);
            dataset.addValue(histogramaG[i], "Green", "" + i);
        }

        return dataset;
    }
}
