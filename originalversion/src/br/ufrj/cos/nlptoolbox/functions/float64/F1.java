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


package br.ufrj.cos.nlptoolbox.functions.float64;

import br.ufrj.cos.nlptoolbox.Function;
import org.jscience.mathematics.number.Float64;
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
public class F1 implements Function<Float64> {

   Float64 c1 = Float64.valueOf(100);

    public Float64 evaluate(Vector<Float64> variables) {
        // 100 * (x2 - x1^2)^2 + (1 - x1)^2
        
        Float64 x1 = variables.get(0);
        Float64 x2 = variables.get(1);
        
        return c1.times(x2.minus(x1.pow(2)).pow(2)).plus(Float64.ONE.minus(x1).pow(2));
        
        
        
    }
    
    Float64 gc_m_400 = Float64.valueOf(-400);
    Float64 gc_400 = Float64.valueOf(400);
    Float64 gc_m_2 = Float64.valueOf(-2);
    Float64 gc_2 = Float64.valueOf(2);
    Float64 gc_200 = Float64.valueOf(200);
    Float64 gc_m_200 = Float64.valueOf(-200);
    
    public Vector<Float64> gradient(Vector<Float64> variables) {
        // 100 * (x2 - x1^2)^2 + (1 - x1)^2
        // exp. 100 * (x2^2 - 2*x2*(x1^2) + x1^4) + 1 - 2x1 + x1^2
        // exp. 100 * x2^2 - 200*x2*(x1^2) + 100*x1^4 + 1 - 2x1 + x1^2
        // g(x1) -> -400x1*x2 + 400*x1^3 -2 + 2x1
        // g(x2) -> 2x2 - 200*x1^2
        
        Float64 x1 = variables.get(0);
        Float64 x2 = variables.get(1);
        
      //  Rational[] ret = new Rational[2];
        
        Float64 g1 = gc_m_400.times(x1).times(x2).plus ( gc_400.times(x1.pow(3)) ).plus( gc_m_2).plus(gc_2.times(x1));
        Float64 g2 = gc_2.times(x2).plus ( gc_m_200.times(x1.pow(2)) );

        //        ret[0] = g1;
//        ret[1] = c1;
        
        
        return DenseVector.valueOf(g1, g2);
    }
    
    Float64 gc_1200 = Float64.valueOf(1200);

    public Matrix hessian(Vector<Float64> variables) {
        // 100 * (x2 - x1^2)^2 + (1 - x1)^2
        // exp. 100 * (x2^2 - 2*x2*(x1^2) + x1^4) + 1 - 2x1 + x1^2
        // exp. 100 * x2^2 - 200*x2*(x1^2) + 100*x1^4 + 1 - 2x1 + x1^2
        // g(x1) -> -400x1*x2 + 400*x1^3 -2 + 2x1
        // g(x2) -> 2x2 - 200*x1^2
        // h(1,1) -> -400*x2 + 1200*x1^2 + 2
        // h(1,2) -> -400*x1
        // h(2,1) -> -400*x1
        // h(2,2) -> 2
        
        Float64 x1 = variables.get(0);
        Float64 x2 = variables.get(1);
        
        return DenseMatrix.valueOf(new Float64[][] {
            {gc_m_400.times(x2).plus ( gc_1200.times(x1.pow(2))).plus(gc_2), gc_m_400.times(x1)},
            {gc_m_400.times(x1), gc_2}
        });
    }

    
    
    
}
