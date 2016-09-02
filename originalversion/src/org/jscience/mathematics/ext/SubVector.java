/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscience.mathematics.ext;

import java.util.List;
import javolution.context.StackContext;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.DimensionException;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class SubVector<F extends Field<F>> extends Vector<F> {

    Vector<F> source=null;
    int from=0; 
    int to=0;
    
    private SubVector(
        Vector<F> _source, 
        int _from, 
        int _to) {
        
        source = _source;
        from = _from;
        to = _to;
    
    }
    
    public static <F extends Field<F>> Vector<F> valueOf(
        Vector<F> _source, 
        int _from, 
        int _to) throws DimensionException {
    
        if (_from >= _to) {
            throw new DimensionException("linefrom >= lineto");
        }
        
        if (_from >= _source.getDimension()) {
            throw new DimensionException("Subvector not within row range");
        }
        
        List els = new java.util.Vector();
        
        for (int i=_from;i<_to;i++) {
            els.add(_source.get(i));
        }
        
        return DenseVector.valueOf(els);
        
        //return new SubVector(_source,_from,_to);
    }
    
    @Override
    public int getDimension() {
        return to-from;
    }

    @Override
    public F get(int i) {
        return source.get(i+from);
    }

    @Override
    public Vector<F> opposite() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<F> plus(Vector<F> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<F> times(F k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public F times(Vector<F> that) {
        final int n = to-from;
        if (that.getDimension() != n)
            throw new DimensionException();
        StackContext.enter();
        try { // Reduces memory allocation / garbage collection.
            F sum = get(0).times(that.get(0));
            for (int i = 1; i < n; i++) {
                sum = sum.plus(get(i).times(that.get(i)));
            }
            return StackContext.outerCopy(sum);
        } finally {
            StackContext.exit();
        }
    }

    @Override
    public Vector<F> copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
