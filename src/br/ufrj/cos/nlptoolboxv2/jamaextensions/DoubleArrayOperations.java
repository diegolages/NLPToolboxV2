/*
 *  Copyright (c) 2008, Diego Lages
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package br.ufrj.cos.nlptoolboxv2.jamaextensions;

import Jama.Matrix;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author zcp7
 */
public class DoubleArrayOperations {

    public static final double[] times(double[] a, double k) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] * k;
        }

        return a;
    }

    public static final double[] timesNew(double[] a, double k) {

        double[] ret = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            ret[i] = a[i] * k;
        }

        return ret;
    }

    public static final double[] plus(double[] xk, Matrix matrix) {
        if (matrix.getArray().length == 1) {
            throw new RuntimeException("Invalid Matrix size " + _matrixsize(matrix));
        }
        if (matrix.getArray()[0].length > 1) {
            throw new RuntimeException("Invalid Matrix size " + _matrixsize(matrix));
        }
        double[][] A = matrix.getArray();

        for (int i = 0; i < xk.length; i++) {
            xk[i] = xk[i] + A[i][0];
        }
        return xk;
    }

    public static final double[] plusNew(double[] xk, Matrix matrix) {
        if (matrix.getArray().length > 1) {
            throw new RuntimeException("Invalid Matrix size " + _matrixsize(matrix));
        }
        double[] ret = new double[xk.length];
        double[][] A = matrix.getArray();

        for (int i = 0; i < xk.length; i++) {
            ret[i] = xk[i] + A[0][i];
        }
        return ret;
    }

    public static final String _matrixsize(Matrix m) {
        return "[" + m.getArray().length + "," + m.getArray()[0].length + "]";
    }

    public static final double[] minusNew(double[] gradient, double[] d) {
        if (gradient.length != d.length) {
            throw new RuntimeException("Invalid Dimensions " + gradient.length + " x " + d.length);
        }
        double[] ret = new double[gradient.length];

        for (int i = 0; i < gradient.length; i++) {
            ret[i] = gradient[i] - d[i];
        }

        return ret;
    }

    public static final double dotProduct(double[] a, double[] b) {

        if (a.length != b.length) {
            throw new RuntimeException("Invalid Dimensions " + a.length + " x " + b.length);
        }

        double ret = 0.0;

        for (int i = 0; i < a.length; i++) {
            ret += a[i] * b[i];
        }

        return ret;

    }

    public static String toString(double[] a) {
        return joinArray(a, ",");
    }

    private static String joinArray(Object array, String separator) {
        int arrayLength = Array.getLength(array);
        if (arrayLength == 0) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(Array.get(array, 0));
        for (int i = 1; i < arrayLength; i++) {
            buffer.append(separator);
            buffer.append(Array.get(array, i));
        }

        return buffer.toString();
    }

}
