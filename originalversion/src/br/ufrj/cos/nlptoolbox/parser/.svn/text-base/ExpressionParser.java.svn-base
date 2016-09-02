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


package br.ufrj.cos.nlptoolbox.parser;

import br.ufrj.cos.nlptoolbox.NumberFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.cheffo.jeplite.ASTConstant;
import org.cheffo.jeplite.ASTFunNode;
import org.cheffo.jeplite.ASTVarNode;
import org.cheffo.jeplite.JEP;
import org.cheffo.jeplite.SimpleNode;
import org.jscience.mathematics.function.Constant;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.structure.Field;

/**
 *
 * @author Diego
 */
public class ExpressionParser<F extends Field<F>> {

    int nvariables=0;

    public ExpressionParser() {
    }

    public ExpressionParser(int _nvariables) {
        nvariables = _nvariables;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        
//        Polynomial<Float64> f = null;
//        
//        ExpressionParser<Float64> mp = new ExpressionParser<Float64>();
//        NumberFactory<Float64> nf = new Float64NumberFactory();
//        mp.setNumberfactory(nf);
//        f = mp.parse("5+x2*x1^53");
//        System.out.println(f);
        System.out.println("np: " + new ExpressionParser().findNvariables("(1-x1)^2+100*(x2-x1)^2"));
    }

    public int getNVariables() {
        return nvariables;
    }
    
    List<Variable<F>> variables = new java.util.Vector();
    
    public List<Variable<F>> getVariables() {
        return variables;
    }
    
    
    
    private int findNvariables(String expression) {
        if (nvariables != 0) {
            return nvariables;
        }
        StringTokenizer st = new StringTokenizer(expression, "+-*/^()");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.startsWith("x")) {
                String number = token.substring(1);
                int pn = Integer.parseInt(number);
                if (pn > nvariables) {
                    nvariables = pn;
                }
            }
           // System.out.println("token: " + token);
        }
        return nvariables;
    }
    
    public Polynomial<F> parse(String expression) throws Exception {
        
        findNvariables(expression);
        JEP jep = new JEP();
        //jep.setVarNode("x1",new ASTVarNode(1));
        //jep.setVarNode("x2",new ASTVarNode(2));
        
        HashMap<String,Polynomial> symbols = new HashMap();
        
        int n = getNVariables();
        
        for (int i=0;i<n;i++) {
            Variable<F> var = new Variable.Local<F>("x"+(i+1));
            Polynomial<F> x = Polynomial.valueOf(numberfactory.makeNumber(1), var);
            symbols.put("x"+(i+1), x);
            jep.setVarNode("x"+(i+1),new ASTVarNode(2));
            variables.add(var);
        }
        
        
        jep.parseExpression(expression);
        //System.out.println(jep.toString());
       
        SimpleNode node = jep.getTopNode();
        
        
        
        
        //
        return getPolynomial(node, symbols);
        
        
        
      //  System.out.println(f.toString());
        
        
    }
    
    NumberFactory<F> numberfactory=null;
    
    public NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }

    private Polynomial<F> getPolynomial(SimpleNode node, Map<String, Polynomial> symbols) throws Exception {
        
        
        if (node instanceof ASTFunNode) {
            
            String operator = node.getName();
        
            SimpleNode left = node.jjtGetChild(0);
            
            if (node.jjtGetNumChildren() == 1) {

                if (left instanceof ASTConstant) {
                    if ("+".equals(operator)) {
                        return Constant.valueOf(numberfactory.makeNumber(node.getValue()));
                    } else if ("-".equals(operator)) {
                        return Constant.valueOf(numberfactory.makeNumber(-node.getValue()));
                    }
                    
                }

                String variablename = left.getName();
            
                return symbols.get(variablename);
            }
            
            SimpleNode right = node.jjtGetChild(1);
            
            
            
            Polynomial<F> a = null;
            Polynomial<F> b = null;
            
            
                if ("*".equals(operator)) {
                    a = getPolynomial(left,symbols);
                    b = getPolynomial(right,symbols);
                    
                    return a.times(b);
                } else if ("/".equals(operator)) {
                    a = getPolynomial(left,symbols);
                    b = getPolynomial(right,symbols);

                    return a.times(b).pow(-1);
                    //return getPolynomial(left,symbols).times(getPolynomial(right,symbols).pow(-1));
                } else if ("+".equals(operator)) {
                    a = getPolynomial(left,symbols);
                    b = getPolynomial(right,symbols);

                    return a.plus(b);
                    //return getPolynomial(left,symbols).plus(getPolynomial(right,symbols));
                } else if ("-".equals(operator)) {
                    a = getPolynomial(left,symbols);
                    b = getPolynomial(right,symbols);

                    return a.minus(b);
                    //return getPolynomial(left,symbols).minus(getPolynomial(right,symbols));
                }  else if ("^".equals(operator)) {
                    if (!(right instanceof ASTConstant)) {
                        throw new Exception("For now, the right side of ^ can only be integers");
                    }
                    
                    ASTConstant r = (ASTConstant)right;

                    a = getPolynomial(left,symbols);

                    return a.pow((int)r.getValue());
                    
                  //  return getPolynomial(left,symbols).p(getPolynomial(right,symbols));
                } else {
                    throw new Exception("Unknown operator: " + operator);
                }
            
        }
        
        if (node instanceof ASTVarNode) {
            
            String variablename = node.getName();
            
            return symbols.get(variablename);
        }
        
        if (node instanceof ASTConstant) {
            return Constant.valueOf(numberfactory.makeNumber(node.getValue()));
        }
        
        throw new Exception("Unidentified expression: " + node.toString());
    }

}
