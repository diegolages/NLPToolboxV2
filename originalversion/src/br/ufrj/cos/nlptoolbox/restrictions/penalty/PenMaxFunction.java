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

package br.ufrj.cos.nlptoolbox.restrictions.penalty;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PenMaxFunction<F extends Field<F>> implements Function<F> {

    Function<F> function=null;
    NumberFactory<F> numberfactory=null;
    F zero = null;
    F realzero = null;
    F mi = null;
    Function<F> pow2function=null;

    static Matrix zeromatrix = null;

    
    Comparator<F> comparator = new Comparator<F>();

    public PenMaxFunction(Function<F> f,Function<F> pow2f, NumberFactory<F> nf, double _mi) {
        this(f,pow2f, nf,0,_mi);
    }

    public PenMaxFunction(Function<F> f,Function<F> pow2f, NumberFactory<F> nf, double z, double _mi) {
        function = f;
        numberfactory = nf;
        zero = numberfactory.makeNumber(z);
        realzero = numberfactory.makeNumber(0);
        mi = numberfactory.makeNumber(_mi);
        pow2function = pow2f;
    }
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        
        F r = function.evaluate(variables);
        
        
        if (comparator.compare(zero, r) == -1) {
            return realzero;
        } else {
            F pow2r = pow2function.evaluate(variables);
            return pow2r.times(mi);
        }
    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        F r = function.evaluate(variables);
        
        if (comparator.compare(zero, r) == -1) {
            return SparseVector.valueOf(variables.getDimension(),realzero,0,realzero);
        } else {
            return pow2function.gradient(variables).times(mi);
        }
    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {

        if (zeromatrix == null) {
            zeromatrix = DenseMatrix.valueOf(SparseMatrix.valueOf(SparseVector.valueOf(variables.getDimension(),realzero,0,realzero), realzero));
        }

        if (variables.getDimension() != zeromatrix.getNumberOfColumns()) {
            zeromatrix = DenseMatrix.valueOf(SparseMatrix.valueOf(SparseVector.valueOf(variables.getDimension(),realzero,0,realzero), realzero));
        }

        F r = function.evaluate(variables);
        
        if (comparator.compare(zero, r) == -1) {

            return zeromatrix;
        } else {
            return pow2function.hessian(variables).times(mi);
        }
    }

}
