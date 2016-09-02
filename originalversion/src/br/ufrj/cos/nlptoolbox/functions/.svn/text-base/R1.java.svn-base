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
import org.jscience.mathematics.number.Rational;
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
public class R1 implements Function<Float64> {


    public Float64 evaluate(Vector<Float64> variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        
        double x = variables.get(0).doubleValue();

        if (x < 1) {
            return Float64.valueOf(1);
        }

        return Float64.valueOf(x*x - Math.log(x-1));
        
        
    }
    
    public Vector<Float64> gradient(Vector<Float64> variables) {

        double x = variables.get(0).doubleValue();

        if (x < 1) {
            return DenseVector.valueOf(Float64.valueOf(1));
        }

        double r = 2*x - 0.004*(1/(x-1));
        
        return DenseVector.valueOf(Float64.valueOf(r));
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) {

        double x = variables.get(0).doubleValue();

        if (x < 1) {
            return DenseMatrix.valueOf(new Float64[][] {
                {Float64.valueOf(-1)}
            });
        }

        double r = 2 + 0.004*(1 / (x-1)*(x-1));

        return DenseMatrix.valueOf(new Float64[][] {
            {Float64.valueOf(r)}
        });
    }

    
    
    
}
