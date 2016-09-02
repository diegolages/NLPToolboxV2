/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.performance.p1;

/**
 *
 * @author Diego
 */
public class P3 {

    double M = 1000000000;

    private void ddo() {
        int N = 1000;

        //randomize();

        double matrix1[][] = initmatrix(N);
        double matrix2[][] = initmatrix(N);
        double matrix3[][] = initmatrix(N);

        int i;

        for (i = 0; i < 1; i++) {
            multiplymatrix(matrix1, matrix2, matrix3);
        }


    }

    private void multiplymatrix(double[][] a1, double[][] a2, double[][] a3) {
        int i, j, k;	//loop control

        int n = a1.length;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                for (k = 0; k < n; k++) {

                    a3[i][j] += a1[i][k] * a2[k][j];
                }
            }
        }
    }

    private double r() {
        double d = Math.random() * M;
        d /= 100.0;
        return d;
    }

    private double[][] initmatrix(int n) {
        double[][] matrix = new double[n][n];

        int i, j;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                matrix[i][j] = r();
            }
        }
        return matrix;
    }

    public static void main(String argv[]) {
        P3 p3 = new P3();
        p3.ddo();
    }
}
