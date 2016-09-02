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
import org.jscience.mathematics.number.Float64;

/**
 *
 * @author Diego
 */
public class Float64NumberFactory implements NumberFactory<Float64> {


    @Override
    public Float64 makeNumber(double d) {
        return Float64.valueOf(d);
    }

    @Override
    public Float64 makeNumber(long d) {
        return Float64.valueOf(d);
    }

    @Override
    public Float64 makeComplex(double a, double b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Float64 makeComplex(long a, long b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Float64 makeMax() {
        return Float64.valueOf(Double.MAX_VALUE);
    }

    @Override
    public boolean checkInteger(Float64 _x, Float64 _precision) {
        double x = _x.doubleValue();

        if (Math.abs(x-Math.round(x)) <= (_precision.doubleValue()*100.0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int round(Float64 x) {
        return (int) Math.round(x.doubleValue());
    }

}
