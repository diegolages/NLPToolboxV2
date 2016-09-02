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


package br.ufrj.cos.nlptoolboxv2.methods.quasinewton;

import Jama.Matrix;
import br.ufrj.cos.nlptoolboxv2.Comparator;
import br.ufrj.cos.nlptoolboxv2.Function;
import br.ufrj.cos.nlptoolboxv2.LinearFunction;
import br.ufrj.cos.nlptoolboxv2.Norm;
import br.ufrj.cos.nlptoolboxv2.jamaextensions.DoubleArrayOperations;
import br.ufrj.cos.nlptoolboxv2.jamaextensions.Vector;
import br.ufrj.cos.nlptoolboxv2.linesearch.LineSearch;
import br.ufrj.cos.nlptoolboxv2.methods.MinimizationMethod;

/**
 *
 * @author Diego
 */
public abstract class QuasiNewton implements MinimizationMethod {

    double [] initialpoint = null;
    double precision;
    long maxiterations = -1;
    Norm norm = null;
    double err;
    long nits = 0;
    double two = 2;
    double zero = 0;
    
    private LineSearch linesearch = null;
    
    
    Comparator comparator = new Comparator();
    
    @Override
    public void setInitialPoint(double [] variables) {
        initialpoint = variables;
    }
    
    @Override
    public long getIterationscount() {
        return nits;
    }
    
//    private Vector makeEqualVector(int dimension, double value) {
//        double [] d = new double [dimension];
//        
//        for (int i=0;i<dimension;i++) {
//            d[i] = value;
//        }
//        
//        return new Vector(d);
//    }

    protected Matrix makeIdentity(int dimension) { // TODO: optimize (sparse, dense?)
        
        double [][] A = new double[dimension][dimension];
        for (int i=0;i<dimension;i++) {
            for (int j=0;j<dimension;j++) {
                if (i == j) {
                    A[i][j] = 1;
                } else {
                    A[i][j] = 0;
                }
            }
        }
        return new Matrix(A);
    }
    
    protected abstract Matrix getInverse(Matrix Bk, Matrix inverseBk, Matrix sk, Vector yk);
    
    protected abstract Matrix getNextBk(Matrix Bk, Matrix sk, Vector yk);
    
    @Override
    public double [] minimize(final Function f) throws Exception {
        
        two = 2;
        double one = 1;
        zero = 0;

        double [] oldxk = null;
        double [] xk = initialpoint;
        Vector yk = null;
        
        Matrix Bk = makeIdentity(initialpoint.length);
        Matrix inverseBk = makeIdentity(initialpoint.length);
        
        
        //Vector sk = makeEqualVector(initialpoint.length, zero);

        
        do {
            
            
            // sk = delta xk
            Matrix sk = inverseBk.times(DoubleArrayOperations.times(f.gradient2(xk), -1.0));
            
            final Matrix f_sk = sk;
            double []    f_xk = xk;

            final Matrix    hessian = f.hessian(f_xk);
            final double [] grad    = f.gradient2(f_xk);

            
            LinearFunction ln = new LinearFunction() {

                @Override
                public double evaluate(double x) throws Exception {
                    
                    // return Taylor Series expansion of f
                    double a = f.evaluate(f_xk);
                    
                    a = a + DoubleArrayOperations.dotProduct(grad, f_sk.getColumnPackedCopy()) * x;
                    
                    //a = a + (grad.times(f_sk).times(x));
                    
                    return a;
                    
                }

                @Override
                public double derivative(double x) throws Exception {
                    
                    //TODO: REDO double a = grad.times(f_sk);
                    
                    double a = DoubleArrayOperations.dotProduct(grad, f_sk.getColumnPackedCopy());
                    
                    
                    //F a = grad.times(f_sk);
                    
                    
                    return a;
                }
            
            };
            
            // chute = f_lambdaxn.times(f_lambdaxn)
            double alpha = linesearch.minimize(ln, 1.0);
           // F alpha = numberfactory.makeNumber(0.0001);
            
            // xk+1 = xk + alpha * sk
            oldxk = xk;
            double fminold = f.evaluate(xk);

            xk = DoubleArrayOperations.plus(xk,(  sk.times(alpha) ));
            double fmin = f.evaluate(xk);
            
            
            // yk = gradient(xk+1) - gradient(xk) 
            yk = new Vector(DoubleArrayOperations.minusNew(f.gradient2(xk),( f.gradient2(oldxk) )));

            // TODO: REDO yk = DenseVector.valueOf(yk);
            
            double t = yk.times(sk).doubleSingleValue();
            
                    
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
    
    public void setParameters(double... p) {
    }

    public void setPrecision(double f) {
        precision = f;
    }

    public void setMaxiterations(long i) {
        maxiterations = i;
    }

    public void setNorm(Norm n) {
       norm = n;
    }

    public double getErr() {
        return err;
    }

    public LineSearch getLinesearch() {
        return linesearch;
    }

    public void setLinesearch(LineSearch linesearch) {
        this.linesearch = linesearch;
    }


    @Override
    public void resetIterationscount() {
        nits=0;
    }

}
