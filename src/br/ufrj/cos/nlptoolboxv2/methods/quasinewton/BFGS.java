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


package br.ufrj.cos.nlptoolboxv2.methods.quasinewton;

import Jama.Matrix;
import br.ufrj.cos.nlptoolboxv2.jamaextensions.Vector;
import br.ufrj.cos.nlptoolboxv2.methods.MinimizationMethod;

/**
 *
 * @author Diego
 */
public class BFGS extends QuasiNewton implements MinimizationMethod {

    @Override
    protected Matrix getInverse(Matrix Bk, Matrix inverseBk, Matrix sk, Vector yk) {

        Matrix identity = makeIdentity(sk.getRowDimension());
        
        Matrix myk = yk.transpose();
        Matrix msk = sk.transpose();
        
        Matrix term = identity.plus ( myk.times(msk.transpose()).times(  yk.times(sk).inverse()   ).uminus()   );
        
        //Matrix fsecondpart = identity.plus ( myk.times(msk.transpose()).times(  yk.times(sk).inverse()   )).transpose();
        
        Matrix firstpart = term.transpose().times(inverseBk).times(term);
        
        Matrix secondpart = msk.times(msk.transpose()).times ( yk.times(sk).inverse()  );
        
        Matrix ret = firstpart.plus(secondpart);
        
        return ret;
    }
    
    @Override
    protected Matrix getNextBk(Matrix Bk, Matrix sk, Vector yk) {
        
        
        Matrix myk = yk.transpose();
        Matrix msk = sk.transpose();
        
        Matrix firstpart = myk.times(myk.transpose()).times(  yk.times(sk).inverse()  );
        
        Matrix s1 = Bk.times(msk);
        Matrix s2 = Bk.times(msk).transpose();
        
        
        Matrix secondpart = s1.times( s2  ).times( sk.times( Bk.times(sk)  ).inverse()  );
        
        
        Matrix ret = Bk.plus(firstpart).plus(secondpart.uminus());
        
        return ret;
    }

}
