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
public class Lista5_Ex2 {

    public static void main(String args[]) {

        int tamJanela = Integer.parseInt(JOptionPane.showInputDialog("3 - Janela 3x3\n5 - Janela 5x5\n7 - Janela 7x7\n9 - Janela 9x9"));
        int coloridoOuCinza = Integer.parseInt(JOptionPane.showInputDialog("1 - Colorido\n2 - Cinza"));

        ImageIcon img1;

        URL url1 = Lista5_Ex2.class.getResource("../Imagens/coala.jpg");
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

        int[] r;
        int[] g;
        int[] b;
        int rMaisUsado, bMaisUsado, gMaisUsado;
        Color cor;

        int meioJanela = (int) tamJanela / 2;

        alterada = deepCopy(original);

        for (int j = meioJanela; j < original.getHeight() - (meioJanela); j++) {

            for (int i = meioJanela; i < original.getWidth() - (meioJanela); i++) {
                rMaisUsado = 0;
                gMaisUsado = 0;
                bMaisUsado = 0;
                r = new int[256];
                g = new int[256];
                b = new int[256];

                for (int k = 0; k < tamJanela; k++) {
                    for (int m = 0; m < tamJanela; m++) {
                        cor = new Color((int) (original.getRGB((i - meioJanela) + k, (j - meioJanela) + m)));
                        r[cor.getRed()]++;
                        g[cor.getGreen()]++;
                        b[cor.getBlue()]++;
                    }
                }
                rMaisUsado = max(r);
                gMaisUsado = max(g);
                bMaisUsado = max(b);

                alterada.setRGB(i, j, new Color(rMaisUsado, gMaisUsado, bMaisUsado).getRGB());
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

    private static boolean isPontoDentroDaImagem(BufferedImage original, int i, int j) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (i >= original.getWidth() || j >= original.getHeight()) {
            return false;
        }

        return true;
    }

    public static int max(int[] lista) {
        int max = 0;
        int end = 0;

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] > max) {
                max = lista[i];
                end = i;
            }
        }
        return end;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
