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
import br.ufrj.cos.nlptoolbox.util.StringUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.ASTFunNode;
import org.nfunk.jep.Node;

/**
 *
 * @author Diego
 */
public class TestSimplifly {

    String expression = "";
    int nvariables = 0;
    DJep jep = new DJep();
    Node[] gradientexpressions = null;
    Node[][] hessianexpressions = null;

    Function stub = null;

    public static void main(String argv[]) throws Exception {
        new TestSimplifly();
    }

    String getNodeString(Node hnode) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        jep.print(hnode, ps);
        String content = baos.toString();

        return content;
    }

    public TestSimplifly() throws Exception {

       // expression = "0.347509729469927+0.347509729469927+(-0.0+-0.0*x1)*x5+(-0.0+-0.0*x1)*x6+(-0.0+-0.0*x1)*x8+(-0.0+-0.0*x1)*x12+(-0.0+-0.0*x2)*x3+(-0.0+-0.0*x2)*x12+(-0.0+-0.0*x2)*x30+(-0.0+-0.0*x3)*x2+(-0.0+-0.0*x3)*x4+(-0.0+-0.0*x3)*x5+(-0.0+-0.0*x3)*x6+(-0.0+-0.0*x3)*x7+(-0.0+-0.0*x3)*x8+(-0.0+-0.0*x3)*x9+(-0.0+-0.0*x3)*x10+(-0.0+-0.0*x3)*x11+(-0.0+-0.0*x3)*x12+(-0.0+-0.0*x3)*x13+(-0.0+-0.0*x3)*x14+(-0.0+-0.0*x3)*x15+(-0.0+-0.0*x3)*x16+(-0.0+-0.0*x3)*x17+(-0.0+-0.0*x3)*x18+(-0.0+-0.0*x3)*x19+(-0.0+-0.0*x3)*x20+(-0.0+-0.0*x3)*x21+-0.0+-0.0*x3+(-0.0+-0.0*x3)*x22+(-0.0+-0.0*x3)*x23+(-0.0+-0.0*x3)*x24+(-0.0+-0.0*x3)*x25+-0.0+-0.0*x3+(-0.0+-0.0*x3)*x26+(-0.0+-0.0*x3)*x27+(-0.0+-0.0*x3)*x28+(-0.0+-0.0*x3)*x29+(-0.0+-0.0*x3)*x31+(-0.0+-0.0*x4)*x3+(-0.0+-0.0*x4)*x5+(-0.0+-0.0*x4)*x6+(-0.0+-0.0*x4)*x8+(-0.0+-0.0*x4)*x12+(-0.0+-0.0*x4)*x30+(-0.0+-0.0*x5)*x1+(-0.0+-0.0*x5)*x3+(-0.0+-0.0*x5)*x4+(-0.0+-0.0*x5)*x11+(-0.0+-0.0*x5)*x12+(-0.0+-0.0*x5)*x14+(-0.0+-0.0*x5)*x18+(-0.0+-0.0*x5)*x20+(-0.0+-0.0*x5)*x22+(-0.0+-0.0*x5)*x23+(-0.0+-0.0*x5)*x26+(-0.0+-0.0*x5)*x27+(-0.0+-0.0*x5)*x29+(-0.0+-0.0*x5)*x30+(-0.0+-0.0*x5)*x31+(-0.0+-0.0*x6)*x1+(-0.0+-0.0*x6)*x3+(-0.0+-0.0*x6)*x4+(-0.0+-0.0*x6)*x11+(-0.0+-0.0*x6)*x12+(-0.0+-0.0*x6)*x14+(-0.0+-0.0*x6)*x18+(-0.0+-0.0*x6)*x20+(-0.0+-0.0*x6)*x22+(-0.0+-0.0*x6)*x23+(-0.0+-0.0*x6)*x26+(-0.0+-0.0*x6)*x27+(-0.0+-0.0*x6)*x29+(-0.0+-0.0*x6)*x30+(-0.0+-0.0*x6)*x31+(-0.0+-0.0*x7)*x3+(-0.0+-0.0*x7)*x12+(-0.0+-0.0*x7)*x30+(-0.0+-0.0*x8)*x1+(-0.0+-0.0*x8)*x3+(-0.0+-0.0*x8)*x4+(-0.0+-0.0*x8)*x11+(-0.0+-0.0*x8)*x12+(-0.0+-0.0*x8)*x13+(-0.0+-0.0*x8)*x14+(-0.0+-0.0*x8)*x18+(-0.0+-0.0*x8)*x20+(-0.0+-0.0*x8)*x22+(-0.0+-0.0*x8)*x23+(-0.0+-0.0*x8)*x26+(-0.0+-0.0*x8)*x27+(-0.0+-0.0*x8)*x29+(-0.0+-0.0*x8)*x30+(-0.0+-0.0*x8)*x31+(-0.0+-0.0*x9)*x3+(-0.0+-0.0*x9)*x12+(-0.0+-0.0*x9)*x30+(-0.0+-0.0*x10)*x3+(-0.0+-0.0*x10)*x12+(-0.0+-0.0*x10)*x30+(-0.0+-0.0*x11)*x3+(-0.0+-0.0*x11)*x5+(-0.0+-0.0*x11)*x6+(-0.0+-0.0*x11)*x8+(-0.0+-0.0*x11)*x12+(-0.0+-0.0*x11)*x30+(-0.0+-0.0*x12)*x1+(-0.0+-0.0*x12)*x2+(-0.0+-0.0*x12)*x3+(-0.0+-0.0*x12)*x4+(-0.0+-0.0*x12)*x5+(-0.0+-0.0*x12)*x6+(-0.0+-0.0*x12)*x7+(-0.0+-0.0*x12)*x8+(-0.0+-0.0*x12)*x9+(-0.0+-0.0*x12)*x10+(-0.0+-0.0*x12)*x11+(-0.0+-0.0*x12)*x13+(-0.0+-0.0*x12)*x14+(-0.0+-0.0*x12)*x15+(-0.0+-0.0*x12)*x16+(-0.0+-0.0*x12)*x17+(-0.0+-0.0*x12)*x19+(-0.0+-0.0*x12)*x21+-0.0+-0.0*x12+(-0.0+-0.0*x12)*x22+(-0.0+-0.0*x12)*x23+(-0.0+-0.0*x12)*x24+(-0.0+-0.0*x12)*x25+-0.0+-0.0*x12+(-0.0+-0.0*x12)*x26+(-0.0+-0.0*x12)*x27+(-0.0+-0.0*x12)*x28+(-0.0+-0.0*x12)*x29+(-0.0+-0.0*x13)*x3+(-0.0+-0.0*x13)*x8+(-0.0+-0.0*x13)*x12+(-0.0+-0.0*x14)*x3+(-0.0+-0.0*x14)*x5+(-0.0+-0.0*x14)*x6+(-0.0+-0.0*x14)*x8+(-0.0+-0.0*x14)*x12+(-0.0+-0.0*x14)*x30+(-0.0+-0.0*x15)*x3+(-0.0+-0.0*x15)*x12+(-0.0+-0.0*x15)*x30+(-0.0+-0.0*x16)*x3+(-0.0+-0.0*x16)*x12+(-0.0+-0.0*x17)*x3+(-0.0+-0.0*x17)*x12+(-0.0+-0.0*x17)*x20+(-0.0+-0.0*x17)*x25+-0.0+-0.0*x17+(-0.0+-0.0*x17)*x30+(-0.0+-0.0*x18)*x3+(-0.0+-0.0*x18)*x5+(-0.0+-0.0*x18)*x6+(-0.0+-0.0*x18)*x8+(-0.0+-0.0*x19)*x3+(-0.0+-0.0*x19)*x12+(-0.0+-0.0*x20)*x3+(-0.0+-0.0*x20)*x5+(-0.0+-0.0*x20)*x6+(-0.0+-0.0*x20)*x8+(-0.0+-0.0*x20)*x17+(-0.0+-0.0*x21)*x3+(-0.0+-0.0*x21)*x12+(-0.0+-0.0*x22)*x3+(-0.0+-0.0*x22)*x5+(-0.0+-0.0*x22)*x6+(-0.0+-0.0*x22)*x8+(-0.0+-0.0*x22)*x12+(-0.0+-0.0*x22)*x30+(-0.0+-0.0*x23)*x3+(-0.0+-0.0*x23)*x5+(-0.0+-0.0*x23)*x6+(-0.0+-0.0*x23)*x8+(-0.0+-0.0*x23)*x12+(-0.0+-0.0*x24)*x3+(-0.0+-0.0*x24)*x12+(-0.0+-0.0*x25)*x3+(-0.0+-0.0*x25)*x12+(-0.0+-0.0*x25)*x17+(-0.0+-0.0*x25)*x30+(-0.0+-0.0*x26)*x3+(-0.0+-0.0*x26)*x5+(-0.0+-0.0*x26)*x6+(-0.0+-0.0*x26)*x8+(-0.0+-0.0*x26)*x12+(-0.0+-0.0*x27)*x3+(-0.0+-0.0*x27)*x5+(-0.0+-0.0*x27)*x6+(-0.0+-0.0*x27)*x8+(-0.0+-0.0*x27)*x12+(-0.0+-0.0*x28)*x3+(-0.0+-0.0*x28)*x12+(-0.0+-0.0*x28)*x30+(-0.0+-0.0*x29)*x3+(-0.0+-0.0*x29)*x5+(-0.0+-0.0*x29)*x6+(-0.0+-0.0*x29)*x8+(-0.0+-0.0*x29)*x12+(-0.0+-0.0*x30)*x2+(-0.0+-0.0*x30)*x4+(-0.0+-0.0*x30)*x5+(-0.0+-0.0*x30)*x6+(-0.0+-0.0*x30)*x7+(-0.0+-0.0*x30)*x8+(-0.0+-0.0*x30)*x9+(-0.0+-0.0*x30)*x10+(-0.0+-0.0*x30)*x11+(-0.0+-0.0*x30)*x14+(-0.0+-0.0*x30)*x15+(-0.0+-0.0*x30)*x17+(-0.0+-0.0*x30)*x22+(-0.0+-0.0*x30)*x25+-0.0+-0.0*x30+(-0.0+-0.0*x30)*x28+(-0.0+-0.0*x31)*x3+(-0.0+-0.0*x31)*x5+(-0.0+-0.0*x31)*x6+(-0.0+-0.0*x31)*x8";
expression = "(-0.0+-0.0*x1)*x5+exp(x1)";


        jep.addStandardConstants();
        jep.addStandardFunctions();
        jep.addComplex();
        jep.setAllowUndeclared(true);
        jep.setAllowAssignment(true);
        jep.setImplicitMul(true);

        // Sets up standard rules for differentiating sin(x) etc.
        jep.addStandardDiffRules();


        for (int i = 0; i < 31; i++) {
            jep.addVariable("x" + (i + 1), 0);
        }


        System.out.println("exp:" + expression.length());

        jep.parseExpression(expression);

        //for (int i=0;i<2;i++) {
            Node n = jep.parse(expression);
            String s = getNodeString(n);
            System.out.println("(" + s.length() + "): " + s);
            
            n = jep.simplify(n);
            s = getNodeString(n);
            System.out.println("(" + s.length() + "): " + s);

            n = jep.simplify(n);
            s = getNodeString(n);
            System.out.println("(" + s.length() + "): " + s);

            
        //}

            Node diffed = jep.differentiate(n, "x1");

            System.out.println(getNodeString(diffed));


    }
}
