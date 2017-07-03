/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listas_processamento_imagens;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Guilherme
 */
public class Lista5_Ex6 {

    public static void main(String args[]) {

        int tamJanela = Integer.parseInt(JOptionPane.showInputDialog("3 - Janela 3x3\n5 - Janela 5x5"));
        int coloridoOuCinza = Integer.parseInt(JOptionPane.showInputDialog("1 - Colorido\n2 - Cinza"));

        double[][] janelaX = geraMatrix(tamJanela, 1);
        double[][] janelaY = geraMatrix(tamJanela, 2);

        ImageIcon img1;

        URL url1 = Lista5_Ex6.class.getResource("../Imagens/img4.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original;
        BufferedImage alterada;
        if (coloridoOuCinza == 1) {
            original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
            alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        } else {
            original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), BufferedImage.TYPE_BYTE_GRAY);
            alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), BufferedImage.TYPE_BYTE_GRAY);
        }
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int rx = 0, gx = 0, bx = 0;
        int ry = 0, gy = 0, by = 0;
        Color cor;
        int meioJanela = (int) tamJanela / 2;

        for (int j = meioJanela; j < original.getHeight() - (meioJanela); j++) {

            for (int i = meioJanela; i < original.getWidth() - (meioJanela); i++) {
                rx = 0;
                gx = 0;
                bx = 0;
                ry = 0;
                gy = 0;
                by = 0;

                for (int k = 0; k < tamJanela; k++) {
                    for (int m = 0; m < tamJanela; m++) {
                        cor = new Color((int) (original.getRGB((i - meioJanela) + k, (j - meioJanela) + m)));
                        rx += cor.getRed() * janelaX[k][m];
                        gx += cor.getGreen() * janelaX[k][m];
                        bx += cor.getBlue() * janelaX[k][m];

                        ry += cor.getRed() * janelaX[k][m];
                        gy += cor.getGreen() * janelaX[k][m];
                        by += cor.getBlue() * janelaX[k][m];
                    }
                }

                rx = (int) Math.sqrt((rx * rx) + (ry * ry));
                gx = (int) Math.sqrt((gx * gx) + (gy * gy));
                bx = (int) Math.sqrt((bx * bx) + (by * by));

                if (rx > 255) {
                    rx = 255;
                }
                if (gx > 255) {
                    gx = 255;
                }
                if (bx > 255) {
                    bx = 255;
                }

                if (rx < 0) {
                    rx = 0;
                }
                if (gx < 0) {
                    gx = 0;
                }
                if (bx < 0) {
                    bx = 0;
                }

                alterada.setRGB(i, j, new Color(rx, gx, bx).getRGB());

            }
        }
        JFrame frmOriginal = new JFrame("Teste Imagem - Original");
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

    }

    public static double[][] geraMatrix(int tamJanela, int op) {
        double[][] janela;// = new float[tamJanela][tamJanela];
        switch (tamJanela) {
            case 3:
                if (op == 1) {
                    janela = new double[][]{{1, 0, -1},
                    {2, 0, -2},
                    {1, 0, -1}};

                } else {
                    janela = new double[][]{{-1, -2, -1},
                    {0, 0, 0,},
                    {1, 2, 1}};

                }
                break;
            default:
                if (op == 1) {
                    janela = new double[][]{{1, 2, 0, -2, -1},
                    {4, 8, 0, -8, -4},
                    {6, 12, 0, -12, -6},
                    {4, 8, 0, -8, -4},
                    {1, 2, 0, -2, 1}};
                } else {
                    janela = new double[][]{{-1, -4, -6, -4, -1},
                    {-2, -8, -12, -8, -2},
                    {0, 0, 0, 0, 0},
                    {2, 8, 12, 8, 2},
                    {1, 4, 6, 4, 1}};
                }
                break;

        }
        return janela;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
