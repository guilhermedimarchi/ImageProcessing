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
public class Lista2_Ex4 {

    public static void main(String args[]) {

        // int threshold = Integer.parseInt(JOptionPane.showInputDialog("Threshold: "));
        ImageIcon img1;

        //URL url1 = Lista2_Ex4.class.getResource("../Imagens/meioMeio.jpg");        // URL url1 = Lista2_Ex4.class.getResource("../Imagens/maisEscura.jpg");
        //URL url1 = Lista2_Ex4.class.getResource("../Imagens/maisClaro.jpg");
        URL url1 = Lista2_Ex4.class.getResource("../Imagens/maisEscura.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int r, g, b;
        int avg = 0, qtd = 0;
        Color cor;
        double[] histograma = new double[256];
        double somatoria = 0;

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                avg = (r + g + b) / 3;

                histograma[avg]++;
                somatoria += avg;
                qtd++;

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

//        JFrame frmAlterada = new JFrame("Alterada");
//        JPanel pan2 = new JPanel();
//        JLabel lbl2 = new JLabel(new ImageIcon(alterada));
//        pan2.add(lbl2);
//        frmAlterada.getContentPane().add(pan2);
//        frmAlterada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frmAlterada.pack();
//        frmAlterada.setVisible(true);
//
        JFreeChart barChart = ChartFactory.createBarChart("Histograma", "", "", createDataset(histograma), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cRed = new ChartPanel(barChart);

        JFrame frmHist = new JFrame("Histograma 1");
        frmHist.getContentPane().add(cRed);
        frmHist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHist.pack();
        frmHist.setVisible(true);

        classifica(somatoria, qtd);

    }

    public static CategoryDataset createDataset(double[] histograma) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            dataset.addValue(histograma[i], "", "" + i);
        }

        return dataset;
    }

    public static void classifica(double somatoria, int qtd) {

        somatoria = somatoria / qtd;

        if (somatoria > 170) {
            System.out.println("Imagem clara");
        } else if (somatoria > 85) {
            System.out.println("Indefinido");
        } else {
            System.out.println("Imagem escura");
        }

    }

}
