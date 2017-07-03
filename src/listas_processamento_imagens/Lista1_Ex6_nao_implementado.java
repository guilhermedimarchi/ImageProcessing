/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package listas_processamento_imagens;


import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Glauco
 */
public class Lista1_Ex6_nao_implementado {

    public static void main(String args[])
    {

        
        ImageIcon img1;

        
        URL url1 = Lista1_Ex6_nao_implementado.class.getResource("../Imagens/skylin.jpg");
        img1 = new ImageIcon(url1);

      
        BufferedImage original = new BufferedImage( img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        BufferedImage alterada = new BufferedImage( img1.getIconWidth(), img1.getIconHeight(), Transparency.OPAQUE);
        original.getGraphics().drawImage(img1.getImage(),0,0,null);
        
        int r ,g,b;
        ArrayList<Integer> rgb = new ArrayList<Integer>();
        
        Color cor;
        
        BitSet rb = new BitSet(3);
        
        
        
        for(int j=0; j < original.getHeight(); j++) {
            
            for(int i=0; i < original.getWidth(); i++) 
            {
                 cor = new Color(original.getRGB(i, j));
                 
                 r = cor.getRed();
                 g = cor.getGreen();
                 b = cor.getBlue();
                 rgb.add(r);
                 rgb.add(g);
                 rgb.add(b);
                 

               // alterada.setRGB(i, j, new Color((r & 0xF0),(g & 0xF0),(b & 0xF0)).getRGB()); 
            }
            
        }

         
         JFrame frmOriginal = new JFrame("Teste Imagem - Original");
         JPanel pan = new JPanel();
         JLabel lbl = new JLabel( new ImageIcon(original));
         pan.add( lbl );
         frmOriginal.getContentPane().add( pan );
         frmOriginal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         frmOriginal.pack();
         frmOriginal.setVisible(true);
         
         JFrame frmAlterada = new JFrame("Alterada");
         JPanel pan2 = new JPanel();
         JLabel lbl2 = new JLabel( new ImageIcon(alterada));
         pan2.add( lbl2 );
         frmAlterada.getContentPane().add( pan2 );
         frmAlterada.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         frmAlterada.pack();
         frmAlterada.setVisible(true);
         
    }
    
    
public static byte[] colorconvertRGB_IYUV_I420(int[] aRGB, int width, int height) {
        final int frameSize = width * height;
        final int chromasize = frameSize / 4;
       
        int yIndex = 0;
        int uIndex = frameSize;
        int vIndex = frameSize + chromasize;
        byte [] yuv = new byte[width*height*3/2];
       
        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {

                //a = (aRGB[index] & 0xff000000) >> 24; //not using it right now
                R = (aRGB[index] & 0xff0000) >> 16;
                G = (aRGB[index] & 0xff00) >> 8;
                B = (aRGB[index] & 0xff) >> 0;

                Y = ((66 * R + 129 * G +  25 * B + 128) >> 8) +  16;
                U = (( -38 * R -  74 * G + 112 * B + 128) >> 8) + 128;
                V = (( 112 * R -  94 * G -  18 * B + 128) >> 8) + 128;

                yuv[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));
               
                if (j % 2 == 0 && index % 2 == 0)
                {
                    yuv[uIndex++] = (byte)((U < 0) ? 0 : ((U > 255) ? 255 : U));
                    yuv[vIndex++] = (byte)((V < 0) ? 0 : ((V > 255) ? 255 : V));
                }

                index ++;
            }
        }       
        return yuv;
    } 
//private void convertRGBAtoYUV420P(byte[] rgba, byte[] yuv, int width, int height)
//{
//    final int frameSize = width * height;
//    final int chromasize = frameSize / 4;
//
//    int yIndex = 0;
//    int uIndex = frameSize;
//    int vIndex = frameSize + chromasize;
//
//    int R, G, B, Y, U, V;
//    int index = 0;
//
//    int rgbaIndex = 0;
//
//    for (int j = 0; j < height; j++)
//    {
//        for (int i = 0; i < width; i++)
//        {
//            R = rgba[rgbaIndex++] + 128; // My rgba bytes are -128 to 127
//            G = rgba[rgbaIndex++] + 128;
//            B = rgba[rgbaIndex++] + 128;
//            rgbaIndex++; // skip A
//
//            Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
//            U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
//            V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;
//
//            yuv[yIndex++] = (byte)clamp(Y);
//
//            if (j % 2 == 0 && index % 2 == 0)
//            {
//                yuv[uIndex++] = (byte)clamp(U);
//                yuv[vIndex++] = (byte)clamp(V);
//            }
//
//            index++;
//        }
//    }
//}
 
}





