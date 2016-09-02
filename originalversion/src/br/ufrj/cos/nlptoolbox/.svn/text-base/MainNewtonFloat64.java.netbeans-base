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

import br.ufrj.cos.nlptoolbox.functions.float64.F1;
import br.ufrj.cos.nlptoolbox.functions.polynomials.P1;
import br.ufrj.cos.nlptoolbox.methods.Newton;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MainNewtonFloat64 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Function f2 = new P1();//new F2big(N);
        
        Float64 ix1 = Float64.valueOf(-2);
        Float64 ix2 = Float64.valueOf(1);
        
//        Float64 r[] = new Float64[N];
//        for (int i=0;i<N;i++) {
//            r[i] = Float64.valueOf(N);
//        }
        
        Vector<Float64> iv = DenseVector.valueOf(ix1,ix2);
        
        System.out.println(f2.evaluate(iv).toString());
        System.out.println(f2.gradient(iv).toString());
        //System.out.println(f2.hessian(iv).toString());
        
        Newton<Float64> n = new Newton<Float64>();
        n.setInitialPoint(iv);
        n.setMaxiterations(20);
        n.setNorm(new QuadraticNorm<Float64>());
        n.setParameters(Float64.valueOf(0.9));
        n.setPrecision(Float64.valueOf(0.0000000001));
        
        Vector<Float64> min = n.minimize(f2);
        System.out.println("minização: " + min.toString());
        System.out.println("fmin: " + f2.evaluate(min).toString());
        System.out.println("iterações: " + n.getIterationscount());
        System.out.println("erro: " + n.getErr());
        
        
    }

}
