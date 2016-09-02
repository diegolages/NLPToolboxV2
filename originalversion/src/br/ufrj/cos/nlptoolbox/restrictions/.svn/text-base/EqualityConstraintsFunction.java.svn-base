/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.restrictions;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenaltyFunction;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author lages
 */
public class EqualityConstraintsFunction<F extends Field<F>> implements Function<F> {

    Function<F>       function=null;
    Function<F>[] functions=null;
    F         lambda=null;

    public EqualityConstraintsFunction(Function<F> f, Function<F>[] fs,F _lambda,NumberFactory nf) {
        function = f;
        functions = fs;
        lambda = _lambda;
    }

    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        F ret = function.evaluate(variables);

        for (Function<F> func : functions) {
            ret = ret.plus(lambda.times(func.evaluate(variables)));
        }
        return ret;
        //return function.evaluate(variables).plus(barrierfunction.evaluate(variables).times(lambda));
    }

    @Override
    public Vector<F> gradient(Vector variables) throws Exception {

        Vector ret = function.gradient(variables);
        for (Function func : functions) {
            ret = ret.plus(func.gradient(variables).times(lambda));
        }
        return ret;

    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {

        Matrix ret = function.hessian(variables);
        for (Function func : functions) {
            ret = ret.plus(func.hessian(variables).times(lambda));
        }
        return ret;
    }

}
