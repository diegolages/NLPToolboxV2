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

package br.ufrj.cos.nlptoolboxv2.linesearch;

import br.ufrj.cos.nlptoolboxv2.Comparator;
import br.ufrj.cos.nlptoolboxv2.LinearFunction;

/**
 *
 * @author Diego
 */
public class Wolfe implements LineSearch {

    Comparator comparator = new Comparator();
    private long maxiterations;
    private double precision;

    @Override
    public double minimize(LinearFunction f, double initialx) throws Exception {

        long j = 0;

        double xn = initialx;

        double err = 0.0;

        double zero = 0.0;
        double one = 1.0;
        double two = 2.0;
        
        double tmin = zero;
        double tmax = Double.MAX_VALUE;
        
        double m1 = precision;
        double m2 = one-precision;
        
        do {

            //xn = xn.plus( f.evaluate(xn).times(  f.derivative(xn).inverse() ).opposite()  );

            if (comparator.compare(f.evaluate(xn),m1*(f.derivative(zero))*(xn)) <= 0) {

                if (comparator.compare(f.derivative(xn),m2*(f.derivative(zero))) >= 0) {

//                    System.out.println("tmax = " + tmax);
//                    System.out.println("tmin = " + tmin);
//                    System.out.println("t = " + t);

                    return (tmax-tmin)*(1.0/2.0);

                }

                tmin = xn;

            } else {
                tmax = xn;

                //step3:
                xn = (tmax-tmin)*(1.0/2.0);

                tmin = xn;

            }



            err = f.evaluate(xn);

            err = err*(err);
            j++;
        } while (j < getMaxiterations());

        return xn;
    }

    @Override
    public long getMaxiterations() {
        return maxiterations;
    }

    @Override
    public void setMaxiterations(long maxiterations) {
        this.maxiterations = maxiterations;
    }

    @Override
    public double getPrecision() {
        return precision;
    }

    @Override
    public void setPrecision(double precision) {
        this.precision = precision;
    }

}
