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

/**
 *
 * @author Guilherme
 */
public class Lista4_Ex3 {

    public static void main(String args[]) {

        ImageIcon img1, img2;

        URL url1 = Lista4_Ex3.class.getResource("../Imagens/img4.jpg");
        img1 = new ImageIcon(url1);

        url1 = Lista4_Ex3.class.getResource("../Imagens/fundoTransparente.png");
        img2 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.BITMASK);
        BufferedImage original2 = new BufferedImage(img2.getIconWidth(), img2.getIconHeight(), Transparency.BITMASK);
        BufferedImage alterada;
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);
        original2.getGraphics().drawImage(img2.getImage(), 0, 0, null);

        int r, g, b, r2, g2, b2, a, a2;
        Color cor, cor2;
        int altura, largura;

        if (original.getHeight() > original2.getHeight()) {
            altura = original.getHeight();
        } else {
            altura = original2.getHeight();
        }

        if (original.getWidth() > original2.getWidth()) {
            largura = original.getWidth();
        } else {
            largura = original2.getWidth();
        }

        alterada = new BufferedImage(largura, altura, Transparency.BITMASK);
        int aux = 0;

        for (int j = 0; j < altura; j++) {

            for (int i = 0; i < largura; i++) {
                aux = 0;
                cor = new Color(255, 255, 255);
                cor2 = new Color(255, 255, 255);

                if (isPontoDentroDaImagem(original, i, j)) {
                    cor = new Color(original.getRGB(i, j), true);
                    aux += 1;
                }
                if (isPontoDentroDaImagem(original2, i, j)) {
                    cor2 = new Color(original2.getRGB(i, j), true);
                    aux += 2;
                }
                a = cor.getAlpha();
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                a2 = cor2.getAlpha();
                r2 = cor2.getRed();
                g2 = cor2.getGreen();
                b2 = cor2.getBlue();

                switch (aux) {
                    //somente  imagem 1
                    case 1:
                        alterada.setRGB(i, j, new Color(r, g, b).getRGB());
                        break;
                    // somente imagem 2
                    case 2:
                        alterada.setRGB(i, j, new Color(r2, g2, b2).getRGB());
                        break;
                    // duas imagens
                    case 3:
                        if (a != 0) {
                            alterada.setRGB(i, j, new Color(r2, g2, b2).getRGB());
                        }
                        //alterada.setRGB(i, j, new Color(r,g,b).getRGB());
                        if (a2 == 0) //alterada.setRGB(i, j, new Color(r2,g2,b2).getRGB());
                        {
                            alterada.setRGB(i, j, new Color(r, g, b).getRGB());
                        }
                        break;
                    default:
                        break;
                }
            }
        }

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

}
