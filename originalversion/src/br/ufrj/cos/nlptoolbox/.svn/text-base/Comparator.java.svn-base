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


package br.ufrj.cos.nlptoolbox;

import org.jscience.mathematics.number.Number;
import org.jscience.mathematics.number.Real;
import org.jscience.mathematics.structure.Field;

//teste subversion

/**
 *
 * @author Diego
 */
public class Comparator<F extends Field<F>> {

    public int compare(F left, F right) {
        if ((left instanceof Number) && (right instanceof Number)) {
            Number nl = (Number)left;
            Number nr = (Number)right;
            if (nl.doubleValue() == nr.doubleValue()) {
                return 0;
            } else if (nl.doubleValue() > nr.doubleValue()) {
                return 1;
            } else {
                return -1;
            }
            //return ((Number) left).isLargerThan((Number) right) ? 1 : -1;
        }
        if (left.equals(left.plus(left))) // Zero
            return -1;
        if (right.equals(right.plus(right))) // Zero
            return 1;
        return 0;
    }
}
