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


package br.ufrj.cos.nlptoolbox.functions;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.parser.ExpressionParser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;

/**
 *
 * @author Diego
 */
public class ExpressionFunction<F extends Field<F>> extends PolynomialFunction<F> implements NumberFactoryableFunction<F>{
    
    //Variable<F> varXx = null;//new Variable.Local<Float64>("x1");
    //Variable<F> varY2 = null;//new Variable.Local<Float64>("x2");
    
    List<Variable<F>> variables = null;
    
    String expression="";

    int nvariables = 0;
    
    public ExpressionFunction(String exp) {
        expression = exp;
    }

    public ExpressionFunction(String string, int _nvariables) {
        this(string);
        nvariables = _nvariables;
    }

    @Override
    public String toString() {
        try {
            return getPolynomial().toString();
        } catch (Exception e) {
            return "null (exception)";
        }
    }
    
    public String getGradientString() throws Exception {
        getPolynomial();
        String ret = "[";
        for (Variable<F> v : getVariables()) {
            ret = ret + cachedpolynomial.differentiate(v).toText() + ",";
        }
        ret = ret + "]";
        return ret;
    }
    
    public String getHessianString() throws Exception {
        getPolynomial();
        String ret = "[";
        for (Variable<F> v1 : getVariables()) {
            for (Variable<F> v2 : getVariables()) {
                ret = ret + cachedpolynomial.differentiate(v1).differentiate(v2).toText() + ",";
            }
            ret = ret + "\n";
        }
        ret = ret + "]";
        return ret;
    }
    
    private NumberFactory<F> numberfactory = null;
    
    Polynomial<F> cachedpolynomial = null;
    
    @Override
    protected Polynomial<F> getPolynomial() throws Exception {
        if (cachedpolynomial == null) {
            ExpressionParser<F> ep = new ExpressionParser<F>(nvariables);
            ep.setNumberfactory(getNumberfactory());
//            try {
                cachedpolynomial = ep.parse(expression);
                int n = ep.getNVariables();
                variables = ep.getVariables();
                
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                return null;
//            }
        }
        return cachedpolynomial;   
    }
    
    @Override
    public List<Variable<F>> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable<F>> l) throws Exception {
        variables = l;
    }

    public //new Variable.Local<Float64>("x2");
    NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }
    
}
