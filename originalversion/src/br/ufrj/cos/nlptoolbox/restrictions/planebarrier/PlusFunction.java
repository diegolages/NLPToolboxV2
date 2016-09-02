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
import br.ufrj.cos.nlptoolbox.methods.QRFactoredNewton;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.number.Real;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PlusFunction implements Function<Float64> {

    List<Function<Float64>> funcs = new java.util.Vector();

    public PlusFunction(Function<Float64> ... fs) {

        for (Function<Float64> f : fs) {
            funcs.add(f);
        }
    }

    public Float64 evaluate(Vector<Float64> variables) throws Exception {

        Float64 r = Float64.ZERO;

        for (Function<Float64> f : funcs) {
            r = r.plus(f.evaluate(variables));
        }

        return r;

    }

    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {

        Vector<Float64> r = SparseVector.valueOf(variables.getDimension(), Float64.ZERO, 0, Float64.ZERO);

        for (Function<Float64> f : funcs) {
            r = r.plus(f.gradient(variables));
        }

        return r;
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) throws Exception {

        int dimension = variables.getDimension();

//        double [][] d_ret = new double[dimension][dimension];
//
//        for (int i=0;i<dimension;i++) {
//            for (int j=0;j<dimension;j++) {
//                d_ret[i][j] = 0;
//            }
//        }
//
//        Jama.Matrix ret = new Jama.Matrix(d_ret);


        Float64 [][] retmat = new Float64[dimension][dimension];

        for (int i=0;i<dimension;i++) {
            for (int j=0;j<dimension;j++) {
                retmat[i][j] = Float64.ZERO;
            }
        }


        Matrix<Float64> ret =  DenseMatrix.valueOf(retmat);


        for (Function<Float64> f : funcs) {
            Matrix<Float64> m = f.hessian(variables);

//            Jama.Matrix jmatrix = QRFactoredNewton.toJama(m);
//
//            ret.plus(jmatrix);

            ret = ret.plus(m);
        }

//        return QRFactoredNewton.fromJama(ret);

        return ret;
    }
    
}
