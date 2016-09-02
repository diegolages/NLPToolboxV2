
package br.ufrj.cos.nlptoolbox.functions;

import br.ufrj.cos.nlptoolbox.Function;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;


public class V3Stub implements Function<Float64> {


    public Float64 evaluate(Vector<Float64> variables) {
        
        Float64 _x1 = variables.get(0);
        Float64 _x2 = variables.get(1);

        double x1 = _x1.doubleValue();
        double x2 = _x1.doubleValue();


        double ret = 2*x1 + 2*x2;

        return Float64.valueOf(ret);

    }
    
    public Vector<Float64> gradient(Vector<Float64> variables) {

        Float64 _x1 = variables.get(0);
        Float64 _x2 = variables.get(1);

        double x1 = _x1.doubleValue();
        double x2 = _x1.doubleValue();


        double g_1 = 2*x1 + 2*x2;
        double g_2 = 2*x1 + 2*x2;

        
        return DenseVector.valueOf(Float64.valueOf(g_1), Float64.valueOf(g_2));
    }

    public Matrix hessian(Vector<Float64> variables) {


        Float64 _x1 = variables.get(0);
        Float64 _x2 = variables.get(1);

        double x1 = _x1.doubleValue();
        double x2 = _x1.doubleValue();


        double h_1_1 = 2*x1 + 2*x2;
        double h_1_2 = 2*x1 + 2*x2;
        double h_2_1 = 2*x1 + 2*x2;
        double h_2_2 = 2*x1 + 2*x2;

        
        return DenseMatrix.valueOf(new Float64[][] {
            {Float64.valueOf(-198), Float64.valueOf(-198)},
            {Float64.valueOf(-198), Float64.valueOf(-198)}
        });
    }

    
}
