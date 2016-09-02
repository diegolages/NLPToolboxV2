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
import java.util.List;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class MultiplePlaneBarrierCompositeFunctionFloat64 implements Function{

    Function       function=null;
    Function       auxfunction=null;
    Float64                 mi=null;

    int dimension = 0;

    List<ExpV3PlaneBarrierRestriction> functions = null;
    
    public MultiplePlaneBarrierCompositeFunctionFloat64(Function f, int _dimension, List<ExpV3PlaneBarrierRestriction> fs, Float64 _mi) throws Exception {
        function = f;
        dimension = _dimension;

        functions = fs;

        //String allfunc = "";

        List<Function> funcs = new java.util.Vector();

        for (ExpV3PlaneBarrierRestriction pbr : fs) {

           if (pbr.isLowerbound()) {

               Function pfunc  =
                    new PlusFunction(
                        pbr.getFunction(),
                        ExpressionFunctionV3Factory.makeExpressionFunctionV3(
                            ""+pbr.getLimit(),
                            dimension)
                    );

               Function tcfunc = new TimesConstFunction(_mi.opposite(), pfunc);

               Function tfunc  = new ExpFunction(tcfunc);

               funcs.add(tfunc);

               // pfunc = -(-(func)+(limit)) = -(func)+(limit) = -limit + func
               // tcfunc = (mi*(-(func)+(limit))) = -mi*(-(func)+(limit))
               // exp (f0func)
               // + exp ((mi*(-(func)+(limit))))

              // allfunc = allfunc + " + " + " exp((" + _mi + "*(-(" + pbr.getFunctionstr() + ")+ (" + pbr.getLimit() + ")" + ")))";

                //auxfunction = new ExpressionFunctionV3(, dimension);
            } else {

               Function pfunc  =
                    new PlusFunction(
                        pbr.getFunction(),
                        ExpressionFunctionV3Factory.makeExpressionFunctionV3(
                            ""+pbr.getLimit(),
                            dimension)
                    );

               Function tcfunc = new TimesConstFunction(_mi, pfunc);

               Function tfunc  = new ExpFunction(tcfunc);

               funcs.add(tfunc);




               //allfunc = allfunc + " + " + " exp((-" + _mi + "*(-(" + pbr.getFunctionstr() + ")+ (" + pbr.getLimit() + ")" + ")))";

                //auxfunction = new ExpressionFunctionV3("exp((-" + _mi + "*(-" + variable + "+ (" + limit + "))))", dimension);
            }
        }

        Function [] afs = new Function[funcs.size()];

        for (int i=0;i<funcs.size();i++) {
            afs[i] = funcs.get(i);
        }

        auxfunction = new PlusFunction(afs);

        //auxfunction = ExpressionFunctionV3Factory.makeExpressionFunctionV3(allfunc, dimension);
        
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
