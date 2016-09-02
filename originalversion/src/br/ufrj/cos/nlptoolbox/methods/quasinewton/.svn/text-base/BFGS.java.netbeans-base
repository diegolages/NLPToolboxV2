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
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class BFGS<F extends Field<F>> extends QuasiNewton<F> implements MinimizationMethod<F> {

    protected Matrix<F> getInverse(Matrix<F> Bk, Matrix<F> inverseBk, Vector<F> sk, Vector<F> yk) {

        Matrix<F> identity = DenseMatrix.valueOf(makeIdentity(sk.getDimension()));
        
        DenseMatrix<F> myk = DenseMatrix.valueOf(DenseVector.valueOf(yk)).transpose();
        DenseMatrix<F> msk = DenseMatrix.valueOf(DenseVector.valueOf(sk)).transpose();
        
        Matrix<F> term = identity.plus ( myk.times(msk.transpose()).times(  yk.times(sk).inverse()   ).opposite()   );
        
        //Matrix<F> fsecondpart = identity.plus ( myk.times(msk.transpose()).times(  yk.times(sk).inverse()   )).transpose();
        
        Matrix<F> firstpart = term.transpose().times(inverseBk).times(term);
        
        Matrix<F> secondpart = msk.times(msk.transpose()).times ( yk.times(sk).inverse()  );
        
        Matrix<F> ret = firstpart.plus(secondpart);
        
        return ret;
    }
    
    protected Matrix<F> getNextBk(Matrix<F> Bk, Vector<F> sk, Vector<F> yk) {
        
        
        DenseMatrix<F> myk = DenseMatrix.valueOf(DenseVector.valueOf(yk)).transpose();
        DenseMatrix<F> msk = DenseMatrix.valueOf(DenseVector.valueOf(sk)).transpose();
        
        Matrix<F> firstpart = myk.times(myk.transpose()).times(  yk.times(sk).inverse()  );
        
        Matrix<F> s1 = Bk.times(msk);
        Matrix<F> s2 = Bk.times(msk).transpose();
        
        
        Matrix<F> secondpart = s1.times( s2  ).times( sk.times( Bk.times(sk)  ).inverse()  );
        
        
        Matrix<F> ret = Bk.plus(firstpart).plus(secondpart.opposite());
        
        return ret;
    }

}
