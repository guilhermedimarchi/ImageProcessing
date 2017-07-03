/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listas_processamento_imagens;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Guilherme
 */
public class Lista3_Ex4 {

    public static void main(String args[]) {

        int threshold = 240;//Integer.parseInt(JOptionPane.showInputDialog("Threshold: "));

        //binarizar a imagem
        //percorrer pixels ate encontrar valor
        //quando encontrar empilhar e ir apagando os pixels
        //comecar a percorrer pixels novamente
        ImageIcon img1;

          URL url1 = Lista3_Ex4.class.getResource("../Imagens/contaFiguras.jpg");
        //URL url1 = Lista3_Ex4.class.getResource("../Imagens/figuras.jpg");
        //URL url1 = Lista3_Ex4.class.getResource("../Imagens/figuras2.jpg");
        img1 = new ImageIcon(url1);

        BufferedImage original = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage alterada = new BufferedImage(img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(), 0, 0, null);

        int r, g, b;
        Color cor;
        int avg = 0;
        int[][] matriz = new int[img1.getIconWidth()][img1.getIconHeight()];

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {
                cor = new Color(original.getRGB(i, j));
                r = cor.getRed();
                g = cor.getGreen();
                b = cor.getBlue();

                avg = (r + g + b) / 3;

                if (avg >= threshold) {
                    avg = 255;
                    matriz[i][j] = 0;
                } else {
                    avg = 0;
                    matriz[i][j] = 1;
                }
                alterada.setRGB(i, j, new Color(avg, avg, avg).getRGB());
            }

        }
//        for(int j=0; j < original.getHeight(); j++) {
//            
//            for(int i=0; i < original.getWidth(); i++) 
//            {
//                System.out.print(matriz[i][j]);
//            }
//            System.out.println(" ");
//        }

        Stack stack = new Stack();
        int qtdFiguras = 0;
        //XYCoordinate pos;
        MyCoord pos = new MyCoord();
        MyCoord posAtual = new MyCoord();

        int k = 0;

        for (int j = 0; j < original.getHeight(); j++) {

            for (int i = 0; i < original.getWidth(); i++) {

                if (matriz[i][j] == 1) {
                    //matriz[i][j]=0;
                    pos.setX(i);
                    pos.setY(j);

                    qtdFiguras++;
                    stack.push(pos);

                    while (!stack.isEmpty()) {
                        posAtual = (MyCoord) stack.pop();

                        // System.out.println(matriz[posAtual.getY()][posAtual.getX()]);
                        matriz[posAtual.getX()][posAtual.getY()] = 0;

                        //System.out.println(matriz[posAtual.getX()][posAtual.getY()]);
                        // 0 0 1
                        // 0 0 0
                        // 0 0 0
                        if (matriz[posAtual.getX() - 1][posAtual.getY()] == 1) {
                            stack.push(new MyCoord(posAtual.getX() - 1, posAtual.getY()));
                        }

                        if (matriz[posAtual.getX() + 1][posAtual.getY()] == 1) {
                            stack.push(new MyCoord(posAtual.getX() + 1, posAtual.getY()));
                        }

                        if (matriz[posAtual.getX()][posAtual.getY() - 1] == 1) {
                            stack.push(new MyCoord(posAtual.getX(), posAtual.getY() - 1));
                        }

                        if (matriz[posAtual.getX()][posAtual.getY() + 1] == 1) {
                            stack.push(new MyCoord(posAtual.getX(), posAtual.getY() + 1));
                        }

                    }
                }

            }
        }

        System.out.println("Quantidade de figuras: " + qtdFiguras);

        JFrame frmOriginal = new JFrame("Teste Imagem - Original");
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
    }

    public static int getBit(int n, int k) {
        return (n >> k) & 1;
    }

}

class MyCoord {

    private int X;
    private int Y;

    public MyCoord() {
        this(0, 0);
    }

    public MyCoord(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
}
