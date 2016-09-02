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


package br.ufrj.cos.nlptoolbox.restrictions.planebarrier;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV2;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenaltyFunction;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PlaneBarrierCompositeFunction<F extends Field<F>> implements Function<F> {

    Function<F>       function=null;
    ExpressionFunctionV2<F>       auxfunction=null;
    F                 mi=null;

    int dimension = 0;
    String variable = null;
    int limit;
    boolean lowerbound;
    
    public PlaneBarrierCompositeFunction(Function<F> f, int _dimension, String _variable, int _limit, boolean _lowerbound, F _mi,NumberFactory<F> nf) throws Exception {
        function = f;
        dimension = _dimension;
        variable = _variable;
        limit = _limit;
        lowerbound = _lowerbound;
        if (lowerbound) {
            auxfunction = new ExpressionFunctionV2<F>("pow((" + Math.E + "),(" + _mi + "*(-" + variable + "+ (" + limit + "))))", dimension);
        } else {
            auxfunction = new ExpressionFunctionV2<F>("pow((" + Math.E + "),(-" + _mi + "*(-" + variable + "+ (" + limit + "))))", dimension);
        }
        auxfunction.setNumberfactory(nf);
        mi = _mi;
    }
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        return function.evaluate(variables).plus(auxfunction.evaluate(variables));
    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        return function.gradient(variables).plus(auxfunction.gradient(variables));
    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {
        return function.hessian(variables).plus(auxfunction.hessian(variables));
    }

}
