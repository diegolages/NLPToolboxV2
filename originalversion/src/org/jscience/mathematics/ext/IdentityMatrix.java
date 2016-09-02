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
public class IdentityMatrix<F extends Field<F>> extends Matrix<F> {

    int n=0;
    F one=null;
    F zero=null;
    
    private IdentityMatrix(int _n, F _one, F _zero) {
        n = _n;
        one = _one;
        zero = _zero;
    }
    
    public static <F extends Field<F>> IdentityMatrix<F> newInstance(int _n, F _one, F _zero) {
        return new IdentityMatrix(_n, _one, _zero);
    }
    
    
    @Override
    public int getNumberOfRows() {
        return n;
    }

    @Override
    public int getNumberOfColumns() {
        return n;
    }

    @Override
    public F get(int i, int j) {
        return i == j ? one : zero;
    }

    @Override
    public Vector<F> getRow(int i) {
        return SparseVector.valueOf(n,zero,i,one);
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<F> getColumn(int j) {
        return SparseVector.valueOf(n,zero,j,one);
    }

    @Override
    public Vector<F> getDiagonal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Matrix<F> opposite() {
        return getSparseMatrix().opposite();
    }
    
    private SparseMatrix<F> getSparseMatrix() {
        return SparseMatrix.valueOf(this, zero);
    }

    @Override
    public Matrix<F> plus(Matrix<F> that) {
        return getSparseMatrix().plus(that);
    }

    @Override
    public Matrix<F> times(F k) {
        return getSparseMatrix().times(k);
    }

    @Override
    public Vector<F> times(Vector<F> v) {
        return getSparseMatrix().times(v);
    }

    @Override
    public Matrix<F> times(Matrix<F> that) {
        return that;
    }

    @Override
    public Matrix<F> inverse() {
        return this;
    }

    @Override
    public F determinant() {
        return one;
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
