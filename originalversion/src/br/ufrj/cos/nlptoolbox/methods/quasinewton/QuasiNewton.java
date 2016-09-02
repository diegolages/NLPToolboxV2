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


package br.ufrj.cos.nlptoolbox.methods.quasinewton;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.LinearFunction;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public abstract class QuasiNewton<F extends Field<F>> implements MinimizationMethod<F> {

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
    
    private DenseVector<F> makeEqualVector(int dimension, F value) {
        java.util.List<F> elements = new java.util.Vector();
        
        for (int i=0;i<dimension;i++) {
            elements.add(value);
        }
        
        return  DenseVector.valueOf(elements);
    }

    protected Matrix<F> makeIdentity(int dimension) {
        
        F one = numberfactory.makeNumber(1);
        
        
        DenseVector<F> diagonal = makeEqualVector(dimension, one);
        
        return SparseMatrix.valueOf(diagonal, numberfactory.makeNumber(0));
    }
    
    protected abstract Matrix<F> getInverse(Matrix<F> Bk, Matrix<F> inverseBk, Vector<F> sk, Vector<F> yk);
    
    protected abstract Matrix<F> getNextBk(Matrix<F> Bk, Vector<F> sk, Vector<F> yk);
    
    public Vector<F> minimize(final Function<F> f) throws Exception {
        
        two = numberfactory.makeNumber(2L);
        F one = numberfactory.makeNumber(1L);
        zero = numberfactory.makeNumber(0L);

        Vector<F> oldxk = null;
        Vector<F> xk = initialpoint;
        Vector<F> yk = null;
        
        Matrix<F> Bk = DenseMatrix.valueOf(makeIdentity(initialpoint.getDimension()));
        Matrix<F> inverseBk = DenseMatrix.valueOf(makeIdentity(initialpoint.getDimension()));
        
        
        Vector<F> sk = makeEqualVector(initialpoint.getDimension(), zero);

        
        do {
            
            
            // sk = delta xk
            sk = inverseBk.times(f.gradient(xk).opposite());
            
            final Vector<F> f_sk = sk;
            final Vector<F> f_xk = xk;

            final Matrix    hessian = f.hessian(f_xk);
            final Vector<F> grad    = f.gradient(f_xk);

            
            LinearFunction<F> ln = new LinearFunction<F>() {

                @Override
                public F evaluate(F x) throws Exception {
                    
                    // return Taylor Series expansion of f
                    F a = f.evaluate(f_xk);
                    
                    a = a.plus (grad.times(f_sk).times(x));
                    
// TODO: fix                    a = a.plus (x.times(x).times(two.inverse()).times( f_sk.times(hessian.times(f_sk)) ) );
                    
                    return a;
                    
                }

                @Override
                public F derivative(F x) throws Exception {
                    
                    F a = grad.times(f_sk);
                    
   //TODO: fix                 a = a.plus ( x.times( f_sk.times(hessian.times(f_sk)) )   );
                    
                    return a;
                }
            
            };
            
            // chute = f_lambdaxn.times(f_lambdaxn)
            F alpha = linesearch.minimize(ln, one);
           // F alpha = numberfactory.makeNumber(0.0001);
            
            // xk+1 = xk + alpha * sk
            oldxk = xk;
            F fminold = f.evaluate(xk);
            xk = xk.plus(  sk.times(alpha) );
            F fmin = f.evaluate(xk);
            
            
            // yk = gradient(xk+1) - gradient(xk) 
            yk = f.gradient(xk).plus( f.gradient(oldxk).opposite() );

            yk = DenseVector.valueOf(yk);
            
            F t = sk.times(yk);
            
                    
//            if (comparator.compare(t, zero) < 0) {
//                System.err.println("OPPPSSS!!");
//            }
            
            Bk = getNextBk(Bk, sk, yk);
            inverseBk = getInverse(Bk, inverseBk, sk, yk);
            
            
            
            //err = norm.norm(xk.minus(oldxk));
            
            err = norm.norm(sk.times(alpha));
            
//            err = fmin.plus(fminold.opposite());
//            err = err.times(err);
            
            nits++;

            System.out.println("bfgs iteration");
            
        } while (comparator.compare(err, precision) > 0 && nits < maxiterations);
        
        
        return xk;
    }
    
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
