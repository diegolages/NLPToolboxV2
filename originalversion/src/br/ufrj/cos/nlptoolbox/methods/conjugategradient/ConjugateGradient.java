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


package br.ufrj.cos.nlptoolbox.methods.conjugategradient;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.LinearFunction;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public abstract class ConjugateGradient<F extends Field<F>> implements MinimizationMethod<F> {

    Vector<F> initialpoint = null;
    F precision = null;
    long maxiterations = -1;
    Norm<F> norm = null;
    F err = null;
    long nits = 0;
    F two = null;
    F zero = null;
    
    private LineSearch<F> linesearch = null;
    private NumberFactory<F> numberfactory = null;
    
    
    Comparator<F> comparator = new Comparator<F>();
    
    public void setInitialPoint(Vector<F> variables) {
        initialpoint = variables;
    }
    
    public long getIterationscount() {
        return nits;
    }

    public Vector<F> minimize(final Function<F> f) throws Exception {
        
        two = numberfactory.makeNumber(2L);
        F one = numberfactory.makeNumber(1L);
        zero = numberfactory.makeNumber(0L);
        
        Vector<F> oldxn = null;
        Vector<F> xn = initialpoint;
        
        Vector<F> deltaxn_1 = f.gradient(initialpoint).opposite();
        Vector<F> deltaxn = null;
        
        Vector<F> lambdaxn_1 = deltaxn_1;
        Vector<F> lambdaxn = null;
        
        
        do {
            
            if (deltaxn != null) {
                deltaxn_1 = deltaxn;
            }
            deltaxn = f.gradient(xn).opposite();
            
            F beta = getBeta(deltaxn, deltaxn_1,lambdaxn_1,f, xn);
            
            if (lambdaxn != null) {
                lambdaxn_1 = lambdaxn;
            }
            lambdaxn = lambdaxn_1.times(beta).plus(deltaxn);
            
            final Vector<F> f_lambdaxn = lambdaxn;
            final Vector<F> f_xn = xn;
            // minimize alpha
            
            LinearFunction<F> ln = new LinearFunction<F>() {

                @Override
                public F evaluate(F x) throws Exception {
                    
                    // return Taylor Series expansion of f
                    F a = f.evaluate(f_xn);
                    
                    a = a.plus (f.gradient(f_xn).times(f_lambdaxn).times(x));
                    
    ////TODO                a = a.plus (x.times(x).times(two.inverse()).times( f_lambdaxn.times(f.hessian(f_xn).times(f_lambdaxn)) ) );
                    
                    return a;
                    
                }

                @Override
                public F derivative(F x) throws Exception {
                    
                    F a = f.gradient(f_xn).times(f_lambdaxn);
                    
//// TODO                    a = a.plus ( x.times( f_lambdaxn.times(f.hessian(f_xn).times(f_lambdaxn)) )   );
                    
                    return a;
                }
            
            };
            
            // chute = f_lambdaxn.times(f_lambdaxn)
            F alpha = linesearch.minimize(ln, zero);
            
            oldxn = xn;
            xn = xn.plus(   lambdaxn.times(alpha));
            
            err = norm.norm(xn.minus(oldxn));
            
            nits++;
            
        } while (comparator.compare(err, precision) > 0 && nits < maxiterations);
        
        return xn;
    }
    

    
    protected abstract F getBeta(Vector<F> deltaxn, Vector<F> deltaxn_1,Vector<F> lambdaxn_1,Function<F> function, Vector<F> xn) throws Exception;

    public void setParameters(F... p) {
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

    public LineSearch getLinesearch() {
        return linesearch;
    }

    public void setLinesearch(LineSearch linesearch) {
        this.linesearch = linesearch;
    }

    public NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }

    @Override
    public void resetIterationscount() {
        nits=0;
    }

}
