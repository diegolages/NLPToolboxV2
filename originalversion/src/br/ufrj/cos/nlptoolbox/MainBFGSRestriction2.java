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
import br.ufrj.cos.nlptoolbox.linesearch.NewtonRaphson;
import br.ufrj.cos.nlptoolbox.methods.Newton;
import br.ufrj.cos.nlptoolbox.methods.quasinewton.BFGS;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.barrier.BarrierMinimizationMethod;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.number.FloatingPoint;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MainBFGSRestriction2 {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws Exception {
//        int N = 5;
//
//        //ExpressionFunction f2 = new ExpressionFunction("(1-x1)^2+100*(x2-x1)^2");
//
//        ExpressionFunction f2 = new ExpressionFunction("x1^2+x2^2");
//        f2.setNumberfactory(new Float64NumberFactory());
//
////
//        ExpressionFunction r1 = new ExpressionFunction("x1-1+0*x2");
//        r1.setNumberfactory(new Float64NumberFactory());
//        r1.setVariables(f2.getVariables());
//
//
//        List<Function<Float64>> restrictions = new java.util.Vector();
//        restrictions.add(r1);
//
//        f2.setNumberfactory(new Float64NumberFactory());
//        Float64 ix1 = Float64.valueOf(2.5);
//        Float64 ix2 = Float64.valueOf(0.5);
//
//        Vector<Float64> iv = DenseVector.valueOf(ix1,ix2);
//
////        System.out.println(f2.evaluate(iv).toString());
////        System.out.println(f2.gradient(iv).toString());
//        //System.out.println(f2.hessian(iv).toString());
//
//        BFGS<Float64> bfgs = new BFGS();
//
//        NewtonRaphson<FloatingPoint> linesearch = new NewtonRaphson<FloatingPoint>();
//        linesearch.setMaxiterations(10);
//        linesearch.setPrecision(FloatingPoint.valueOf(0.000000000001));
//        bfgs.setLinesearch(linesearch);
//
//        //PenaltyMinimizationMethod<Float64> n = new PenaltyMinimizationMethod(, restrictions, null);
//        BarrierMinimizationMethod<Float64> n = new BarrierMinimizationMethod(new Newton<Float64>(), restrictions, null);
//        n.setInitialc(Float64.valueOf(100000));
//        n.setCfactor(Float64.valueOf(0.1));
//        n.setNumberfactory(new Float64NumberFactory());
//        n.setInitialPoint(iv);
//        n.setMaxiterations(50);
//        n.setNorm(new QuadraticNorm<Float64>());
//        n.setParameters(Float64.valueOf(0.99));
//        n.setPrecision(Float64.valueOf(0.000001));
//
//        System.out.println("minização: " + n.minimize(f2).toString());
//        System.out.println("iterações: " + n.getIterationscount());
//        System.out.println("erro: " + n.getErr());
//
//
//    }

}
