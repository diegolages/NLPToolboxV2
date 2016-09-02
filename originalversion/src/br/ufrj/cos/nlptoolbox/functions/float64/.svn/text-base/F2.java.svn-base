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


package br.ufrj.cos.nlptoolbox.functions.float64;

import br.ufrj.cos.nlptoolbox.Function;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.number.Real;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class F2 implements Function<Float64> {


    public Float64 evaluate(Vector<Float64> variables) {
        // x1^2 + x2^2
        
        Float64 x1 = variables.get(0);
        Float64 x2 = variables.get(1);
        
        return x1.pow(2).plus(x2.pow(2));

    }

    public Vector<Float64> gradient(Vector<Float64> variables) {

        // g(x1) -> 2x1
        // g(x2) -> 2x2
        
        Float64 x1 = variables.get(0);
        Float64 x2 = variables.get(1);

        
        
        return DenseVector.valueOf(x1.times(2), x2.times(2));
    }

    public Matrix hessian(Vector<Float64> variables) {
        // h(1,1) -> 2
        // h(1,2) -> 1
        // h(2,1) -> 1
        // h(2,2) -> 2
        
        // Identity
        return DenseMatrix.valueOf(new Float64[][] {
            {Float64.valueOf(2), Float64.ZERO},
            {Float64.ZERO, Float64.valueOf(2)}
        });
    }

    
    
    
}
