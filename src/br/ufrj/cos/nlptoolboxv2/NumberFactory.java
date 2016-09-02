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

import org.jscience.mathematics.structure.Field;

/**
 *
 * @author Diego
 */
public interface NumberFactory<F extends Field<F>> {

    F makeNumber(double d);
    
    F makeNumber(long d);
    
    F makeComplex(double a,double b);
    
    F makeComplex(long a, long b);
    
    F makeMax();

    boolean checkInteger(F x,F precision);

    int round(F x);
    
}
