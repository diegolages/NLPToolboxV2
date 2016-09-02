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
import br.ufrj.cos.nlptoolbox.methods.Newton;
import br.ufrj.cos.nlptoolbox.methods.genetic.Genetic;
import br.ufrj.cos.nlptoolbox.numberfactories.Float64NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MainGenetic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int N = 5;
        
        ExpressionFunction f2 = new ExpressionFunction("x1^2+x2^2");//new F1();//new F2big(N);
        f2.setNumberfactory(new Float64NumberFactory());
        Float64 ix1 = Float64.valueOf(2);
        Float64 ix2 = Float64.valueOf(1);
        
        
        Vector<Float64> iv = DenseVector.valueOf(ix1,ix2);
        
        Genetic<Float64> n = new Genetic<Float64>();
        n.setNumberfactory(new Float64NumberFactory());
        n.setInitialPoint(iv);
        n.setMaxiterations(100);
        n.setNorm(new QuadraticNorm<Float64>());
        n.setParameters();
        n.setPrecision(Float64.valueOf(0.00001));
        
        System.out.println("minização: " + n.minimize(f2).toString());
        System.out.println("iterações: " + n.getIterationscount());
        System.out.println("erro: " + n.getErr());
        
        
    }

}
