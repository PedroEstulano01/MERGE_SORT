package com.mycompany.main_sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MergeSort {

    public static void MergeSort(int[] vetor) {
        if (vetor.length <= 1) {
            return;
        }

        int meio = vetor.length / 2;
        int[] esquerda = Arrays.copyOfRange(vetor, 0, meio);
        int[] direita = Arrays.copyOfRange(vetor, meio, vetor.length);

        MergeSort(esquerda);
        MergeSort(direita);

        mesclar(vetor, esquerda, direita);
    }

    private static void mesclar(int[] vetor, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
        }
        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
        }
        while (j < direita.length) {
            vetor[k++] = direita[j++];
        }
    }

    public static int[] carregarDados(String nomeArquivo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(nomeArquivo));
        int[] dados = new int[100000]; 
        int i = 0;
        while (scanner.hasNextInt()) {
            dados[i++] = scanner.nextInt();
        }
        scanner.close();
        return dados;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String nomeArquivo = "C:\\Users\\Administrator\\Downloads\\dados100mil.txt";
        int[] dados = carregarDados(nomeArquivo); 

        long inicio = System.currentTimeMillis();
        MergeSort(dados);
        long fim = System.currentTimeMillis();

        long tempoExecucao = fim - inicio;

        long horas = TimeUnit.MILLISECONDS.toHours(tempoExecucao);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(tempoExecucao) % 60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(tempoExecucao) % 60;
        long milissegundos = tempoExecucao % 1000;

        String horaFormatada = String.format("%02d:%02d:%02d:%03d", horas, minutos, segundos, milissegundos);

        System.out.println("Tempo de execução: " + horaFormatada);
        // System.out.println(Arrays.toString(dados)); // Imprimir o array ordenado
    }
}