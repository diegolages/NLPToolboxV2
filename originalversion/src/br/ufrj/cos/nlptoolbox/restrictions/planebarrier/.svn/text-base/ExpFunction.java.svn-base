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


package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

import br.ufrj.cos.nlptoolbox.Function;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class ExpFunction implements Function<Float64> {

    Function<Float64> func = null;

    public ExpFunction(Function<Float64> f) {

        func = f;
    }

    public Float64 evaluate(Vector<Float64> variables) throws Exception {

        Float64 r = Float64.ZERO;

        return Float64.valueOf( Math.exp(func.evaluate(variables).doubleValue()));

    }

    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {

        Vector<Float64> r0 = func.gradient(variables);

        Float64 eval = func.evaluate(variables);

        List l = new java.util.Vector();

        for (int i=0;i<r0.getDimension();i++) {
            l.add(r0.get(i).times(eval.exp()));
        }

        return DenseVector.valueOf(l);
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) throws Exception {

        Float64 eval = func.evaluate(variables);

        Vector<Float64> grad = gradient(variables);

        Float64 [][] retmat = new Float64[variables.getDimension()][variables.getDimension()];

        for (int i=0;i<retmat.length;i++) {
            for (int j=0;j<retmat.length;j++) {
                retmat[i][j] = grad.get(i).times(grad.get(j)).times(eval.exp());
            }
        }

        return DenseMatrix.valueOf(retmat);
    }
    
}
