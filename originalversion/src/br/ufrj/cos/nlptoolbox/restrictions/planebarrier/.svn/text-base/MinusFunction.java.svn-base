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
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MinusFunction implements Function<Float64> {

    Function<Float64> f1=null;
    Function<Float64> f2=null;
    double mi = 1;

    public MinusFunction(Function<Float64> _f1, Function<Float64> _f2) {
        f1 = _f1;
        f2 = _f2;
        }

    public MinusFunction(Function<Float64> _f1, Function<Float64> _f2, double _mi) {
        f1 = _f1;
        f2 = _f2;
        mi = _mi;
        }

    public Float64 evaluate(Vector<Float64> variables) throws Exception {

        Float64 r = f1.evaluate(variables).minus(f2.evaluate(variables)).times(mi);

        return r;

    }

    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {

        Vector<Float64> r = f1.gradient(variables).minus(f2.gradient(variables));
        //SparseVector.valueOf(variables.getDimension(), Float64.ZERO, 0, Float64.ZERO);
//
//        for (Function<Float64> f : funcs) {
//            r = r.plus(f.gradient(variables));
//        }

        return r.times(Float64.valueOf(mi));
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) throws Exception {

        Matrix<Float64> r = f1.hessian(variables).minus(f2.hessian(variables));

        return r.times(Float64.valueOf(mi));

//        Float64 [][] retmat = new Float64[variables.getDimension()][variables.getDimension()];
//
//        for (int i=0;i<retmat.length;i++) {
//            for (int j=0;j<retmat.length;j++) {
//                retmat[i][j] = Float64.ZERO;
//            }
//        }
//
//        return DenseMatrix.valueOf(retmat);
    }
    
}
