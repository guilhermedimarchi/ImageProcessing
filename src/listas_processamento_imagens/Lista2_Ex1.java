/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas_processamento_imagens;

import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Lista2_Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int corte = 0;
        int valMin = 0;
        int valMax = 0;
        int filtro = Integer.parseInt(JOptionPane.showInputDialog("1 - Passa baixa\n2 - Passa alta\n3 - Passa faixa\n4 - Rejeita faixa"));

        if (filtro == 1 || filtro == 2) {
            corte = Integer.parseInt(JOptionPane.showInputDialog("Corte: "));
        } else {
            valMin = Integer.parseInt(JOptionPane.showInputDialog("Valor minimo: "));
            valMax = Integer.parseInt(JOptionPane.showInputDialog("valor maximo: "));
        }

        int tam = Integer.parseInt(JOptionPane.showInputDialog("Tamanho valor digitalizado em bits: "));

        double valorAnalogico = (Math.random() * 10);

        System.out.println("Numero analogico antes do filtro: " + valorAnalogico);
        switch (filtro) {
            case 1:
                if (valorAnalogico > corte) {
                    valorAnalogico = 0;
                }
                // valorAnalogico = corte;
                break;
            case 2:
                if (valorAnalogico < corte) {
                    valorAnalogico = 0;
                }
                break;
            case 3:
                if (valorAnalogico < valMin && valorAnalogico > valMax) {
                    valorAnalogico = 0;
                }
                break;

            case 4:
                if (valorAnalogico > valMin && valorAnalogico < valMax) {
                    valorAnalogico = 0;
                }
                break;

        }

        System.out.println("Numero analogico depois do filtro: " + valorAnalogico);
        System.out.println("Digitalizado usando " + tam + " bits: " + digitaliza(valorAnalogico, tam));

    }

    public static String digitaliza(double valorAnalogico, int tam) {

        int divisor = (int) (1 * Math.pow(2, tam));
        int analogico = (int) valorAnalogico;

        StringBuilder sb = new StringBuilder();

        if (analogico == 0) {
            return "0";
        } else if (analogico >= divisor) {
            for (int i = 0; i < tam; i++) {
                sb.append('1');
            }
        } else {
            do {
                sb.append(analogico & 1);
            } while ((analogico >>= 1) != 0);

        }

        return sb.reverse().toString();

    }

}
