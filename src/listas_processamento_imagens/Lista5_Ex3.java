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
public class Lista5_Ex3 {

    public static void main(String args[]) {

        int tamJanela = Integer.parseInt(JOptionPane.showInputDialog("3 - Janela 3x3\n5 - Janela 5x5\n7 - Janela 7x7\n9 - Janela 9x9"));
        int coloridoOuCinza = Integer.parseInt(JOptionPane.showInputDialog("1 - Colorido\n2 - Cinza"));

        ImageIcon img1;

        URL url1 = Lista5_Ex3.class.getResource("../Imagens/ruido.jpg");
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

        int[] r = new int[tamJanela * tamJanela];
        int[] g = new int[tamJanela * tamJanela];
        int[] b = new int[tamJanela * tamJanela];

        Color cor;

        int meioJanela = (int) tamJanela / 2;

        alterada = deepCopy(original);
        for (int j = meioJanela; j < original.getHeight() - (meioJanela); j++) {

            for (int i = meioJanela; i < original.getWidth() - (meioJanela); i++) {

                for (int k = 0; k < tamJanela; k++) {
                    for (int m = 0; m < tamJanela; m++) {
                        cor = new Color((int) (original.getRGB((i - meioJanela) + k, (j - meioJanela) + m)));
                        r[(k * tamJanela) + m] = cor.getRed();
                        g[(k * tamJanela) + m] = cor.getGreen();
                        b[(k * tamJanela) + m] = cor.getBlue();
                    }
                }
                r = ordenar(r);
                g = ordenar(g);
                b = ordenar(b);
                int pos = (tamJanela * tamJanela) / 2;

                alterada.setRGB(i, j, new Color(r[pos], g[pos], b[pos]).getRGB());
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

    private static int[] ordenar(int[] numArray) {

        int n = numArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numArray[j - 1] > numArray[j]) {
                    temp = numArray[j - 1];
                    numArray[j - 1] = numArray[j];
                    numArray[j] = temp;
                }
            }
        }
        return numArray;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
