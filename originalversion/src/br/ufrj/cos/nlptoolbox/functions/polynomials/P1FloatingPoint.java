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


package br.ufrj.cos.nlptoolbox.functions.polynomials;

import br.ufrj.cos.nlptoolbox.functions.PolynomialFunction;
import java.util.List;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.FloatingPoint;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class P1FloatingPoint extends PolynomialFunction<FloatingPoint> {

    Variable<FloatingPoint> varX = new Variable.Local<FloatingPoint>("x");
    Variable<FloatingPoint> varY = new Variable.Local<FloatingPoint>("y");
    
    FloatingPoint c1 = FloatingPoint.valueOf(100);
    
    @Override
    protected Polynomial<FloatingPoint> getPolynomial() {
        Polynomial<FloatingPoint> x = Polynomial.valueOf(FloatingPoint.ONE, varX);
        Polynomial<FloatingPoint> y = Polynomial.valueOf(FloatingPoint.ONE, varY);
        
        Polynomial<FloatingPoint> f = ( y.plus(x.pow((int)2).opposite()).pow(2)  ).times(c1).plus((x.opposite().plus(FloatingPoint.ONE)).pow(2));
                
        return f;
    }

    @Override
    protected List<Variable<FloatingPoint>> getVariables() {
        java.util.Vector ret = new java.util.Vector();
        ret.add(varX);
        ret.add(varY);
        return ret;
    }
    
    public static void main(String argv[]) {
//        FloatingPoint ix1 = FloatingPoint.valueOf(-2);
//        FloatingPoint ix2 = FloatingPoint.valueOf(1);
//
//        Vector<FloatingPoint> iv = DenseVector.valueOf(ix1,ix2);
//        
//        P1 p1 = new P1();
//        System.out.println(p1.evaluate(iv).toString());
//        
//        p1.getGradientPolynomials();
//        p1.getHessianPolynomials();
//        
//        System.out.println(p1.gradient(iv).toString());
//        System.out.println(p1.hessian(iv).toString());
    }

}
