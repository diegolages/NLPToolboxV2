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


package br.ufrj.cos.nlptoolbox.methods.quasinewton;

import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class BroydenFamily<F extends Field<F>> extends QuasiNewton<F> implements MinimizationMethod<F> {

    @Override
    protected Matrix<F> getInverse(Matrix<F> Bk, Matrix<F> inverseBk, Vector<F> sk, Vector<F> yk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Matrix<F> getNextBk(Matrix<F> Bk, Vector<F> sk, Vector<F> yk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
