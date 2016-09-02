/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscience.mathematics.ext;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DimensionException;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class SubMatrix<F extends Field<F>> extends Matrix<F> {

    Matrix<F> source=null;
    int linefrom=0; 
    int lineto=0;
    int columnfrom=0;
    int columnto=0;
    
    private SubMatrix(
        Matrix<F> _source, 
        int _linefrom, 
        int _lineto,
        int _columnfrom,
        int _columnto) {
        
        source = _source;
        linefrom = _linefrom;
        lineto = _lineto;
        columnfrom = _columnfrom; 
        columnto = _columnto;
    
    }
    
    public static <F extends Field<F>> SubMatrix<F> valueOf(
        Matrix<F> _source, 
        int _linefrom, 
        int _lineto,
        int _columnfrom,
        int _columnto) throws DimensionException {
    
        if (_linefrom >= _lineto) {
            throw new DimensionException("linefrom >= lineto");
        }
        
        if (_columnfrom >= _columnto) {
            throw new DimensionException("columnfrom >= columnto");
        }
        
        if (_columnfrom >= _source.getNumberOfColumns()) {
            throw new DimensionException("Submatrix not within column range");
        }
        
        if (_linefrom >= _source.getNumberOfRows()) {
            throw new DimensionException("Submatrix not within row range");
        }
        
        return new SubMatrix(_source,_linefrom,_lineto,_columnfrom,_columnto);
    }
    
    @Override
    public int getNumberOfRows() {
        return lineto-linefrom;
    }

    @Override
    public int getNumberOfColumns() {
        return columnto-columnfrom;
    }

    @Override
    public F get(int i, int j) {
        return source.get(linefrom+i, columnfrom+j);
    }

    @Override
    public Vector<F> getRow(int i) {
        return SubVector.valueOf(source.getRow(linefrom+i),columnfrom,columnto);
    }

    @Override
    public Vector<F> getColumn(int j) {
        return SubVector.valueOf(source.getColumn(columnfrom+j),linefrom,lineto);
    }

    @Override
    public Vector<F> getDiagonal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> opposite() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> plus(Matrix<F> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> times(F k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<F> times(Vector<F> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> times(Matrix<F> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> inverse() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public F determinant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> transpose() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public F cofactor(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> adjoint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> tensor(Matrix<F> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<F> vectorization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
