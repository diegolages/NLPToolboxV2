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
import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.number.Real;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class F1 implements Function<Rational> {

   Rational c1 = Rational.valueOf(100, 1);

    public Rational evaluate(Vector<Rational> variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        
        Rational x1 = variables.get(0);
        Rational x2 = variables.get(1);
        
        return c1.times(x2.minus(x1.pow(2))).plus(Rational.ONE.minus(x1).pow(2));
        
        
        
    }
    
    Rational gc1 = Rational.valueOf(-200, 1);
    Rational gc2 = Rational.valueOf(2, 1);

    public Vector<Rational> gradient(Vector<Rational> variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        // exp. 100x2 - 100x1^2 + 1 - 2x1 + x1^2
        // g(x1) -> -200x1 -2 + 2x1
        // g(x2) -> 100
        
        Rational x1 = variables.get(0);
        Rational x2 = variables.get(1);
        
      //  Rational[] ret = new Rational[2];
        
        Rational g1 = gc1.times(c1).minus(gc2).plus(gc2.plus(x1));
//        ret[0] = g1;
//        ret[1] = c1;
        
        
        return DenseVector.valueOf(g1, c1);
    }

    public Matrix hessian(Vector<Rational> variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        // exp. 100x2 - 100x1^2 + 1 - 2x1 + x1^2
        // g(x1) -> -200x1 -2 + 2x1
        // g(x2) -> 100
        // h(1,1) -> -200 + 2
        // h(1,2) -> 0
        // h(2,1) -> 0
        // h(2,2) -> 0
        
        
        return DenseMatrix.valueOf(new Rational[][] {
            {Rational.valueOf(-198, 1), Rational.ZERO},
            {Rational.ZERO, Rational.ZERO}
        });
    }

    
    
    
}
