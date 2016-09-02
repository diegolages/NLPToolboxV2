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
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class OppositeFunction implements Function<Float64> {

    Function<Float64> f1=null;

    public OppositeFunction(Function<Float64> _f1) {
        f1 = _f1;
    }

    public Float64 evaluate(Vector<Float64> variables) throws Exception {

        Float64 r = f1.evaluate(variables).opposite();

        return r;

    }

    public Vector<Float64> gradient(Vector<Float64> variables) throws Exception {

        Vector<Float64> r = f1.gradient(variables).opposite();
        return r;
    }

    public Matrix<Float64> hessian(Vector<Float64> variables) throws Exception {

        Matrix<Float64> r = f1.hessian(variables).opposite();

        return r;

    }
    
}
