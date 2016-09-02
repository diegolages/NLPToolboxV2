/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.ext;


import java.util.List;
import org.jscience.mathematics.number.Float64;

import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Float64Matrix;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseMatrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 * TODO
 */
public final class QRDecomposition  {
    private Matrix<Float64> A=null;
    private Matrix<Float64> R = null;
    private Matrix<Float64> Q = null;
    
    private static Vector makeE1(int numberOfColumns) {
        return DenseVector.valueOf(SparseVector.valueOf(numberOfColumns, Float64.ZERO,0,Float64.ONE));
    }
    
    private static Float64Matrix makeI(int n) {
        List<Float64> l = new java.util.Vector();
        for (int i=0;i<n;i++) {
            l.add(Float64.ONE);
        }
        
        return Float64Matrix.valueOf(SparseMatrix.valueOf(DenseVector.valueOf(l), Float64.ZERO));
    }

    
    
    static Float64 sqrt2 = Float64.valueOf(2).sqrt();

    /**
     * TODO
     */
    public static QRDecomposition valueOf(
            Float64Matrix source) {
        
        Matrix Q=null;
        Matrix R=null;
        
       // Float64Matrix s2 = 
        
        Matrix U = null;
        Matrix bigU = null;
        
        
        Matrix<Float64> s = source;
        
        int n=0;
        
        while (s != null) {
        
            
            Float64 a11 = s.get(0,0);

            Vector<Float64> A1 = s.getColumn(0);

            Float64 beta = a11.divide(a11.abs()).times(A1.times(A1).sqrt()).opposite();

            Vector e1 = makeE1(s.getNumberOfRows());

            Vector<Float64> y = A1.minus(e1.times(beta));

            Float64 alpha = sqrt2.divide(y.times(y).sqrt());

            Vector<Float64> v = y.times(alpha);

            Matrix<Float64> v1 = DenseMatrix.valueOf(DenseVector.valueOf(v)).transpose();
            Matrix<Float64> v2 = v1.transpose();


            Matrix<Float64> I = (Matrix<Float64>)IdentityMatrix.newInstance(s.getNumberOfRows(), Float64.ONE, Float64.ZERO);//makeI(s.getNumberOfRows());

            U = I.minus(v1.times(v2));

            U = DenseMatrix.valueOf(U);
            
            Matrix A2 = U.times(s);
            
            //System.out.println(U);
        
            if (s.getNumberOfColumns() > 1 && s.getNumberOfRows() > 1) {
                s = SubMatrix.valueOf(A2, 1, s.getNumberOfRows(), 1, s.getNumberOfColumns());
            } else {
                s = null;
            } 
            
            if (n == 0) {
                bigU = U;
                Q = bigU;
                
            } else {
                bigU = BlockMatrix.newInstance(
                          IdentityMatrix.newInstance(
                             n, 
                             Float64.ONE, 
                             Float64.ZERO), 
                          ZeroMatrix.newInstance(n, bigU.getNumberOfColumns()-n, Float64.ZERO), 
                          ZeroMatrix.newInstance(bigU.getNumberOfRows()-n, n, Float64.ZERO), 
                          U);
                
                Q = Q.times(bigU);
            }
            
            n++;
        }
        
        return new QRDecomposition(source,Q);
    }

    private QRDecomposition(Matrix<Float64> _A, Matrix<Float64> _Q) {
        A = _A;
        Q = _Q;
        R = Q.transpose().times(A);
    }

    /**
     * Constructs the LU decomposition of the specified matrix.
     * We make the choise of Lii = ONE (diagonal elements of the
     * lower triangular matrix are multiplicative identities).
     *
     * @param  source the matrix to decompose.
     * @throws MatrixException if the matrix source is not square.
     */
    private void construct(Matrix source) {
//        _LU = source instanceof DenseMatrix ? ((DenseMatrix<F>) source).copy()
//                : DenseMatrix.valueOf(source);
//        _pivots.clear();
//        for (int i = 0; i < _n; i++) {
//            _pivots.add(Index.valueOf(i));
//        }
//
//        // Main loop.
//        Comparator<Field> cmp = QRDecomposition.getPivotComparator();
//        final int n = _n;
//        for (int k = 0; k < _n; k++) {
//
//            if (cmp != null) { // Pivoting enabled.
//                // Rearranges the rows so that the absolutely largest
//                // elements of the matrix source in each column lies
//                // in the diagonal.
//                int pivot = k;
//                for (int i = k + 1; i < n; i++) {
//                    if (cmp.compare(_LU.get(i, k), _LU.get(pivot, k)) > 0) {
//                        pivot = i;
//                    }
//                }
//                if (pivot != k) { // Exchanges.
//                    for (int j = 0; j < n; j++) {
//                        F tmp = _LU.get(pivot, j);
//                        _LU.set(pivot, j, _LU.get(k, j));
//                        _LU.set(k, j, tmp);
//                    }
//                    int j = _pivots.get(pivot).intValue();
//                    _pivots.set(pivot, _pivots.get(k));
//                    _pivots.set(k, Index.valueOf(j));
//                    _permutationCount++;
//                }
//            }
//
//            // Computes multipliers and eliminate k-th column.
//            F lukkInv = _LU.get(k, k).inverse();
//            for (int i = k + 1; i < n; i++) {
//                // Multiplicative order is important
//                // for non-commutative elements.
//                _LU.set(i, k, _LU.get(i, k).times(lukkInv));
//                for (int j = k + 1; j < n; j++) {
//                    _LU.set(i, j, _LU.get(i, j).plus(
//                            _LU.get(i, k).times(_LU.get(k, j).opposite())));
//                }
//            }
//        }
    }

    private QRDecomposition() {
    }

    public Matrix<Float64> getA() {
        return A;
    }

    public void setA(Matrix<Float64> A) {
        this.A = A;
    }

    public Matrix<Float64> getR() {
        return R;
    }

    public void setR(Matrix<Float64> R) {
        this.R = R;
    }

    public Matrix<Float64> getQ() {
        return Q;
    }

    public void setQ(Matrix<Float64> Q) {
        this.Q = Q;
    }

}