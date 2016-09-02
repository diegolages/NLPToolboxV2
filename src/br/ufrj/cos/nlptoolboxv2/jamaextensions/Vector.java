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
package br.ufrj.cos.nlptoolboxv2.jamaextensions;

import Jama.Matrix;

/**
 *
 * @author zcp7
 */
public class Vector extends Matrix {

    
    
    public Vector(double[][] doubles) {
        super(doubles);
    }
    
    public static Vector makeVector(double ... d) {
        return new Vector(d);
    }
    
    public Vector(double ... d) { // TODO: check row, column
        double [][] _A = new double [1][d.length];
        _A[0] = d;
        
        int _m = _A.length;
        int _n = _A[0].length;
        
        setM(_m);
        setN(_n);
        for (int i = 0; i < _m; i++) {
           if (_A[i].length != _n) {
              throw new IllegalArgumentException("All rows must have the same length.");
           }
        }
        setA(_A);
    }

    
}
