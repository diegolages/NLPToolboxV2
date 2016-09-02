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
import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;

/**
 *
 * @author Diego
 */
public class ComplexNumberFactory implements NumberFactory<Complex> {


    @Override
    public Complex makeNumber(double d) {
        return Complex.valueOf(d,0);
    }

    @Override
    public Complex makeNumber(long d) {
        return Complex.valueOf(d,0);
    }

    @Override
    public Complex makeComplex(double a, double b) {
        return Complex.valueOf(a, b);
    }

    @Override
    public Complex makeComplex(long a, long b) {
        return Complex.valueOf(a, b);
    }

    @Override
    public Complex makeMax() {
        return Complex.valueOf(Double.MAX_VALUE,0);
    }

    @Override
    public boolean checkInteger(Complex _x, Complex _precision) {
        double x = _x.doubleValue();

        if (Math.abs(x-Math.floor(x)) <= (_precision.doubleValue()/10)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int round(Complex x) {
        return (int) Math.round(x.doubleValue());
    }

}
