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
public class QuadraticFunction implements Function<Float64>{

    String exp = "";
    int idx=-1;

    Matrix          matrix = null;
    double [][]     sigma = null;

    public QuadraticFunction(double [][] _sigma) throws Exception {
        
        sigma = _sigma;

        Float64[][] f64matrix = new Float64[_sigma.length][_sigma.length];
        for (int i=0;i<_sigma.length;i++) {
            for (int j=0;j<_sigma.length;j++) {
                f64matrix[i][j] = Float64.valueOf(_sigma[i][j]);
            }
        }
        matrix = DenseMatrix.valueOf(f64matrix);
    }

    @Override
    public String toString() {
        return matrix.toString();
    }


    @Override
    public Float64 evaluate(Vector variables) throws Exception {
        DenseMatrix wtransposed = DenseMatrix.valueOf(DenseVector.valueOf(variables));
        Matrix r = wtransposed.times(matrix).times(wtransposed.transpose());

        return (Float64) r.get(0, 0);
    }

    @Override
    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {
        java.util.Vector ret = new java.util.Vector();

        for (int i=0;i<variables.getDimension();i++) {
            Float64 part1 = Float64.valueOf(2).times(sigma[i][i]).times(variables.get(i));

            Float64 part2 = Float64.ZERO;

            for (int j=0;j<variables.getDimension();j++) {
                if (i != j) {
                   part2 = part2.plus(variables.get(j).times(sigma[i][j]));
                }
            }

            ret.add(part1.plus(part2.times(2)));
            
        }

        return DenseVector.valueOf(ret);
    }

    @Override
    public Matrix hessian(Vector variables) throws Exception {
        return matrix.times(Float64.valueOf(2));
    }
    
}
