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

package br.ufrj.cos.nlptoolbox.linesearch;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.LinearFunction;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;

/**
 *
 * @author Diego
 */
public class Wolfe<F extends Field<F>> implements LineSearch<F> {

    Comparator<F> comparator = new Comparator<F>();
    private long maxiterations;
    private F precision;
    NumberFactory<F> numberfactory = null;

    @Override
    public F minimize(LinearFunction<F> f, F initialx) throws Exception {

        long j = 0;

        F xn = initialx;

        F err = null;

        F zero = numberfactory.makeNumber(0);
        F one = numberfactory.makeNumber(1);
        F two = numberfactory.makeNumber(2);
        
        F tmin = zero;
        F tmax = numberfactory.makeMax();
        
        F m1 = precision;
        F m2 = one.plus(precision.opposite());
        
        do {

            //xn = xn.plus( f.evaluate(xn).times(  f.derivative(xn).inverse() ).opposite()  );

            if (comparator.compare(f.evaluate(xn),m1.times(f.derivative(zero)).times(xn)) <= 0) {

                if (comparator.compare(f.derivative(xn),m2.times(f.derivative(zero))) >= 0) {

//                    System.out.println("tmax = " + tmax);
//                    System.out.println("tmin = " + tmin);
//                    System.out.println("t = " + t);

                    return (tmax.plus(tmin.opposite()).times(two.inverse()));

                }

                tmin = xn;

            } else {
                tmax = xn;

                //step3:
                xn = (tmax.plus(tmin.opposite()).times(two.inverse()));

                tmin = xn;

            }



            err = f.evaluate(xn);

            err = err.times(err);
            j++;
        } while (j < getMaxiterations());

        return xn;
    }

    public long getMaxiterations() {
        return maxiterations;
    }

    public void setMaxiterations(long maxiterations) {
        this.maxiterations = maxiterations;
    }

    public F getPrecision() {
        return precision;
    }

    public void setPrecision(F precision) {
        this.precision = precision;
    }

    public static void main(String argv[]) throws Exception {

        LinearFunction<Float64> lf = new LinearFunction<Float64>() {

            @Override
            public Float64 evaluate(Float64 _x) {
                //f(x) = cos(x) − x3

                double x = _x.doubleValue();

                return Float64.valueOf(Math.cos(x) - Math.pow(x, 3));

            }

            @Override
            public Float64 derivative(Float64 _x) {
                //f'(x) = −sin(x) − 3x2

                double x = _x.doubleValue();

                return Float64.valueOf(-Math.sin(x) - 3 * Math.pow(x, 2));
            }
        };

        NewtonRaphson nr = new NewtonRaphson();
        nr.setMaxiterations(10000000);
        nr.setPrecision(Float64.valueOf(0.00000001));

        System.out.println(nr.minimize(lf, Float64.valueOf(0.5)));

    }

    public NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }
}
