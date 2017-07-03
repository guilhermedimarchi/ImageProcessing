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
public class Lista5_Ex5 {

    public static void main(String args[]) {

        int tamJanela = Integer.parseInt(JOptionPane.showInputDialog("3 - Janela 3x3\n5 - Janela 5x5\n7 - Janela 7x7\n9 - Janela 9x9"));
        int coloridoOuCinza = Integer.parseInt(JOptionPane.showInputDialog("1 - Colorido\n2 - Cinza"));
        int verticalOuHorizontal = Integer.parseInt(JOptionPane.showInputDialog("1 - Horizontal\n2 - Vertical\n3 - Diagonal primaria\n4 - Diagonal secundaria"));

        double[][] janela = geraMatrix(tamJanela, verticalOuHorizontal);

        ImageIcon img1;

        URL url1 = Lista5_Ex5.class.getResource("../Imagens/figuras.jpg");
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

        int r = 0, g = 0, b = 0;
        Color cor;

        int tam = tamJanela * tamJanela;
        int meioJanela = (int) tamJanela / 2;

        for (int j = meioJanela; j < original.getHeight() - (meioJanela); j++) {

            for (int i = meioJanela; i < original.getWidth() - (meioJanela); i++) {
                r = 0;
                g = 0;
                b = 0;

                for (int k = 0; k < tamJanela; k++) {
                    for (int m = 0; m < tamJanela; m++) {
                        cor = new Color((int) (original.getRGB((i - meioJanela) + k, (j - meioJanela) + m)));
                        r += cor.getRed() * janela[k][m];
                        g += cor.getGreen() * janela[k][m];
                        b += cor.getBlue() * janela[k][m];
                    }
                }

                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }

                if (r < 0) {
                    r = 0;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b < 0) {
                    b = 0;
                }

                alterada.setRGB(i, j, new Color(r, g, b).getRGB());

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

    public static double[][] geraMatrix(int tamJanela, int verticalOuHorizontal) {
        double[][] janela;// = new float[tamJanela][tamJanela];
        switch (tamJanela) {
            case 3:
                switch (verticalOuHorizontal) {
                    case 1:
                        janela = new double[][]{{-1, 2, -1},
                        {-1, 2, -1},
                        {-1, 2, -1}};
                        break;
                    case 2:
                        janela = new double[][]{{-1, -1, -1},
                        {2, 2, 2},
                        {-1, -1, -1}};
                        break;
                    case 3:
                        janela = new double[][]{{-1, -1, 1},
                        {-1, 1, -1},
                        {1, -1, -1}};
                        break;
                    default:
                        janela = new double[][]{{1, -1, -1},
                        {-1, 1, -1},
                        {-1, -1, 1}};
                        break;
                }
                break;
            case 5:
                switch (verticalOuHorizontal) {
                    case 1:
                        janela = new double[][]{{-1, -1, 1, -1, -1},
                        {-1, -1, 4, -1, -1},
                        {-1, -1, 8, -1, -1},
                        {-1, -1, 4, -1, -1},
                        {-1, -1, 1, -1, -1}};
                        break;
                    case 2:
                        janela = new double[][]{{-1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1},
                        {1, 4, 8, 4, 1},
                        {-1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1}};
                        break;
                    case 3:
                        janela = new double[][]{{-1, -1, -1, -1, 1},
                        {-1, -1, -1, 2, -1},
                        {-1, -1, 6, -1, -1},
                        {-1, 2, -1, -1, -1},
                        {1, -1, -1, -1, -1}};
                        break;
                    default:
                        janela = new double[][]{{1, -1, -1, -1, -1},
                        {-1, 2, -1, -1, -1},
                        {-1, -1, 6, -1, -1},
                        {-1, -1, -1, 2, -1},
                        {-1, -1, -1, -1, 1}};
                        break;
                }
                break;
            case 7:
                switch (verticalOuHorizontal) {
                    case 1:
                        janela = new double[][]{{-1, -1, -1, 2, -1, -1, -1},
                        {-1, -1, -1, 4, -1, -1, -1},
                        {-1, -1, -1, 8, -1, -1, -1},
                        {-1, -1, -1, 10, -1, -1, -1},
                        {-1, -1, -1, 8, -1, -1, -1},
                        {-1, -1, -1, 4, -1, -1, -1},
                        {-1, -1, -1, 2, -1, -1, -1}};
                        break;
                    case 2:
                        janela = new double[][]{{-1, -1, -1, 1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1, -1},
                        {1, 4, 8, 10, 8, 4, 1},
                        {-1, -1, -1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1, -1}};
                        break;
                    case 3:
                        janela = new double[][]{{-1, -1, -1, 1, -1, -1, 1},
                        {-1, -1, -1, -1, -1, 2, -1},
                        {-1, -1, -1, -1, 4, -1, -1},
                        {-1, -1, -1, 8, -1, -1, -1},
                        {-1, -1, 4, -1, -1, -1, -1},
                        {-1, 2, -1, -1, -1, -1, -1},
                        {1, -1, -1, -1, -1, -1, -1}};
                        break;
                    default:
                        janela = new double[][]{{1, -1, -1, 1, -1, -1, -1},
                        {-1, 2, -1, -1, -1, -1, -1},
                        {-1, -1, 4, -1, -1, -1, -1},
                        {-1, -1, -1, 8, -1, -1, -1},
                        {-1, -1, -1, -1, 4, -1, -1},
                        {-1, -1, -1, -1, -1, 2, -1},
                        {-1, -1, -1, -1, -1, -1, 1}};
                        break;
                }
                break;
            default:
                if (verticalOuHorizontal == 1) {
                    janela = new double[][]{{-1, -1, -1, -1, 1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 4, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 6, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 8, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 10, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 8, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 6, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 4, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 1, -1, -1, -1, -1}};
                } else if (verticalOuHorizontal == 2) {
                    janela = new double[][]{{-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {1, 4, 6, 8, 10, 8, 6, 4, 1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1}};
                } else if (verticalOuHorizontal == 3) {
                    janela = new double[][]{{-1, -1, -1, -1, -1, -1, -1, -1, 2},
                    {-1, -1, -1, -1, -1, -1, -1, 2, -1},
                    {-1, -1, -1, -1, -1, -1, 2, -1, -1},
                    {-1, -1, -1, -1, -1, 2, -1, -1, -1},
                    {-1, -1, -1, -1, 2, -1, -1, -1, -1},
                    {-1, -1, -1, 2, -1, -1, -1, -1, -1},
                    {-1, -1, 2, -1, -1, -1, -1, -1, -1},
                    {-1, 2, -1, -1, -1, -1, -1, -1, -1},
                    {1, -1, -1, -1, -1, -1, -1, -1, -1}};
                } else {
                    janela = new double[][]{{2, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, 2, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 2, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, 2, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, 2, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, 2, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, 2, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, 2, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, 2}};
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
