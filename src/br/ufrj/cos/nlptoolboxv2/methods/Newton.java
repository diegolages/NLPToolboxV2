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

package br.ufrj.cos.nlptoolboxv2.methods;

import Jama.Matrix;
import br.ufrj.cos.nlptoolboxv2.Comparator;
import br.ufrj.cos.nlptoolboxv2.Function;
import br.ufrj.cos.nlptoolboxv2.Norm;
import br.ufrj.cos.nlptoolboxv2.jamaextensions.Vector;
import br.ufrj.cos.nlptoolboxv2.linesearch.LineSearch;

/**
 *
 * @author Diego
 */
public class Newton implements MinimizationMethod {

    double [] initialpoint = null;
    double precision;
    long maxiterations = -1;
    Norm norm = null;
    double err;
    long nits = 0;
    
    double gamma = 0.0;
    
    Comparator comparator = new Comparator();
    
    @Override
    public void setInitialPoint(double [] variables) {
        initialpoint = variables;
    }
    
    @Override
    public long getIterationscount() {
        return nits;
    }

     protected Matrix invertMatrix(Matrix A) {
         return A.inverse();
     }

    @Override
    public double [] minimize(final Function f) throws Exception {
        
        
        
        Matrix xn = new Vector(initialpoint);

        Matrix xn1 = null;
        

        do {

            Matrix ihessian = invertMatrix(f.hessian(xn.getVectorReference()));
            //Matrix ihessian = f.hessian(xn).inverse();
            double [] g = f.gradient2(xn.getVectorReference());

            Matrix m = ihessian.times(g);
            
            xn1 = xn.minus(m.times(gamma));

            //xn1 = DoubleArrayOperations.minusNew(xn, m.times(gamma).getColumnPackedCopy());
            // m = -0.004 , -2.98
            // xn1 = [-1.99 , 3.68 ]
            err = norm.norm(xn1.minus(xn));
            
            nits++;
            
            xn = xn1;

        } while (err >  precision && nits < maxiterations);
        
        return xn1.getColumnPackedCopy();
    }
    
    

    @Override
    public void setParameters(double... p) {
        gamma = p[0];
    }

    @Override
    public void setPrecision(double f) {
        precision = f;
    }

    @Override
    public void setMaxiterations(long i) {
        maxiterations = i;
    }

    @Override
    public void setNorm(Norm n) {
       norm = n;
    }

    @Override
    public double getErr() {
        return err;
    }
    
    @Override
    public void setLinesearch(LineSearch linesearch) {
        
    }

    @Override
    public void resetIterationscount() {
        nits=0;
    }

}
