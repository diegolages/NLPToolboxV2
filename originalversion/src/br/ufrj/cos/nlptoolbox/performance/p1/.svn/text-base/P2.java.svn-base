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

package br.ufrj.cos.nlptoolbox.performance.p1;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.functions.F1;
import br.ufrj.cos.nlptoolbox.functions.F2;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class P2 {

    int N = 100;
    
    DenseMatrix<Float64> matrix1 = initMatrix(N);
    DenseMatrix<Float64> matrix2 = initMatrix(N);
    
    double M = 1000000000;
    
    public DenseMatrix<Float64> initMatrix(int n) {
        
        Float64 m[][] = new Float64[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                double d = Math.random()*M;
                d /= 100.0;
                m[i][j] = Float64.valueOf(d);
            }
        }
        return DenseMatrix.valueOf(m);
    }
    
    public static void main(String argv[]) {
        P2 p = new P2();
        p.run();
    }
    
    public void run() {
        
        int N = 100;
        
        DenseMatrix<Float64> matrix1 = initMatrix(N);
        DenseMatrix<Float64> matrix2 = initMatrix(N);
        
        for (int i=0;i<50;i++) {
            matrix1.times(matrix2);
        }
        
    }
    
}
