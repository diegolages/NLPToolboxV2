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
import java.util.HashMap;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.SparseVector;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class FractionBarrierFunction<F extends Field<F>> implements Function<F> {

    //List<Function<F>> functions = null;
    Function<F> [] functions = null;
    NumberFactory<F> numberfactory = null;
    F zero = null;
//    F one = null;
    F two = null;
    Comparator<F> comparator = new Comparator<F>();

    public FractionBarrierFunction(Function<F> [] fs, NumberFactory<F> nf) {
        functions = fs;
//        for (Function<F> f : fs) {
//            functions.add(new MinusLogFunction<F>(f,nf));
//        }
        numberfactory = nf;
        zero = numberfactory.makeNumber(0);
        two = numberfactory.makeNumber(2);
//        one = numberfactory.makeNumber(1);
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

            eval = eval.times(eval);

            g = g.times(eval.inverse());

            if (zerovector == null) {
                zerovector = g;
            } else {
                zerovector.plus(g);
            }

        }

        if (zerovector == null) {
            return SparseVector.valueOf(variables.getDimension(), zero, new HashMap() {
            });
        }


        return zerovector;

    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {



        int dimension = variables.getDimension();

         List<DenseVector<F>> elements = new java.util.Vector(dimension);

        int functionssize = functions.length;



        //precalculate grads and hessians for functions...

        Object[] fevals = new Object[functionssize];
        Vector<F>[] fgrads = new Vector[functionssize];
        Matrix<F>[] fhesss = new Matrix[functionssize];
        boolean[] fgradzero = new boolean[functionssize];

//        double [][] d_fgrads = new double[functionssize][variables.getDimension()];
//        double [][][] d_fhesss = new double[functionssize][variables.getDimension()][variables.getDimension()];

        for (int k = 0; k < functionssize; k++) {

            Function<F> f = functions[k];

            F feval = f.evaluate(variables);

            Vector<F> fgrad = f.gradient(variables);

//            double[] d_fgrad = new double[fgrad.getDimension()];
//
//            for (int i = 0; i < fgrad.getDimension(); i++) {
//                d_fgrads[k][i] = ((Float64) (Object) (fgrad.get(i))).doubleValue();
//            }

            fgradzero[k] = isZero(fgrad);

            fevals[k] = feval;
            fgrads[k] = fgrad;

            if (!fgradzero[k]) {
                Matrix<F> fhess = f.hessian(variables);

                fhesss[k] = fhess;

//                for (int i = 0; i < fgrad.getDimension(); i++) {
//                    for (int j = 0; j < fgrad.getDimension(); j++) {
//                        d_fhesss[k][i][j] = ((Float64) (Object) (fhess.get(i,j))).doubleValue();
//                    }
//                }

            }


        }


        for (int i = 0; i < dimension; i++) {

            List<F> vectorelements = new java.util.Vector(dimension);

            for (int j = 0; j < dimension; j++) {

                F r2 = zero;

                for (int k = 0; k < functionssize; k++) {

                    //Function<F> f = functions.get(k);

                    F feval = (F) fevals[k];
                    Vector<F> fgrad = fgrads[k];
                    Matrix<F> fhess = fhesss[k];
                    boolean iszero = fgradzero[k];




                    if (!iszero) {

                        //performance optimization

////                        Float64 feval64 = (Float64) (Object) feval;
////                        double d_feval64 = feval64.doubleValue();

                        F _f1 = two.times(fgrad.get(i)).times(fgrad.get(j)).times(feval);
////                        double d__f1 = 2 * d_fgrads[k][i] * d_fgrads[k][j] * d_feval64;


                        F f2 = feval.times(feval);
////                        double d_f2 = d_feval64 * d_feval64;


                        F f1 = fhess.get(i, j).times(f2).plus(_f1.opposite());
////                        double d_f1 = d_fhesss[k][i][j] * d_f2 + -d__f1;

                        f2 = f2.times(f2);
////                        d_f2 = d_f2 * d_f2;

                        F r = f1.times(f2.inverse());
////                        double d_r = d_f1 * (1.0/d_f2);

                        r2 = r2.plus(r);
////                        r2 = r2.plus((F)(Object)Float64.valueOf(d_r));
                        

                    }
                }


                vectorelements.add(r2);

            }

            DenseVector<F> dv = DenseVector.valueOf(vectorelements);
            elements.add(dv);

        }

        return DenseMatrix.valueOf(elements);
    }

    private boolean isZero(Vector<F> fgrad) {

        if (fgrad.equals(fgrad.opposite())) {
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public Matrix hessian(Vector<F> variables) throws Exception {
//        List<DenseVector<F>> elements = new java.util.Vector();
//
//
//        for (int k = 0; k < functions.size(); k++) {
//
//            Function<F> f = functions.get(k);
//
//            //TODO: put this in the outer loop
//            F feval = f.evaluate(variables);
//
//            Vector<F> fgrad = f.gradient(variables);
//
//            Matrix<F> fhess = f.hessian(variables);
//
//            F r2 = zero;
//
//
//            for (int i = 0; i < variables.getDimension(); i++) {
//
//                List<F> vectorelements = new java.util.Vector();
//
//                for (int j = 0; j < variables.getDimension(); j++) {
//
//                    F _f1 = two.times(fgrad.get(i)).times(fgrad.get(j)).times(feval);
//
//                    F f1 = fhess.get(i, j).times(feval.times(feval)).plus(_f1.opposite());
//
//                    F f2 = feval.times(feval);
//
//                    f2 = f2.times(f2);
//
//                    F r = f1.times(f2.inverse());
//
//                    r2 = r2.plus(r);
//
//
//                    //r2 = r2.inverse();
//                    //r2 = r2.opposite();
//
//
//                    vectorelements.add(r2);
//
//                }
//
//                DenseVector<F> dv = DenseVector.valueOf(vectorelements);
//                elements.add(dv);
//
//            }
//
//        }
//
//        return DenseMatrix.valueOf(elements);
//    }
}
