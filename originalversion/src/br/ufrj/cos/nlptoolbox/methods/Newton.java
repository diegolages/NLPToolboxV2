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

package br.ufrj.cos.nlptoolbox.methods;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class Newton<F extends Field<F>> implements MinimizationMethod<F> {

    Vector<F> initialpoint = null;
    F gamma = null;
    F precision = null;
    long maxiterations = -1;
    Norm<F> norm = null;
    F err = null;
    long nits = 0;
    
    Comparator<F> comparator = new Comparator<F>();
    
    private NumberFactory<F> numberfactory = null;
    
    
    public void setInitialPoint(Vector<F> variables) {
        initialpoint = variables;
    }
    
    public long getIterationscount() {
        return nits;
    }

     protected Matrix invertMatrix(Matrix A) {
         return A.inverse();
     }

    public Vector<F> minimize(Function<F> f) throws Exception {
        
        
        
        Vector<F> xn = initialpoint;

        Vector<F> xn1 = null;
        

        do {

            Matrix ihessian = invertMatrix(f.hessian(xn));
            //Matrix ihessian = f.hessian(xn).inverse();
            Vector<F> g = f.gradient(xn);

            Vector m = ihessian.times(g);

            xn1 = xn.minus(m.times(gamma));

            err = norm.norm(xn1.minus(xn));
            
            nits++;
            
            xn = xn1;

        } while (comparator.compare(err, precision) > 0 && nits < maxiterations);
        
        return xn1;
    }
    
    

    public void setParameters(F... p) {
        gamma = p[0];
    }

    public void setPrecision(F f) {
        precision = f;
    }

    public void setMaxiterations(long i) {
        maxiterations = i;
    }

    public void setNorm(Norm<F> n) {
       norm = n;
    }

    public F getErr() {
        return err;
    }
    
    public NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }

    @Override
    public void setLinesearch(LineSearch linesearch) {
        
    }

    @Override
    public void resetIterationscount() {
        nits=0;
    }

}
