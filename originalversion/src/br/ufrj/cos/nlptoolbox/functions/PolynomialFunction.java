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


package br.ufrj.cos.nlptoolbox.functions;

import br.ufrj.cos.nlptoolbox.Function;
import java.util.List;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public abstract class PolynomialFunction<F extends Field<F>> implements Function<F>{
    
    
    protected abstract Polynomial<F> getPolynomial() throws Exception;
    
    protected abstract java.util.List<Variable<F>> getVariables();
    
    List<Polynomial<F>> initedgradientpolynomials = null;
    
    protected List<Polynomial<F>> getGradientPolynomials() throws Exception {
    
        if (initedgradientpolynomials != null) {
            return initedgradientpolynomials;
        }
        
        Polynomial<F> p = getPolynomial();
        
        List<Polynomial<F>> ret = new java.util.Vector();
        
        //for (Variable<F> v : p.getVariables()) {
        for (Variable<F> v : getVariables()) {

            Polynomial<F> d = p.differentiate(v);
            
            //System.out.println("d(" + v.getSymbol() + ") = " + d);
            
            ret.add(d);
            
        }
        
        initedgradientpolynomials = ret;
        
        return initedgradientpolynomials;
    }
    
    List<List<Polynomial<F>>> initedhessianpolynomials = null;
    
    protected List<List<Polynomial<F>>> getHessianPolynomials() throws Exception{
    
        if (initedhessianpolynomials != null) {
            return initedhessianpolynomials;
        }
        
        Polynomial<F> p = getPolynomial();
        
        List<List<Polynomial<F>>> ret = new java.util.Vector();
        
        for (Variable<F> v1 : getVariables()) {
            
            List<Polynomial<F>> r2 = new java.util.Vector();
            
            for (Variable<F> v2 : getVariables()) {
        
                Polynomial<F> d = p.differentiate(v1).differentiate(v2);
            
               /// System.out.println("d(" + v1.getSymbol() + "," + v2.getSymbol() + ") = " + d);
            
                r2.add(d);
            }
            
            ret.add(r2);
        }
        
        initedhessianpolynomials = ret;
        
        return initedhessianpolynomials;
    }
    
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        Polynomial<F> p = getPolynomial();
        return evaluate(p, variables);
    }
    
    private F evaluate(Polynomial<F> p, Vector<F> variables) {
        
        //System.out.println(p.toString());
        
        
        int m = getVariables().size();
        

        for (int i=0;i<m;i++) {

            //p.getVariables().get(i).set(variables.get(i));
            getVariables().get(i).set(variables.get(i));
        }

        return p.evaluate();
        
    }

    @Override
    public Vector<F> gradient(Vector<F> variables)  throws Exception {
        
        List<F> _ret = new java.util.Vector();
        
        for (Polynomial<F> p : getGradientPolynomials()) {
            
            _ret.add(evaluate(p, variables));
            
        }

        
        
        return DenseVector.valueOf(_ret);
        
    }

    @Override
    public Matrix hessian(Vector<F> variables)  throws Exception {
       
        List<DenseVector<F>> _ret = new java.util.Vector();
        
        List<List<Polynomial<F>>> hessianpolynomials = getHessianPolynomials();
        
        for (List<Polynomial<F>> l : hessianpolynomials) {
        
            List<F> __ret = new java.util.Vector();
            
            for (Polynomial<F> p : l) {
                __ret.add(evaluate(p, variables));
            }
            
            _ret.add(DenseVector.valueOf(__ret));
        }
        
        return DenseMatrix.valueOf(_ret);
        
    }

    
    
}
