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
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3Factory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PlaneBarrierCompositeFunctionFloat64 implements Function{

    Function       function=null;
    Function       auxfunction=null;
    Float64                 mi=null;

    int dimension = 0;
    String variable = null;
    double limit;
    boolean lowerbound;
    
    public PlaneBarrierCompositeFunctionFloat64(Function f, int _dimension, String _variable, double _limit, boolean _lowerbound, Float64 _mi) throws Exception {
        function = f;
        dimension = _dimension;
        variable = _variable;
        limit = _limit;
        lowerbound = _lowerbound;
        if (lowerbound) {
            auxfunction = ExpressionFunctionV3Factory.makeExpressionFunctionV3("exp((" + _mi + "*(-" + variable + "+ (" + limit + "))))", dimension);
        } else {
            auxfunction = ExpressionFunctionV3Factory.makeExpressionFunctionV3("exp((-" + _mi + "*(-" + variable + "+ (" + limit + "))))", dimension);
        }
        mi = _mi;
    }
    
    @Override
    public Float64 evaluate(Vector variables) throws Exception {
        return (Float64) function.evaluate(variables).plus(auxfunction.evaluate(variables));
    }

    @Override
    public Vector<Float64> gradient(Vector variables) throws Exception {
        return function.gradient(variables).plus(auxfunction.gradient(variables));
    }

    @Override
    public Matrix hessian(Vector variables) throws Exception {
        return function.hessian(variables).plus(auxfunction.hessian(variables));
    }

}
