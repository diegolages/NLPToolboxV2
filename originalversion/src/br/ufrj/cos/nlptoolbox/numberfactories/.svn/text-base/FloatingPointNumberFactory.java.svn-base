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

package br.ufrj.cos.nlptoolbox.numberfactories;

import br.ufrj.cos.nlptoolbox.NumberFactory;
import org.jscience.mathematics.number.FloatingPoint;

/**
 *
 * @author Diego
 */
public class FloatingPointNumberFactory implements NumberFactory<FloatingPoint> {


    @Override
    public FloatingPoint makeNumber(double d) {
        return FloatingPoint.valueOf(d);
    }

    @Override
    public FloatingPoint makeNumber(long d) {
        return FloatingPoint.valueOf((double)d);
    }

    @Override
    public FloatingPoint makeComplex(double a, double b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FloatingPoint makeComplex(long a, long b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FloatingPoint makeMax() {
        return FloatingPoint.valueOf(Double.MAX_VALUE);
    }

    @Override
    public boolean checkInteger(FloatingPoint _x, FloatingPoint _precision) {
        double x = _x.doubleValue();

        if (Math.abs(x-Math.floor(x)) <= (_precision.doubleValue()/10)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int round(FloatingPoint x) {
        return (int) Math.round(x.doubleValue());
    }

}
