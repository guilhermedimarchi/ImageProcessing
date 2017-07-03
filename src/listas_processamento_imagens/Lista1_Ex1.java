/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class Lista1_Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ImageIcon img1;

        URL url1 = Lista1_Ex1.class.getResource("../Imagens/yellow.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage originalImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage redImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage greenImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage blueImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        originalImg.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int r, g, b;
        Color cor;

        for (int j = 0; j < originalImg.getHeight(); j++) {

            for (int i = 0; i < originalImg.getWidth(); i++) {
                cor = new Color(originalImg.getRGB(i, j));
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                redImg.setRGB(i, j, new Color(r, r, r).getRGB());

                greenImg.setRGB(i, j, new Color(g, g, g).getRGB());

                blueImg.setRGB(i, j, new Color(b, b, b).getRGB());

            }

        }

        JFrame frmOriginal = new JFrame("Original image");
        JPanel pan = new JPanel();
        JLabel lbl = new JLabel(new ImageIcon(originalImg));
        pan.add(lbl);
        frmOriginal.getContentPane().add(pan);
        frmOriginal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOriginal.pack();
        frmOriginal.setVisible(true);

        JFrame frmRed = new JFrame("Red Image");
        JPanel pan2 = new JPanel();
        JLabel lbl2 = new JLabel(new ImageIcon(redImg));
        pan2.add(lbl2);
        frmRed.getContentPane().add(pan2);
        frmRed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRed.pack();
        frmRed.setVisible(true);

        JFrame frmGreen = new JFrame("Green Image");
        JPanel pan3 = new JPanel();
        JLabel lbl3 = new JLabel(new ImageIcon(greenImg));
        pan3.add(lbl3);
        frmGreen.getContentPane().add(pan3);
        frmGreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGreen.pack();
        frmGreen.setVisible(true);

        JFrame frmBlue = new JFrame("Blue Image");
        JPanel pan4 = new JPanel();
        JLabel lbl4 = new JLabel(new ImageIcon(blueImg));
        pan4.add(lbl4);
        frmBlue.getContentPane().add(pan4);
        frmBlue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBlue.pack();
        frmBlue.setVisible(true);

    }

}
