/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscience.mathematics.ext;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.DimensionException;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class AppendedVector<F extends Field<F>> extends Vector<F> {

    Vector<F> v1=null;
    Vector<F> v2=null;
    
    
    private AppendedVector(
        Vector<F> _v1, 
        Vector<F> _v2) {
        
        v1 = _v1;
        v2 = _v2;
        
    }
    
    public static <F extends Field<F>> Vector<F> valueOf(
        Vector<F> _v1, 
        Vector<F> _v2) throws DimensionException {
        
        return new AppendedVector<F>(_v1, _v2);
    }
    
    @Override
    public int getDimension() {
        return v1.getDimension()+v2.getDimension();
    }

    @Override
    public F get(int i) {
        if (i < v1.getDimension()) {
            return v1.get(i);
        } else {
            return v2.get(i-v1.getDimension());
        }
    }
    
    DenseVector<F> getDenseVector() {
        return DenseVector.valueOf(this);
    }

    @Override
    public Vector<F> opposite() {
        return getDenseVector().opposite();
    }

    @Override
    public Vector<F> plus(Vector<F> that) {
        return getDenseVector().plus(that);
    }

    @Override
    public Vector<F> times(F k) {
        return getDenseVector().times(k);
    }

    @Override
    public F times(Vector<F> that) {
        return getDenseVector().times(that);
    }

    @Override
    public Vector<F> copy() {
        return getDenseVector();
    }

}
