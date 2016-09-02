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
package br.ufrj.cos.nlptoolboxv2.functions;

import br.ufrj.cos.nlptoolboxv2.Function;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class ExpressionFunctionFactory {

    public static ExpressionFunction makeExpressionFunction(String exp) throws Exception {
       return ExpressionFunctionFactory.makeExpressionFunction(exp, 0);
    }

    public static ExpressionFunction makeExpressionFunction(String exp, int _nvariables) throws Exception {
        return makeExpressionFunction(exp, _nvariables, new HashMap());
    }

    static Map<String,Function> cache = (Map<String, Function>) Collections.synchronizedMap(new Cache<String, Function>(1000));

    public static ExpressionFunction makeExpressionFunction(String exp, int _nvariables, HashMap<String,Double> p) throws Exception {

        if (isSimpleOneVariableFunction(exp)) {
            return new SimpleOneVariableFunction(exp, _nvariables);
        }

        LinkedHashMap lhm = new LinkedHashMap();

        ExpressionFunction ret = (ExpressionFunction) cache.get(exp + "|" + _nvariables);
        if (ret != null) { 
            return ret;
        }

        if (!exp.contains("x")) {
            try {
                double v = Double.parseDouble(exp);
                ExpressionFunction f = new ConstFunction(v,_nvariables);
                cache.put(exp + "|" + _nvariables, f);
                return f;
            } catch (Exception e) {

            }
        }

        ExpressionFunction ret2 = new ExpressionFunction(exp,_nvariables,p);
        cache.put(exp + "|" + _nvariables, ret2);
        return ret2;
    }

    private static boolean isSimpleOneVariableFunction(String _exp) {
        String exp = _exp.trim();

        if (!exp.startsWith("x")) {
            return false;
        }

        String exp2 = exp.substring(1);

        try {
            Integer.parseInt(exp2);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
