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

package br.ufrj.cos.linearalgebra;

import org.jscience.mathematics.ext.QRDecomposition;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Matrix;
import org.jscience.mathematics.vector.Matrix;

/**
 *
 * @author Diego
 */
public class Main {

    public static void main(String argv[]) throws Exception {
    
//        Float64Matrix A = Float64Matrix.valueOf(
//            new double[][]
//                {
//                    {63,  41, -88},
//                    {42,  60,  51},
//                    {0,  -28,  56},
//                    {126, 82, -71},
//                    
//                }
//        );

        Float64Matrix A = Float64Matrix.valueOf(
            new double[][]
                {
                    {1    , 1/2.0, 1/3.0, 1/4.0, 1/5.0},
                    {1/2.0, 1/3.0, 1/4.0, 1/5.0, 1/6.0},
                    {1/3.0, 1/4.0, 1/5.0, 1/6.0, 1/7.0},
                    {1/4.0, 1/5.0, 1/6.0, 1/7.0, 1/8.0},
                    {1/5.0, 1/6.0, 1/7.0, 1/8.0, 1/9.0}

                }
        );

//        Float64Matrix A = Float64Matrix.valueOf(
//            new double[][]
//                {
//                    {7, 6.00001},
//                    {6, 5}
//
//                }
//        );

//        Float64Matrix A = Float64Matrix.valueOf(
//            new double[][]
//                {
//                    {1, 2},
//                    {2, 4.0001}
//
//                }
//        );
        
        
//        Float64Matrix A = Float64Matrix.valueOf(
//            new double[][]
//                {
//                    {  -4,   0},
//                    {  -2,  -5},
//                    {   0,   0},
//                    
//                }
//        );
        
//        Float64Matrix A = Float64Matrix.valueOf(
//            new double[][]
//                {
//                    {  3,   2, 3},
//                    {  4,   5, 6}
//                }
//        );
        
        
        QRDecomposition qr = QRDecomposition.valueOf(A);
                
        System.out.println("A: " + qr.getA());
        System.out.println("Q: " + qr.getQ());
        System.out.println("R: " + qr.getR());

        Matrix m = qr.getQ().times(qr.getR());
        
        System.out.println("m: " + m);

        Matrix inv1 = A.inverse();

        Matrix qinv = qr.getQ();
        qinv = qinv.inverse();
        Matrix rinv = qr.getR();
        rinv = rinv.inverse();


        Matrix inv2 = rinv.times(qinv);

        System.out.println("----");

        System.out.println("i1: " + inv1);
        System.out.println("i2: " + inv2);

        System.out.println("----");

        Matrix inv11 = A.inverse().inverse();

        Matrix inv21 = qinv.inverse().times(rinv.inverse());

        System.out.println("i11: " + inv11);
        System.out.println("i21: " + inv21);
    }
    
}
