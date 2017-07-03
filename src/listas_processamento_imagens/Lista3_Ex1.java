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
import javax.swing.JOptionPane;
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
public class Lista3_Ex1 {

    public static void main(String args[]) {
        float deslocamento = (Float.parseFloat(JOptionPane.showInputDialog("Brilho adicional (%): ")) / 100) * 255;

        ImageIcon img1;
        URL url1 = Lista3_Ex1.class.getResource("../Imagens/yellow.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        float r, g, b;
        Color cor;
        float[] hls = {0, 0, 0};
        int[] rgb = {0, 0, 0};

        float[] histogramaR = new float[256];
        float[] histogramaG = new float[256];
        float[] histogramaB = new float[256];

        float[] histogramaR2 = new float[256];
        float[] histogramaG2 = new float[256];
        float[] histogramaB2 = new float[256];

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));

                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                histogramaR[(int) r]++;
                histogramaG[(int) g]++;
                histogramaB[(int) b]++;

                r += deslocamento;
                g += deslocamento;
                b += deslocamento;

                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }

                histogramaR2[(int) r]++;
                histogramaG2[(int) g]++;
                histogramaB2[(int) b]++;

                alterada.setRGB(i, j, new Color((int) r, (int) g, (int) b).getRGB());
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

        JFreeChart barChart2 = ChartFactory.createBarChart("Histograma alterado", "", "", createDataset(histogramaR2, histogramaB2, histogramaG2), PlotOrientation.VERTICAL, true, true, false);
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

    public static int[] hlsToRgb(float h, float l, float s) {
        float r, g, b;

        if (s == 0f) {
            r = g = b = l;
        } else {
            float q = l < 0.5f ? l * (1 + s) : l + s - l * s;
            float p = 2 * l - q;
            r = hueToRgb(p, q, h + 1f / 3f);
            g = hueToRgb(p, q, h);
            b = hueToRgb(p, q, h - 1f / 3f);
        }
        int[] rgb = {(int) (r * 255), (int) (g * 255), (int) (b * 255)};
        return rgb;
    }

    public static float hueToRgb(float p, float q, float t) {
        if (t < 0f) {
            t += 1f;
        }
        if (t > 1f) {
            t -= 1f;
        }
        if (t < 1f / 6f) {
            return p + (q - p) * 6f * t;
        }
        if (t < 1f / 2f) {
            return q;
        }
        if (t < 2f / 3f) {
            return p + (q - p) * (2f / 3f - t) * 6f;
        }
        return p;
    }

    public static float[] rgbHls(float r, float g, float b) {
        float h = 0, s = 0, l = 0, aux = 0;

        r = r / 255;
        g = g / 255;
        b = b / 255;

        float max = max(r, g, b);
        float min = min(r, g, b);

        l = (max + min) / 2;

        if (max == min) {
            h = s = 0;
        } else {
            aux = max - min;
            s = l > 0.5 ? aux / (2 - max - min) : aux / (max + min);

            if (max == r) {
                h = (g - b) / aux + (g < b ? 6 : 0);
            } else if (max == g) {
                h = (b - r) / aux + 2;
            } else if (max == b) {
                h = (r - g) / aux + 4;
            }
            h /= 6;

        }

        return new float[]{h, l, s};
    }

    public static float max(float r, float g, float b) {
        float max = r;
        if (g > max) {
            max = g;
        }
        if (b > max) {
            max = b;
        }
        return max;
    }

    public static float min(float r, float g, float b) {
        float min = r;
        if (g < min) {
            min = g;
        }
        if (b < min) {
            min = b;
        }
        return min;
    }

    public static void deslocaHistograma(float[] histograma, float deslocamento) {
        deslocamento = deslocamento * 255;
        for (int i = 255; i >= 0; i--) {
            if (i + ((int) deslocamento) >= 255) {
                histograma[255] += histograma[i];
            } else {
                histograma[i + ((int) deslocamento)] = histograma[i];
                histograma[i] = 0;
            }

        }

        // return histograma;
    }

}
