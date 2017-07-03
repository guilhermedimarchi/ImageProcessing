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
public class Lista1_Ex2 {

    public static void main(String args[]) {
        ImageIcon img1;

        URL url1 = Lista1_Ex2.class.getResource("../Imagens/cmy.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage cImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage mImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage yImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);

        BufferedImage rImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage gImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage bImg = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);

        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int r, g, b;
        float c, m, y;
        Color cor;

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                c = 1 - (r / 255);
                m = 1 - (g / 255);
                y = 1 - (b / 255);

                rImg.setRGB(i, j, new Color(c, 0, 0).getRGB());
                gImg.setRGB(i, j, new Color(0, m, 0).getRGB());
                bImg.setRGB(i, j, new Color(0, 0, y).getRGB());

                cImg.setRGB(i, j, new Color(r, 255, 255).getRGB());
                mImg.setRGB(i, j, new Color(255, g, 255).getRGB());
                yImg.setRGB(i, j, new Color(255, 255, b).getRGB());
            }
        }

        JFrame frmOriginal = new JFrame("Original");
        JPanel pan = new JPanel();
        JLabel lbl = new JLabel(new ImageIcon(original));
        pan.add(lbl);
        frmOriginal.getContentPane().add(pan);
        frmOriginal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOriginal.pack();
        frmOriginal.setVisible(true);

        JFrame frmR = new JFrame("rImg");
        JPanel panR = new JPanel();
        JLabel lblR = new JLabel(new ImageIcon(rImg));
        panR.add(lblR);
        frmR.getContentPane().add(panR);
        frmR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmR.pack();
        frmR.setVisible(true);

        JFrame frmG = new JFrame("gImg");
        JPanel panG = new JPanel();
        JLabel lblG = new JLabel(new ImageIcon(gImg));
        panG.add(lblG);
        frmG.getContentPane().add(panG);
        frmG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmG.pack();
        frmG.setVisible(true);

        JFrame frmB = new JFrame("bImg");
        JPanel panB = new JPanel();
        JLabel lblB = new JLabel(new ImageIcon(bImg));
        panB.add(lblB);
        frmB.getContentPane().add(panB);
        frmB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmB.pack();
        frmB.setVisible(true);

        JFrame frmC = new JFrame("cImg");
        JPanel panC = new JPanel();
        JLabel lblC = new JLabel(new ImageIcon(cImg));
        panC.add(lblC);
        frmC.getContentPane().add(panC);
        frmC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmC.pack();
        frmC.setVisible(true);

        JFrame frmM = new JFrame("mImg");
        JPanel panM = new JPanel();
        JLabel lblM = new JLabel(new ImageIcon(mImg));
        panM.add(lblM);
        frmM.getContentPane().add(panM);
        frmM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmM.pack();
        frmM.setVisible(true);

        JFrame frmY = new JFrame("yImg");
        JPanel panY = new JPanel();
        JLabel lblY = new JLabel(new ImageIcon(yImg));
        panY.add(lblY);
        frmY.getContentPane().add(panY);
        frmY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmY.pack();
        frmY.setVisible(true);

    }
}
