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
import java.util.List;
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
public class R2 implements Function<Float64> {


    private double mi = 1.0;

    public R2(double m) {
        mi = m;
    }

    public Float64 evaluate(Vector<Float64> variables) {

        double x = variables.get(0).doubleValue();

        return Float64.valueOf(x*x - mi*(1.0/(x-1)));

    }

    public Vector<Float64> gradient(Vector<Float64> variables) {

        double x = variables.get(0).doubleValue();

        double r = 2.0*x + mi*(1.0/Math.pow(x-1,2));

        return DenseVector.valueOf(Float64.valueOf(r));
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) {

        double x = variables.get(0).doubleValue();

        double r = 2 - 2*mi*(x-1)*(1.0/Math.pow(x-1,4));

        return DenseMatrix.valueOf(new Float64[][] {
            {Float64.valueOf(r)}
        });
    }

    
    
    
}
