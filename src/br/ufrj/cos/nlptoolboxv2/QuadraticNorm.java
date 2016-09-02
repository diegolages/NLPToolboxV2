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


package br.ufrj.cos.nlptoolboxv2;

import Jama.Matrix;


/**
 *
 * @author Diego
 */
public class QuadraticNorm implements Norm {

    @Override
    public double norm(Matrix v) {
        
        if (v.getRowDimension() == 1) {
            double s = 0.0;
            
            int cols = v.getColumnDimension();
            

            for (int i=0;i<cols;i++) {
                double d = v.get(0, i);
                s += d*d;
            }
            
            return Math.sqrt(s);
        }

        throw new RuntimeException("Invalid Vector");
        
    }

    
    
}
