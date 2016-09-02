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
import br.ufrj.cos.nlptoolbox.functions.R1;
import br.ufrj.cos.nlptoolbox.functions.R2;
import br.ufrj.cos.nlptoolbox.linesearch.NewtonRaphson;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.methods.Newton;
import br.ufrj.cos.nlptoolbox.methods.quasinewton.BFGS;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenaltyMinimizationMethod;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MainNewtonRestriction2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int N = 5;
        
        //ExpressionFunction f2 = new ExpressionFunction("(1-x1)^2+100*(x2-x1)^2");
        
        //ExpressionFunction f2 = new ExpressionFunction("x1^2 - 0.04/(x1-1)");

        Function f2 = new R2(20);
        //f2.setNumberfactory(new Float64NumberFactory());
//
//
//        f2.setNumberfactory(new Float64NumberFactory());
        Float64 ix1 = Float64.valueOf(1000);
        //Float64 ix2 = Float64.valueOf(1);
        
        
        Vector<Float64> iv = DenseVector.valueOf(ix1);
        
//        System.out.println(f2.evaluate(iv).toString());
//        System.out.println(f2.gradient(iv).toString());
        //System.out.println(f2.hessian(iv).toString());
        
        Newton<Float64> n = new Newton();
//        NewtonRaphson<Float64> linesearch = new NewtonRaphson<Float64>();
//        linesearch.setMaxiterations(10);
//        linesearch.setPrecision(Float64.valueOf(0.0001));
//        n.setLinesearch(linesearch);
        //n.setInitialc(Float64.valueOf(0.1));
        //n.setCfactor(Float64.valueOf(10));
        n.setNumberfactory(new Float64NumberFactory());
        n.setInitialPoint(iv);
        n.setMaxiterations(10);
        n.setNorm(new QuadraticNorm<Float64>());
        n.setParameters(Float64.valueOf(0.99));
        n.setPrecision(Float64.valueOf(0.000001));
        
        System.out.println("minização: " + n.minimize(f2).toString());
        System.out.println("iterações: " + n.getIterationscount());
        System.out.println("erro: " + n.getErr());
        
        
    }

}
