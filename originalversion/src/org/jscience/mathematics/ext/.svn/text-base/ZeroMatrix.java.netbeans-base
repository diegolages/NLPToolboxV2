/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscience.mathematics.ext;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class ZeroMatrix<F extends Field<F>> extends Matrix<F> {

    int n=0;
    int m=0;
    F zero=null;
    
    private ZeroMatrix(int _i,int _j, F _zero) {
        n = _i;
        m = _j;
        zero = _zero;
    }
    
    public static <F extends Field<F>> ZeroMatrix<F> newInstance(int _i, int _j, F _zero) {
        return new ZeroMatrix(_i, _j, _zero);
    }
    
    
    @Override
    public int getNumberOfRows() {
        return n;
    }

    @Override
    public int getNumberOfColumns() {
        return m;
    }

    @Override
    public F get(int i, int j) {
        return zero;
    }

    @Override
    public Vector<F> getRow(int i) {
        return SparseVector.valueOf(m, zero, 0, zero);
    }

    @Override
    public Vector<F> getColumn(int j) {
        return SparseVector.valueOf(n, zero, 0, zero);
    }

    @Override
    public Vector<F> getDiagonal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> opposite() {
        return this;
    }
    
    private SparseMatrix<F> getSparseMatrix() {
        return SparseMatrix.valueOf(this, zero);
    }

    @Override
    public Matrix<F> plus(Matrix<F> that) {
        return that;
    }

    @Override
    public Matrix<F> times(F k) {
        return this;
    }

    @Override
    public Vector<F> times(Vector<F> v) {
        return SparseVector.valueOf(v.getDimension(), zero, 0, zero);
    }

    @Override
    public Matrix<F> times(Matrix<F> that) {
        return this;
    }

    @Override
    public Matrix<F> inverse() {
        return null;
    }

    @Override
    public F determinant() {
        return zero;
    }

    @Override
    public Matrix<F> transpose() {
        return this;
    }

    @Override
    public F cofactor(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> adjoint() {
        return this;
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
        return getSparseMatrix();
    }

}
