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
import java.util.ArrayList;
import java.util.HashMap;
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
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author Diego
 */
public class ExpressionFunctionV3<F extends Field<F>> implements NumberFactoryableFunction<F> {

    String expression = "";
    int nvariables = 0;
    DJep jep = new DJep();
    Node[] gradientexpressions = null;
    Node[][] hessianexpressions = null;
    Function stub = null;

    protected ExpressionFunctionV3(String exp) throws Exception {
        this(exp, 0);
    }

    protected ExpressionFunctionV3(String exp, int _nvariables) throws Exception {
        this(exp, _nvariables, new HashMap());
    }

    DJep cloneJEP() throws ParseException {
        DJep ret = new DJep();

        ret.addStandardConstants();
        ret.addStandardFunctions();
        ret.addComplex();
        ret.setAllowUndeclared(true);
        ret.setAllowAssignment(true);
        ret.setImplicitMul(true);

        // Sets up standard rules for differentiating sin(x) etc.
        ret.addStandardDiffRules();


        for (int i = 0; i < nvariables; i++) {
            jep.addVariable("x" + (i + 1), 0);
        }

        ret.parseExpression(expression);

        ret.simplify(ret.parse(expression));

        return ret;
    }



    protected ExpressionFunctionV3(String exp, int _nvariables, HashMap<String, Double> p) throws Exception {

        File tempfile = File.createTempFile("jep", ".java");



        String javasource = "\n" +
                // "package br.ufrj.cos.nlptoolbox.functions;\n" +
                "\n" +
                "import br.ufrj.cos.nlptoolbox.Function;\n" +
                "import org.jscience.mathematics.number.Float64;\n" +
                "import org.jscience.mathematics.vector.DenseMatrix;\n" +
                "import org.jscience.mathematics.vector.DenseVector;\n" +
                "import org.jscience.mathematics.vector.Matrix;\n" +
                "import org.jscience.mathematics.vector.Vector;\n" +
                "\n" +
                "\n" +
                "public class " + tempfile.getName().replace(".java", "") + " implements Function<Float64>,br.ufrj.cos.nlptoolbox.functions.MatrixHolder {\n";


        javasource = javasource + " private double exp(double d) { return Math.exp(d); } \n";
        javasource = javasource + " private double sqrt(double d) { return Math.sqrt(d); } \n";

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

        for (int i = 0; i < nvariables; i++) {
            jep.addVariable("x" + (i + 1), 0);
        }

        jep.parseExpression(expression);

        String exp_str = getNodeString(jep.simplify(jep.parse(expression)));

        javasource = javasource + " public Float64 evaluate(Vector<Float64> variables) {\n";

        for (int i = 0; i < nvariables; i++) {

            javasource = javasource + "Float64 _x" + (i + 1) + " = variables.get(" + i + ");\n";

            javasource = javasource + " double x" + (i + 1) + " = _x" + (i + 1) + ".doubleValue();\n";

        //jep.addVariable("x" + (i + 1), 0);
        }

        //
//        Float64 _x1 = variables.get(0);
//        Float64 _x2 = variables.get(1);
//
//        double x1 = _x1.doubleValue();
//        double x2 = _x1.doubleValue();
//
//
//        double ret = 2*x1 + 2*x2;
//
//        return Float64.valueOf(ret);

        javasource = javasource + " double ret = " + exp_str + ";\n";

        javasource = javasource + " return Float64.valueOf(ret);\n";

        javasource = javasource + " }\n";


        //gradientexpressions = new Node[nvariables];

        javasource = javasource + " public Vector<Float64> gradient(Vector<Float64> variables) {\n";

        for (int i = 0; i < nvariables; i++) {

            javasource = javasource + "Float64 _x" + (i + 1) + " = variables.get(" + i + ");\n";

            javasource = javasource + " double x" + (i + 1) + " = _x" + (i + 1) + ".doubleValue();\n";

        //jep.addVariable("x" + (i + 1), 0);
        }

        List<String> gexps = new java.util.Vector();

        for (int i = 0; i < nvariables; i++) {

            String varname = "x" + (i + 1);

            Node gnode = jep.differentiate(jep.getTopNode(), varname);
            //for (int k=0;k<nvariables;k++) {
            gnode = jep.simplify(gnode);
            //}

            javasource = javasource + "Float64 F_g_" + (i + 1) + " = Float64.valueOf(" + getNodeString(gnode) + ");\n";

            gexps.add("F_g_" + (i + 1));
        /*
         *
         *  public Vector<Float64> gradient(Vector<Float64> variables) {

        Float64 _x1 = variables.get(0);
        Float64 _x2 = variables.get(1);

        double x1 = _x1.doubleValue();
        double x2 = _x1.doubleValue();


        double g_1 = 2*x1 + 2*x2;
        double g_2 = 2*x1 + 2*x2;


        return DenseVector.valueOf(Float64.valueOf(g_1), Float64.valueOf(g_2));
        }*/
        //gradientexpressions[i] = gnode;

        }


        javasource = javasource + " return DenseVector.valueOf(" + StringUtil.join(gexps, ",") + ");\n ";

        javasource = javasource + " }\n ";


        String hessiansource = "";

        String hessianmatrixsource = "";

        String hessianvariablesource = "";

        //  javasource = javasource + " public Vector<Float64> gradient(Vector<Float64> variables) {  return null;  }\n";
        hessiansource = hessiansource + " public Matrix hessian(Vector<Float64> variables) { \n";

        for (int i = 0; i < nvariables; i++) {

            hessianvariablesource = hessianvariablesource + "Float64 _x" + (i + 1) + " = variables.get(" + i + ");\n";

            hessianvariablesource = hessianvariablesource + " double x" + (i + 1) + " = _x" + (i + 1) + ".doubleValue();\n";

        //jep.addVariable("x" + (i + 1), 0);
        }


        List<String> hexps = new java.util.Vector();




        //hessianexpressions = new Node[nvariables][nvariables];


        Float64[][] cachedhessian = new Float64[nvariables][nvariables];


        for (int i = 0; i < nvariables; i++) {

            final int i2 = i;

            final String varname_i = "x" + (i + 1);
            final Node gnode = jep.differentiate(jep.getTopNode(), varname_i);

            final java.util.Vector<String> gexps2 = new java.util.Vector();

            int availprocessors = Runtime.getRuntime().availableProcessors();

            System.out.println("processing hessian(i) = (" + i + ")");


            //Parallelizer parallelizer = new Parallelizer(1);

            final StringBuffer sb_hessianmatrixsource = new StringBuffer();

            final ExceptionHolder eh = new ExceptionHolder();

            for (int j = 0; j < nvariables; j++) {

                final int j2 = j;

//                parallelizer.run(new Runnable() {
//
//                    @Override
//                    public void run() {

                        


                        try {

                            //DJep clonedjep = cloneJEP();

                            final String varname_j = "x" + (j2 + 1);

                           // Node gnode = jep.differentiate(jep.getTopNode(), varname_i);

                            Node hnode = jep.differentiate(gnode, varname_j);
                            // for (int k=0;k<nvariables;k++) {
                            hnode = jep.simplify(hnode);
                            // }

//                        hessianmatrixsource = hessianmatrixsource + "Float64 F_h_" + (i+1) + "_" + (j+1) + " = Float64.valueOf(" + getNodeString(hnode) + ");\n";
//
//                        gexps2.add("F_h_" + (i+1) + "_" + (j+1));

                            String nodestring = getNodeString(hnode);

                            sb_hessianmatrixsource.append("Float64 F_h_" + (i2 + 1) + "_" + (j2 + 1) + " = Float64.valueOf(" + nodestring + ");\n");

                            if (!nodestring.contains("x")) {
                                cachedhessian[i][j] = Float64.valueOf(Double.parseDouble(getNodeString(hnode)));
                            }

                            gexps2.add("F_h_" + (i2 + 1) + "_" + (j2 + 1));

                        } catch (Exception ex) {
                            eh.setException(ex);
                        }
                    //}
                //});



            }

           // parallelizer.join();

            hessianmatrixsource = hessianmatrixsource + sb_hessianmatrixsource.toString();

            if (eh.getException() != null) {
                throw eh.getException();
            }

            hexps.add("{" + StringUtil.join(gexps2, ",") + "}\n");

        }

        String hjoined = StringUtil.join(hexps, ",\n");

        boolean hasx = false;

        if (hessianmatrixsource.contains("x")) {
            hessiansource = hessiansource + "\n" + hessianvariablesource;
            hessiansource = hessiansource + "\n" + hessianmatrixsource;

            hessiansource = hessiansource + " return DenseMatrix.valueOf(new Float64[][] {\n" + hjoined + "});\n ";

            hessiansource = hessiansource + " }\n ";

            hessiansource = hessiansource + " public void initCachedhessian(Matrix m) { \n";

            //hessiansource = hessiansource + "\n" + hessianmatrixsource;

            hessiansource = hessiansource + " cachedhessian =  m;\n ";

            hessiansource = hessiansource + " } \n";

            hessiansource = hessiansource + " Matrix cachedhessian = null; \n";

            hasx = true;
        } else {

//            hessiansource = hessiansource + " if (cachedhessian == null) { initCachedhessian(); } ;\n ";

            hessiansource = hessiansource + " return cachedhessian; ";

            hessiansource = hessiansource + " }\n ";

            hessiansource = hessiansource + " public void initCachedhessian(Matrix m) { \n";

            //hessiansource = hessiansource + "\n" + hessianmatrixsource;

            hessiansource = hessiansource + " cachedhessian =  m;\n ";

            hessiansource = hessiansource + " } \n";

            hessiansource = hessiansource + " Matrix cachedhessian = null; \n";

        }


        javasource = javasource + hessiansource + "\n";



        javasource = javasource + " }\n";


        OutputStream os = new BufferedOutputStream(new FileOutputStream(tempfile));
        os.write(javasource.getBytes());
        os.close();

        System.out.println("written to: " + tempfile.getAbsolutePath());




        File[] files1 = new File[1];
        // input for first compilation task
        files1[0] = tempfile;

        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector<JavaFileObject> dl =
                new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager manager =
                jc.getStandardFileManager(dl, null, null);

        Iterable<? extends JavaFileObject> jFiles =
                manager.getJavaFileObjects(files1);

        File f1 = new File("/tmp/NLPToolbox.jar");
        if (!f1.exists()) {
            throw new Exception("/tmp/NLPToolbox.jar does not exist");
        }

        File f2 = new File("/tmp/javolution.jar");
        if (!f2.exists()) {
            throw new Exception("/tmp/javolution.jar does not exist");
        }

        ArrayList compileroptions = new ArrayList();
        compileroptions.add("-classpath");
        compileroptions.add("/tmp/NLPToolbox.jar:/tmp/javolution.jar");
        //compileroptions.add("file:///home/lages/cvs/PNLToolbox/dist/NLPToolbox.jar");
//        compileroptions.add("-classpath");
//        compileroptions.add("/home/lages/cvs/gestor/lib/javolution.jar");

        Callable<Boolean> task =
                jc.getTask(null, manager, null, compileroptions, null, jFiles);
        task.call();

        String classfilename = tempfile.getAbsolutePath().replace(".java", ".class");

        if (!new File(classfilename).exists()) {
            throw new Exception("Compilation failed");
        }

        File file = new File("/tmp/");
        URL url = file.toURL();
        URL[] urls = new URL[]{
            url
        };
        final ClassLoader cl = new URLClassLoader(urls,this.getClass().getClassLoader());

        final String dynamicclassname = tempfile.getName().replace(".java", "");

        ClassLoader interceptedclassloader = new ClassLoader(this.getClass().getClassLoader()) {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if (name.equals(dynamicclassname)) {
                    return cl.loadClass(name);
                } else {
                    return super.loadClass(name);
                }
            }

        };

