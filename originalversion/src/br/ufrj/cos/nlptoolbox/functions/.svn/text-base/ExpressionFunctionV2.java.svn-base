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

import br.ufrj.cos.nlptoolbox.NumberFactory;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Double;
import java.util.List;
import java.util.StringTokenizer;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.ASTFunNode;
import org.nfunk.jep.Node;

/**
 *
 * @author Diego
 */
public class ExpressionFunctionV2<F extends Field<F>> implements NumberFactoryableFunction<F>{
    
    
    String expression="";

     int nvariables = 0;

     DJep jep = new DJep();

     Node [] gradientexpressions = null;

     Node [][] hessianexpressions = null;
    
    public ExpressionFunctionV2(String exp) throws Exception {
        this(exp,0);
    }

    public ExpressionFunctionV2(String exp, int _nvariables) throws Exception {
        expression = exp;


        jep.addStandardConstants();
        jep.addStandardFunctions();
        jep.addComplex();
        jep.setAllowUndeclared(true);
        jep.setAllowAssignment(true);
        jep.setImplicitMul(true);

        // Sets up standard rules for differentiating sin(x) etc.
        jep.addStandardDiffRules();

        nvariables = _nvariables;
        if (nvariables == 0) {
            nvariables = findNvariables(expression);
        }

        for (int i=0;i<nvariables;i++) {
            jep.addVariable("x" + (i+1), 0);
        }

        jep.parseExpression(expression);

        gradientexpressions = new Node[nvariables];

        for (int i=0;i<nvariables;i++) {

            String varname = "x" + (i+1);

            Node gnode = jep.differentiate(jep.getTopNode(), varname);

            gradientexpressions[i] = gnode;

        }

        hessianexpressions = new Node[nvariables][nvariables];

        for (int i=0;i<nvariables;i++) {

            String varname_i = "x" + (i+1);
            Node gnode = jep.differentiate(jep.getTopNode(), varname_i);

            for (int j=0;j<nvariables;j++) {

                String varname_j = "x" + (j+1);

                Node hnode = jep.differentiate(gnode, varname_j);

                hessianexpressions[i][j] = hnode;

                //ASTFunNode astfunnode = (ASTFunNode)hnode;
               // System.out.println("compute hessian: i,j: " + i + "," + j);
                

//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                PrintStream ps = new PrintStream(baos);
//                jep.print(hnode, ps);
//                String content = baos.toString();
//
//                System.out.println("exp: " + content);
            }
        }
    }

    private int findNvariables(String expression) {
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

    @Override
    public String toString() {
        return expression;
    }
    
    public String getGradientString() throws Exception {
//        getPolynomial();
//        String ret = "[";
//        for (Variable<F> v : getVariables()) {
//            ret = ret + cachedpolynomial.differentiate(v).toText() + ",";
//        }
//        ret = ret + "]";
//        return ret;
        return null;
    }
    
    public String getHessianString() throws Exception {
//        getPolynomial();
//        String ret = "[";
//        for (Variable<F> v1 : getVariables()) {
//            for (Variable<F> v2 : getVariables()) {
//                ret = ret + cachedpolynomial.differentiate(v1).differentiate(v2).toText() + ",";
//            }
//            ret = ret + "\n";
//        }
//        ret = ret + "]";
//        return ret;
        return null;
    }
    
    private NumberFactory<F> numberfactory = null;
    

    public void setVariables(List<Variable<F>> l) throws Exception {
//        variables = l;
    }

    public //new Variable.Local<Float64>("x2");
    NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }

    @Override
    public F evaluate(Vector<F> variables) throws Exception {

        for (int i=0;i<nvariables;i++) {
            jep.setVarValue("x" + (i+1), variables.get(i));
        }

        //Node node = jep.parse(expression);

        //jep.println(node);

        Double obj = (Double) jep.evaluate(jep.getTopNode());

        return numberfactory.makeNumber(obj);

    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        for (int i=0;i<nvariables;i++) {
            jep.setVarValue("x" + (i+1), variables.get(i));
        }

        java.util.List list = new java.util.Vector();

        for (int i=0;i<nvariables;i++) {
            Node gnode = gradientexpressions[i];

            Double obj = (Double) jep.evaluate(gnode);
            list.add(numberfactory.makeNumber(obj));
        }

        DenseVector<F> ret = DenseVector.valueOf(list);

        return ret;
    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {
        for (int i=0;i<nvariables;i++) {
            jep.setVarValue("x" + (i+1), variables.get(i));
        }

        java.util.List vlist = new java.util.Vector();

        for (int i=0;i<nvariables;i++) {

            java.util.List list = new java.util.Vector();

            for (int j=0;j<nvariables;j++) {
                Node hnode = hessianexpressions[i][j];

                Double obj = (Double) jep.evaluate(hnode);
                list.add(numberfactory.makeNumber(obj));
            }

            DenseVector<F> dv = DenseVector.valueOf(list);

            vlist.add(dv);

        }

        return DenseMatrix.valueOf(vlist);
    }
    
}
