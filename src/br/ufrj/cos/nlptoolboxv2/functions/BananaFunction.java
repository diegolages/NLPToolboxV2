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
public class BananaFunction implements Function {

    @Override
    public double evaluate(double [] variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        //100.0*(y - x^2)^2 + (-x + 1)^2
        // 100.0 * (y^2 -2*y*x^2 + x^4) + (x^2 -2*x + 1)
        // 100.0 * x^4 - 200 * y * x^2 + 100.0 * y^2 + x^2 - 2 * x + 1
        //// [100.0]x4 + [-200.0]x²y + [1.0]x² + [-2.0]x + [100.0]y² + [1.0]
        
        double x1 = variables[0];
        double x2 = variables[1];
        
        return 100 * (x2 - x1*x1) + Math.pow(1 - x1, 2.0);

    }

    @Override
    public double [] gradient2(double [] variables) {
        // 100 * (x2 - x1^2) + (1 - x1)^2
        // exp. 100x2 - 100x1^2 + 1 - 2x1 + x1^2
        // g(x1) -> -200x1 -2 + 2x1
        // g(x2) -> 100
        
        double x1 = variables[0];
        double x2 = variables[1];
    
        
        
        return new double[] {400.0 * Math.pow(x1, 3.0) + (2.0 - 400.0 * x2)*x1 - 2, 200.0 * (x2 - x1*x1)};
    }

    @Override
    public Matrix hessian(double [] variables) {
        
        double x1 = variables[0];
        double x2 = variables[1];
        
        // 100 * (x2 - x1^2) + (1 - x1)^2
        // exp. 100x2 - 100x1^2 + 1 - 2x1 + x1^2
        // g(x1) -> -200x1 -2 + 2x1
        // g(x2) -> 100
        // h(1,1) -> -200 + 2
        // h(1,2) -> 0
        // h(2,1) -> 0
        // h(2,2) -> 0
        
        
        return new Matrix(new double[][] {
            {-400.0*(x2 - 3.0*x1*x1) + 2.0, -400.0 * x1},
            {-400.0 * x1,                   200.0      }
        });
    }

    
    
    
}