        boolean deletefile = true;

        try {
            Class cls = cl.loadClass(dynamicclassname);

            stub = (Function<Float64>) cls.newInstance();

            for (Diagnostic<? extends JavaFileObject> dg : dl.getDiagnostics()) {
                System.out.printf("Message: %s%n", dg.getMessage(null));
            }

            MatrixHolder mh = (MatrixHolder)stub;

            if (!hasx) {
                mh.initCachedhessian(DenseMatrix.valueOf(cachedhessian));
            }

        } catch (ClassNotFoundException cnfe) {
            deletefile = false;
            throw new ErrorInstatiatingException(cnfe);
        } finally {
            if (deletefile) {
                tempfile.delete();
            }
        }
    }

    String getNodeString(Node hnode) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        jep.print(hnode, ps);
        String content = baos.toString();

        content = content.replace("--", "+");
        //content = content.replace("+-", "-");
        //content = content.replace("+-", "-");

        return content;
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
    public F evaluate(Vector variables) throws Exception {

        return (F) stub.evaluate(variables);

    }

    @Override
    public Vector<F> gradient(Vector variables) throws Exception {
        return (Vector<F>) stub.gradient(variables);
    }

    @Override
    public Matrix hessian(Vector variables) throws Exception {
        return stub.hessian(variables);
    }
}
