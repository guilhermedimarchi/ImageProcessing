/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listas_processamento_imagens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Guilherme
 */
public class Lista1_Ex5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File arq = new File("Teste.bmp");
        RandomAccessFile arquivo = null;

        try {
            arquivo = new RandomAccessFile(arq.getAbsolutePath(), "r");
        } catch (FileNotFoundException ex) {
        }

        byte BfType[] = new byte[2];
        byte BfSize[] = new byte[4];
        byte BfReser1[] = new byte[2];
        byte BfReser2[] = new byte[2];
        byte BfOffSetBits[] = new byte[4];

        byte BiSize[] = new byte[4];
        byte BiWidth[] = new byte[4];
        byte BiHeight[] = new byte[4];
        byte BiPlanes[] = new byte[2];
        byte BiBitCount[] = new byte[2];
        byte BiCompress[] = new byte[4];
        byte BiSizeImag[] = new byte[4];
        byte BiXPPMeter[] = new byte[4];
        byte BiYPPMeter[] = new byte[4];
        byte BiClrUsed[] = new byte[4];
        byte BiClrImpor[] = new byte[4];

        byte Blue[] = new byte[1];
        byte Green[] = new byte[1];
        byte Red[] = new byte[1];
        byte Reservado[] = new byte[1];
        try {

            arquivo.read(BfType);
            String dados = new String(BfType);
            System.out.println("Type: " + dados);
            arquivo.read(BfSize);
            System.out.println("Size: " + toInt(BfSize));
            arquivo.read(BfReser1);
            System.out.println("BfReser1: " + toInt(BfReser1));
            arquivo.read(BfReser2);
            System.out.println("BfReser2: " + toInt(BfReser2));
            arquivo.read(BfOffSetBits);
            System.out.println("BfOffSetBits: " + toInt(BfOffSetBits));
            arquivo.read(BiSize);
            System.out.println("BiSize: " + toInt(BiSize));
            arquivo.read(BiWidth);
            System.out.println("BiWidth: " + toInt(BiWidth));
            arquivo.read(BiHeight);
            System.out.println("BiHeight: " + toInt(BiHeight));
            arquivo.read(BiPlanes);
            System.out.println("BiPlanes: " + toInt(BiPlanes));
            arquivo.read(BiBitCount);
            System.out.println("BiBitCount: " + toInt(BiBitCount));
            arquivo.read(BiCompress);
            System.out.println("BiCompress: " + toInt(BiCompress));
            arquivo.read(BiSizeImag);
            System.out.println("BiSizeImag: " + toInt(BiSizeImag));
            arquivo.read(BiXPPMeter);
            System.out.println("BiXPPMeter: " + toInt(BiXPPMeter));
            arquivo.read(BiYPPMeter);
            System.out.println("BiYPPMeter: " + toInt(BiYPPMeter));
            arquivo.read(BiClrUsed);
            System.out.println("BiClrUsed: " + toInt(BiClrUsed));
            arquivo.read(BiClrImpor);
            System.out.println("BiClrImpor: " + toInt(BiClrImpor));
            arquivo.read(Blue);
            System.out.println("Blue: " + toInt(Blue));
            arquivo.read(Green);
            System.out.println("Green: " + toInt(Green));
            arquivo.read(Red);
            System.out.println("Red: " + toInt(Red));
            arquivo.read(Reservado);
            System.out.println("Reservado: " + toInt(Reservado));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    static public int toInt(byte[] b) {

        int num = 0;

        for (int i = b.length - 1; i >= 0; i--) {
            num += (b[i] & 0xFF) << (i * 8);
        }

        return num;
    }

}
