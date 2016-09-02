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
public class TimesConstFunction implements Function<Float64> {

    Function<Float64> func = null;
    Float64 c = null;

    public TimesConstFunction(Float64 _c,Function<Float64> f) {
        func = f;
        c = _c;
    }

    public Float64 evaluate(Vector<Float64> variables) throws Exception {
        return func.evaluate(variables).times(c);
    }

    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {
        return func.gradient(variables).times(c);
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) throws Exception {
        return func.hessian(variables).times(c);
    }
    
}
