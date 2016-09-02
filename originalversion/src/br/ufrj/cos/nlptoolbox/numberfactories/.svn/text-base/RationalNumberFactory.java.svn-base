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
import org.jscience.mathematics.number.Rational;

/**
 *
 * @author Diego
 */
public class RationalNumberFactory implements NumberFactory<Rational> {

    @Override
    public Rational makeNumber(double d) {
       // return Rational.valueOf(Double.toString(d));
        //double truncpart = Math.floor(d);
        // TODO: FIX
        
        if (d == 0.0) {
            return Rational.ZERO;
        }

        String s = Double.toString(d);

        if (s.contains("E")) {
            String se[] = s.split("E");
            long exp = Long.parseLong(se[1]);
            if (exp < 0) {
                return makeNumber(Double.parseDouble(se[0])).times(Rational.valueOf(1, (long)(Math.pow(10, Math.abs(exp)))));
            } else {
                return makeNumber(Double.parseDouble(se[0])).times(Rational.valueOf((long)(Math.pow(10, exp)),1));
            }
        }

        String ss[] = s.split("\\.");
        int l = ss[1].length();
        
        Rational r =  Rational.valueOf((long)(d*Math.pow(10, l)),(long)Math.pow(10, l));
        return r;
    }

    @Override
    public Rational makeNumber(long d) {
        return Rational.valueOf(d, 1);
    }

    @Override
    public Rational makeComplex(double a, double b) {
        return null;
    }

    @Override
    public Rational makeComplex(long a, long b) {
        return null;
    }

    @Override
    public Rational makeMax() {
        return Rational.valueOf(Long.MAX_VALUE, 1);
    }

    @Override
    public boolean checkInteger(Rational _x, Rational _precision) {
        double x = _x.doubleValue();

        if (Math.abs(x-Math.floor(x)) <= (_precision.doubleValue()/10)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int round(Rational x) {
        return (int) Math.round(x.doubleValue());
    }

}
