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

import Jama.Matrix;
import br.ufrj.cos.nlptoolboxv2.Function;

/**
 *
 * @author Diego
 */
public class ConstFunction extends ExpressionFunction {

    int dimension=-1;

    double [] grad = null;
    Matrix hess = null;
    double value;

    public ConstFunction(double v, int _nvariables) throws Exception {
        value = v;
        dimension = _nvariables;
        grad = new double[_nvariables];
        for (int i=0;i<_nvariables;i++) {
            grad[i] = 0;
        }
        double [][] A = new double[_nvariables][_nvariables];
        for (int i=0;i<_nvariables;i++) {
            for (int j=0;j<_nvariables;j++) {
                A[i][j] = 0;
            }
        }
        hess = new Matrix(A);
    }

    @Override
    public String toString() {
        return "" + value;
    }


    @Override
    public double evaluate(double [] variables) throws Exception {
        return value;
    }

    @Override
    public double [] gradient2(double [] variables) throws Exception {
       return grad;
    }

    @Override
    public Matrix hessian(double [] variables) throws Exception {
        return hess;
    }
    
}
