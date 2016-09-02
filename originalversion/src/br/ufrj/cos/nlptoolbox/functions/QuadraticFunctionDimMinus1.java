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


package br.ufrj.cos.nlptoolbox.functions;

import br.ufrj.cos.nlptoolbox.Function;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class QuadraticFunctionDimMinus1 extends QuadraticFunction implements Function<Float64>{

    public QuadraticFunctionDimMinus1(double [][] _sigma) throws Exception {
        super(_sigma);
    }

    @Override
    public String toString() {
        return matrix.toString();
    }


    @Override
    public Float64 evaluate(Vector variables) throws Exception {

        int d = variables.getDimension();

        java.util.Vector v = new java.util.Vector();
        for (int i=0;i<d-1;i++) {
            Float64 f = (Float64) variables.get(i);
            v.add(Float64.valueOf(f.doubleValue()));
        }

        return super.evaluate(DenseVector.valueOf(v));
        
//
//        DenseMatrix wtransposed = DenseMatrix.valueOf(DenseVector.valueOf(variables));
//        Matrix r = wtransposed.times(matrix).times(wtransposed.transpose());
//
//        return (Float64) r.get(0, 0);
    }

    @Override
    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {
        int d = variables.getDimension();

        java.util.Vector v = new java.util.Vector();
        for (int i=0;i<d-1;i++) {
            Float64 f = (Float64) variables.get(i);
            v.add(Float64.valueOf(f.doubleValue()));
        }


        Vector<Float64> ret = super.gradient(DenseVector.valueOf(v));

        v = new java.util.Vector();
        for (int i=0;i<d-1;i++) {
            Float64 f = (Float64) ret.get(i);
            v.add(Float64.valueOf(f.doubleValue()));
        }
        v.add(Float64.ZERO);

        return DenseVector.valueOf(v);
    }

    @Override
    public Matrix hessian(Vector variables) throws Exception {

        int d = variables.getDimension();

        Float64[][] f64matrix = new Float64[d][d];
        for (int i=0;i<d;i++) {
            for (int j=0;j<d;j++) {
                if (i < d-1 && j < d-1) {
                    f64matrix[i][j] = Float64.valueOf(sigma[i][j]);
                } else {
                    f64matrix[i][j] = Float64.ZERO;
                }
                
            }
        }
        Matrix m = DenseMatrix.valueOf(f64matrix).times(Float64.valueOf(2));;
        return m;
    }

    
}
