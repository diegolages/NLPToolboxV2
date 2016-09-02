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
import java.util.HashMap;
import java.util.List;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class Float64ConstFunction implements Function<Float64>{

    Float64 value = null;
    Float64 zero = Float64.ZERO;
    int dimension=-1;

    Vector<Float64> grad = null;
    Matrix          hess = null;

    public Float64ConstFunction(Float64 v, int _nvariables) throws Exception {
        value = v;
        dimension = _nvariables;
        grad = DenseVector.valueOf(SparseVector.valueOf(dimension, zero, new HashMap()));
        hess = DenseMatrix.valueOf(SparseMatrix.valueOf(grad, zero));
    }

    @Override
    public String toString() {
        return value.toString();
    }


    @Override
    public Float64 evaluate(Vector variables) throws Exception {

        return value;

    }

    @Override
    public Vector<Float64> gradient(Vector variables) throws Exception {
       return grad;
    }

    @Override
    public Matrix hessian(Vector variables) throws Exception {
        return hess;
    }
    
}
