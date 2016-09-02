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


package br.ufrj.cos.nlptoolbox;

import br.ufrj.cos.nlptoolbox.functions.ExpressionFunction;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV2;
import br.ufrj.cos.nlptoolbox.methods.Newton;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.numberfactories.RationalNumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MainNewton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int N = 5;
        
        ExpressionFunctionV2 f2 = new ExpressionFunctionV2("(1-x1)^2+100*(x2-x1)^2");//new F1();//new F2big(N);
        f2.setNumberfactory(new Float64NumberFactory());
        Float64 ix1 = Float64.valueOf(-2);
        Float64 ix2 = Float64.valueOf(1);
        
        
        Vector<Float64> iv = DenseVector.valueOf(ix1,ix2);
        
        Newton<Float64> n = new Newton<Float64>();
        n.setInitialPoint(iv);
        n.setMaxiterations(10);
        n.setNorm(new QuadraticNorm<Float64>());
        n.setParameters(Float64.valueOf(0.9));
        n.setPrecision(Float64.valueOf(0.000000001));
        
        System.out.println("minização: " + n.minimize(f2).toString());
        System.out.println("iterações: " + n.getIterationscount());
        System.out.println("erro: " + n.getErr());

//        ExpressionFunctionV2 f2 = new ExpressionFunctionV2("(1-x1)^2+100*(x2-x1)^2");//new F1();//new F2big(N);
//        f2.setNumberfactory(new RationalNumberFactory());
//        Rational ix1 = Rational.valueOf(-2, 1);
//        Rational ix2 = Rational.valueOf(1, 1);
//
//
//        Vector<Rational> iv = DenseVector.valueOf(ix1,ix2);
//
//        Newton<Rational> n = new Newton<Rational>();
//        n.setInitialPoint(iv);
//        n.setMaxiterations(10);
//        n.setNorm(new QuadraticNorm<Rational>());
//        n.setParameters(Rational.valueOf(9,10));
//        n.setPrecision(Rational.valueOf(1, 1000));
//
//        System.out.println("minização: " + n.minimize(f2).toString());
//        System.out.println("iterações: " + n.getIterationscount());
//        System.out.println("erro: " + n.getErr());
        
        
    }

}
