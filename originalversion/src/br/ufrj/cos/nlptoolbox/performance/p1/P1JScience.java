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


package br.ufrj.cos.nlptoolbox.performance.p1;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.functions.F1;
import br.ufrj.cos.nlptoolbox.functions.F2;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class P1JScience {

    int N = 100000;
    
    double M = 1000000000;
    
    Function functions[] = new Function[] {
        new F1(),
        new F2()
    };
    
    List<Vector<Float64>> points = new java.util.LinkedList();
    
    
    public void init() {
        for (int i=0;i<N;i++) {
            Float64 x1 = Float64.valueOf(Math.random()*M);
            Float64 x2 = Float64.valueOf(Math.random()*M);
            points.add(DenseVector.valueOf(x1,x2));
        }
    }
    
    public static void main(String argv[]) throws Exception {
        P1JScience p = new P1JScience();
        p.init();
        p.run();
    }
    
    public void run() throws Exception {
        
        for (Function f : functions) {
            
            for (Vector<Float64> v : points) {
            
                f.evaluate(v);
                f.gradient(v);
                f.hessian(v);
            
            }
            
        }
    }
    
}
