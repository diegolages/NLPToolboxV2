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
public class SimpleOneVariableFunction implements Function<Float64>{

    String exp = "";
    int idx=-1;

    Vector<Float64> grad = null;
    Matrix          hess = null;

    public SimpleOneVariableFunction(String _exp, int _nvariables) throws Exception {
        exp = _exp.trim();

        if (!exp.startsWith("x")) {
            throw new Exception("invalid exp:" + _exp);
        }

        String exp2 = exp.substring(1);

        try {
            idx = Integer.parseInt(exp2);
        } catch (Exception e) {
            throw new Exception("Invalid exp:" + exp);
        }

        idx = idx-1;

        grad = DenseVector.valueOf(SparseVector.valueOf(_nvariables, Float64.ZERO, 0, Float64.ZERO));

        hess = DenseMatrix.valueOf(SparseMatrix.valueOf(grad, Float64.ZERO));
    }

    @Override
    public String toString() {
        return exp;
    }


    @Override
    public Float64 evaluate(Vector variables) throws Exception {
        return (Float64) variables.get(idx);
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
