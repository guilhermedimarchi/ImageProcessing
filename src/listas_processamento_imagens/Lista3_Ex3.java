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
public class Lista3_Ex3 {

    public static void main(String args[]) {
        ImageIcon img1;

        URL url1 = Lista3_Ex3.class.getResource("../Imagens/igreja.png");
        //URL url1 = BaseImg.class.getResource("desert.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);// equalize(new BufferedImage( img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE));
        BufferedImage alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        float r, g, b;
        int red, green, blue;
        Color cor;

        float[] histogramaR = new float[256];
        float[] histogramaG = new float[256];
        float[] histogramaB = new float[256];

        float[] histogramaR2 = new float[256];
        float[] histogramaG2 = new float[256];
        float[] histogramaB2 = new float[256];

        float[] histogramaR3 = new float[256];
        float[] histogramaG3 = new float[256];
        float[] histogramaB3 = new float[256];

        //Montar histograma original
        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));

                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                histogramaR[(int) r]++;
                histogramaG[(int) g]++;
                histogramaB[(int) b]++;
                //alterada.setRGB(i, j, new Color((int)r,(int)g,(int)b).getRGB()); 
            }
        }

        histogramaR2[0] = histogramaR[0];
        histogramaG2[0] = histogramaG[0];
        histogramaB2[0] = histogramaB[0];

        for (int i = 1; i < histogramaR.length; i++) {
            histogramaR2[i] = histogramaR2[i - 1] + histogramaR[i];
            histogramaG2[i] = histogramaG2[i - 1] + histogramaG[i];
            histogramaB2[i] = histogramaB2[i - 1] + histogramaB[i];
        }

        int numberOfPixels = original.getWidth() * original.getHeight();
        float minRed = min(histogramaR2);
        float minGreen = min(histogramaG2);
        float minBlue = min(histogramaB2);

        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {

                cor = new Color(original.getRGB(x, y));

                red = cor.getRed();
                green = cor.getGreen();
                blue = cor.getBlue();

                red = (int) ((((double) histogramaR2[red] - minRed) / (numberOfPixels - minRed)) * 255);
                green = (int) ((((double) histogramaG2[green] - minGreen) / (numberOfPixels - minGreen)) * 255);
                blue = (int) ((((double) histogramaB2[blue] - minBlue) / (numberOfPixels - minBlue)) * 255);

                histogramaR3[red]++;
                histogramaG3[green]++;
                histogramaB3[blue]++;

                alterada.setRGB(x, y, new Color(red, green, blue).getRGB());
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

        JFrame frmAlterada = new JFrame("Alterada");
        JPanel pan2 = new JPanel();
        JLabel lbl2 = new JLabel(new ImageIcon(alterada));
        pan2.add(lbl2);
        frmAlterada.getContentPane().add(pan2);
        frmAlterada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAlterada.pack();
        frmAlterada.setVisible(true);

        JFreeChart barChart = ChartFactory.createBarChart("Histograma original", "", "", createDataset(histogramaR, histogramaB, histogramaG), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cRed = new ChartPanel(barChart);
        JFrame frmHist = new JFrame("Histograma 1");
        frmHist.getContentPane().add(cRed);
        frmHist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHist.pack();
        frmHist.setVisible(true);

//         JFreeChart barChart2 = ChartFactory.createBarChart("Histograma alterado",  "", "",  createDataset(histogramaR2,histogramaB2,histogramaG2),PlotOrientation.VERTICAL, true, true, false);
        JFreeChart barChart2 = ChartFactory.createBarChart("Histograma alterado", "", "", createDataset(histogramaR3, histogramaB3, histogramaG3), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cRed2 = new ChartPanel(barChart2);
        JFrame frmHist2 = new JFrame("Histograma 2");
        frmHist2.getContentPane().add(cRed2);
        frmHist2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHist2.pack();
        frmHist2.setVisible(true);
    }

    public static CategoryDataset createDataset(float[] histogramaR, float[] histogramaB, float[] histogramaG) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            dataset.addValue(histogramaR[i], "Red", "" + i);
            dataset.addValue(histogramaB[i], "Blue", "" + i);
            dataset.addValue(histogramaG[i], "Green", "" + i);
        }
        return dataset;
    }

    public static float min(float[] arr) {
        float min = -1;
        for (int i = 0; i < arr.length; i++) {
            if (min == -1 || arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

}
