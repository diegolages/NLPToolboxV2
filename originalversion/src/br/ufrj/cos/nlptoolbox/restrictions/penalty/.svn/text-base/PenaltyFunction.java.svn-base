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


package br.ufrj.cos.nlptoolbox.restrictions.penalty;

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
public class PenaltyFunction<F extends Field<F>> implements Function<F> {

    List<Function<F>> functions=null;
    NumberFactory<F> numberfactory=null;
    F zero = null;
    F one = null;
    F two = null;
    
    Comparator<F> comparator = new Comparator<F>();
    
    public PenaltyFunction(Function<F>[] fs,NumberFactory<F> nf) {
//        functions = new java.util.Vector();
//        for (Function<F> f : fs) {
//            functions.add(new PenMaxFunction<F>(f,nf));
//        }
//        numberfactory = nf;
//        zero = numberfactory.makeNumber(0);
//        two = numberfactory.makeNumber(2);
//        one = numberfactory.makeNumber(1);
    }
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        
        F r = zero;
        
        for (Function<F> f : functions) {
            F r2 = f.evaluate(variables);
            r = r.plus(r2.times(r2));
        }
        
        return r;
    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        F r = zero;
        
        List<Vector<F>> gradients = new java.util.Vector();
        
        for (Function<F> f : functions) {
            gradients.add(f.gradient(variables));
        }
        
        List<F> retelements = new java.util.Vector();
        
        for (int i=0;i<variables.getDimension();i++) {
            F r2 = zero;
        
            for (int j=0;j<functions.size();j++) {
            
                Function<F> f = functions.get(j);
                Vector<F>   gradient = gradients.get(j);
                
                r2 = r2.plus(f.evaluate(variables).times(gradient.get(i)).times(two));
                
            }
            
            retelements.add(r2);
        }
        
        return DenseVector.valueOf(retelements);
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

                    F e = f.evaluate(variables);
                    
                    F e2 = e.plus(one);
                    
                    F e3 = (F) f.hessian(variables).get(i, j);
                    
                    F e4 = e2.times(e3);
                    
                    r2 = r2.plus(e4);
                }
                
                vectorelements.add(r2);
                
            }
            
            DenseVector<F> dv = DenseVector.valueOf(vectorelements);
            elements.add(dv);
                
        }
        
        return DenseMatrix.valueOf(elements);
    }

}
