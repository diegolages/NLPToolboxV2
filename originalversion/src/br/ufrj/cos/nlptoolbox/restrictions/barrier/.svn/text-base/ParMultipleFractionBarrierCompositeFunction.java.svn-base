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

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3;
import br.ufrj.cos.nlptoolbox.functions.ExpressionFunctionV3Factory;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenMaxFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.ExpFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.MinusFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.OppositeFunction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.ExpV3PlaneBarrierRestriction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.PlaneBarrierRestriction;
import br.ufrj.cos.nlptoolbox.restrictions.planebarrier.PlusFunction;
import java.util.Arrays;
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class ParMultipleFractionBarrierCompositeFunction<F extends Field<F>> implements Function{

    Function       function=null;
    FractionBarrierFunction       auxfunction=null;
    double                 mi=0;

    int dimension = 0;

    List<PlaneBarrierRestriction> functions = null;
    
    public ParMultipleFractionBarrierCompositeFunction(Function f, int _dimension, List<PlaneBarrierRestriction> fs, double _mi, NumberFactory nf) throws Exception {
        function = f;
        dimension = _dimension;

        functions = fs;

        Function [] ff=new Function[fs.size()];

        PlusFunction _p = new PlusFunction(ff);

        String allfunc = "";

        //Float64 __mi = (Float64)_mi;

        int idx=0;
        for (PlaneBarrierRestriction pbr : fs) {

           if (pbr.isLowerbound()) {

               //ExpressionFunctionV3 consfunc = ExpressionFunctionV3Factory.makeExpressionFunctionV3(pbr.getFunctionstr(),_dimension);

               Function consfunc = pbr.getFunction();


               Function _limit_func = ExpressionFunctionV3Factory.makeExpressionFunctionV3(""+ pbr.getLimit(), dimension);


               MinusFunction mf = new MinusFunction(_limit_func, consfunc, _mi);

               ff[idx] = mf;

                //auxfunction = new ExpressionFunctionV3(, dimension);
            } else {

               //ExpressionFunctionV3 consfunc = ExpressionFunctionV3Factory.makeExpressionFunctionV3(pbr.getFunctionstr(),_dimension);
               Function consfunc = pbr.getFunction();


               Function _limit_func = ExpressionFunctionV3Factory.makeExpressionFunctionV3(""+ pbr.getLimit(), dimension);


               MinusFunction mf = new MinusFunction( consfunc, _limit_func , _mi);
               

               ff[idx] = mf;

            }
           idx++;
        }


        auxfunction = new FractionBarrierFunction(ff,nf);//new ExpressionFunctionV3(allfunc, dimension);
        
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
