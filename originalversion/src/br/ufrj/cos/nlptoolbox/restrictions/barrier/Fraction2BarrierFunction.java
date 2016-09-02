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


package br.ufrj.cos.nlptoolbox.restrictions.barrier;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class Fraction2BarrierFunction<F extends Field<F>> implements Function<F> {

    List<Function<F>> functions=null;
    NumberFactory<F> numberfactory=null;
    F zero = null;
    F one = null;
    F two = null;
    
    Comparator<F> comparator = new Comparator<F>();
    
    public Fraction2BarrierFunction(List<Function<F>> fs,NumberFactory<F> nf) {
        functions = fs;
        numberfactory = nf;
        zero = numberfactory.makeNumber(0);
        two = numberfactory.makeNumber(2);
        one = numberfactory.makeNumber(1);
    }
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        
        F r = zero;
        
        for (Function<F> f : functions) {
            F r2 = f.evaluate(variables).inverse();
            r = r.plus(r2);
        }
        
        return r.opposite();
    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        

        Vector<F> zerovector = null;

        // for each function

        for (Function<F> f : functions) {
            Vector<F> g = f.gradient(variables);

            F eval = f.evaluate(variables);

            F eval2 = eval.times(eval);

            g = g.times(eval2.inverse());

            if (zerovector == null) {
                zerovector = g;
            } else {
                zerovector.plus(g);
            }

        }

        return zerovector;

    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {
        List<DenseVector<F>> elements = new java.util.Vector();
           

        for (int i=0;i<variables.getDimension();i++) {
            
            List<F> vectorelements = new java.util.Vector();
            
            for (int j=0;j<variables.getDimension();j++) {
                
                F r2 = zero;
        
                for (int k=0;k<functions.size();k++) {

                    Function<F> f = functions.get(k);

                    //TODO: put this in the outer loop
                    F feval = f.evaluate(variables);

                    Vector<F> fgrad = f.gradient(variables);

                    Matrix<F> fhess = f.hessian(variables);

                    F _f1 = two.times(fgrad.get(i)).times(fgrad.get(j)).times(feval);

                    F f1 = fhess.get(i, j).times(feval.times(feval)).plus(_f1.opposite());

                    F f2 = feval.times(feval);

                    f2 = f2.times(f2);

                    F r = f1.times(f2.inverse());
                    
                    r2 = r2.plus(r);
                }

                
                vectorelements.add(r2);
                
            }
            
            DenseVector<F> dv = DenseVector.valueOf(vectorelements);
            elements.add(dv);
                
        }
        
        return DenseMatrix.valueOf(elements);
    }

}
