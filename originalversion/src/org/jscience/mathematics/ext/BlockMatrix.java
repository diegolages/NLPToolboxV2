/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jscience.mathematics.ext;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DimensionException;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 * 
 * M = A B
 *     C D
 * 
 */
public class BlockMatrix<F extends Field<F>> extends Matrix<F> {

    
    Matrix<F> A=null;
    Matrix<F> B=null;
    Matrix<F> C=null;
    Matrix<F> D=null;
    
    private BlockMatrix(Matrix<F> _A, Matrix<F> _B, Matrix<F> _C, Matrix<F> _D) {
        A = _A;
        B = _B;
        C = _C;
        D = _D;
    }
    
    public static <F extends Field<F>> BlockMatrix<F> newInstance(Matrix<F> _A, Matrix<F> _B, Matrix<F> _C, Matrix<F> _D) {
        
        if (_A.getNumberOfColumns() + _B.getNumberOfColumns() !=
            _C.getNumberOfColumns() + _D.getNumberOfColumns()) {
            throw new DimensionException("Invalid Dimensions");
        }
        
        if (_A.getNumberOfRows() + _C.getNumberOfRows() !=
            _B.getNumberOfRows() + _D.getNumberOfRows()) {
            throw new DimensionException("Invalid Dimensions");
        }
        
        
        return new BlockMatrix(_A,_B,_C,_D);
    }

    @Override
    public int getNumberOfRows() {
        return A.getNumberOfRows()+C.getNumberOfRows();
    }

    @Override
    public int getNumberOfColumns() {
        return A.getNumberOfColumns() + B.getNumberOfColumns();
    }

    @Override
    public F get(int i, int j) {
        if (i < A.getNumberOfRows()) {
            if (j < A.getNumberOfColumns()) {
                return A.get(i,j);
            } else {
                return B.get(i,j-A.getNumberOfColumns());
            }
        } else {
            if (j < A.getNumberOfColumns()) {
                return C.get(i-A.getNumberOfRows(),j);
            } else {
                return D.get(i-A.getNumberOfRows(),j-A.getNumberOfColumns());
            }
        }
    }
    
    private DenseMatrix<F> getDenseMatrix() {
        return DenseMatrix.valueOf(this);
    }

    @Override
    public Vector<F> getRow(int i) {
        if (i < A.getNumberOfRows()) {
            return AppendedVector.valueOf(A.getRow(i), B.getRow(i));
        } else {
            return AppendedVector.valueOf(C.getRow(i-A.getNumberOfRows()), D.getRow(i-A.getNumberOfRows()));
        }
    }

    @Override
    public Vector<F> getColumn(int j) {
        if (j < A.getNumberOfColumns()) {
            return AppendedVector.valueOf(A.getColumn(j), C.getColumn(j));
        } else {
            return AppendedVector.valueOf(B.getColumn(j-A.getNumberOfRows()), D.getColumn(j-A.getNumberOfRows()));
        }
    }

    @Override
    public Vector<F> getDiagonal() {
        return getDenseMatrix().getDiagonal();
    }

    @Override
    public Matrix<F> opposite() {
        return getDenseMatrix().opposite();
    }

    @Override
    public Matrix<F> plus(Matrix<F> that) {
        return getDenseMatrix().plus(that);
    }

    @Override
    public Matrix<F> times(F k) {
        return getDenseMatrix().times(k);
    }

    @Override
    public Vector<F> times(Vector<F> v) {
        return getDenseMatrix().times(v);
    }

    @Override
    public Matrix<F> times(Matrix<F> that) {
        return getDenseMatrix().times(that);
    }

    @Override
    public Matrix<F> inverse() {
        return getDenseMatrix().inverse();
    }

    @Override
    public F determinant() {
        return getDenseMatrix().determinant();
    }

    @Override
    public Matrix<F> transpose() {
        return getDenseMatrix().transpose();
    }

    @Override
    public F cofactor(int i, int j) {
        return getDenseMatrix().cofactor(i, j);
    }

    @Override
    public Matrix<F> adjoint() {
        return getDenseMatrix().adjoint();
    }

    @Override
    public Matrix<F> tensor(Matrix<F> that) {
        return getDenseMatrix().tensor(that);
    }

    @Override
    public Vector<F> vectorization() {
        return getDenseMatrix().vectorization();
    }

    @Override
    public Matrix<F> copy() {
        return getDenseMatrix();
    }
    
    
}
