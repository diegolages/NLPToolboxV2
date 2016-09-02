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
import org.jscience.mathematics.number.Real;

/**
 *
 * @author Diego
 */
public class RealNumberFactory implements NumberFactory<Real> {

    @Override
    public Real makeNumber(double d) {
        return Real.valueOf(d);
    }

    @Override
    public Real makeNumber(long d) {
        return Real.valueOf(d);
    }

    @Override
    public Real makeComplex(double a, double b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Real makeComplex(long a, long b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Real makeMax() {
        return Real.valueOf(Double.MAX_VALUE);
    }

    @Override
    public boolean checkInteger(Real _x, Real _precision) {
        double x = _x.doubleValue();

        if (Math.abs(x-Math.floor(x)) <= (_precision.doubleValue()/10)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int round(Real x) {
        return (int) Math.round(x.doubleValue());
    }

}
