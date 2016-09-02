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
public class Armijo implements LineSearch {

    Comparator    comparator = new Comparator();
    private long  maxiterations;
    private double precision;
    
    @Override
    public double minimize(LinearFunction f, double initialx ) throws Exception {
        
        long j = 0;
        
        double N = 1.1;
        
        double xn = initialx;
        
        double err = 0.0;
        
        double zero = 0;
        double two = 2;
        
        double tmin = zero;
        double tmax = Double.MAX_VALUE;
        
      //  do {

            
            
        if (f.evaluate(xn) <= f.evaluate(0.0) + precision * f.derivative(0) * xn) {

            while (f.evaluate(xn) <= f.evaluate(0.0) + precision * f.derivative(0) * xn) {
                
                tmin = xn;
                xn = N * xn;

            }

            tmax = xn;
            xn = xn * (1.0/N);



        } else {

            if (comparator.compare(xn,tmax) > 0) {

                xn = tmax;

            } else {

                while (f.evaluate(xn) > f.evaluate(0) + precision * f.derivative(0) * xn) {
                
                    xn = xn*(1.0/N);
                    tmax = xn;

                }

            }

        }
        return (tmax + tmin)*(1.0/2.0);
            
            
            
            //xn = xn.plus( f.evaluate(xn).times(  f.derivative(xn).inverse() ).opposite()  );
            
     //       err = f.evaluate(xn);
     //       
     //       err = err.times(err);
     //       j++;
     //   } while (comparator.compare(err,getPrecision()) > 0 && j < getMaxiterations());
        
       // return xn;
    }

    public long getMaxiterations() {
        return maxiterations;
    }

    public void setMaxiterations(long maxiterations) {
        this.maxiterations = maxiterations;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }
    
//    public static void main(String argv[]) throws Exception {
//        Armijo a = new Armijo();
//        
//        Function f = new Function() {
//            public double derivative(double v) throws Exception {
//                    return 4 * Math.pow(v,3) - 3 * v * v;
//            }
//
//            public double evaluate(double x) throws Exception {
//                    return Math.pow(x,4) - Math.pow(x,3) + 1;
//            }
//	
//        };
//		
//	System.out.println("minimo: " + a.findMinimum(f, 0.00002, 1.1, 1));
//
//    }
//    
//    public static void main(String argv[]) throws Exception {
//        
//        LinearFunction<Float64> lf = new LinearFunction<Float64>() {
//
//            @Override
//            public Float64 evaluate(Float64 _x) {
//                //f(x) = cos(x) − x3
//                
//                double x = _x.doubleValue();
//                
//                return Float64.valueOf(Math.cos(x) - Math.pow(x, 3));
//                
//            }
//
//            @Override
//            public Float64 derivative(Float64 _x) {
//                //f'(x) = −sin(x) − 3x2
//                
//                double x = _x.doubleValue();
//                
//                return Float64.valueOf(-Math.sin(x) - 3 * Math.pow(x, 2));
//            }
//            
//        };
//        
//        Armijo nr = new Armijo();
//        nr.setNumberfactory(new Float64NumberFactory());
//        nr.setMaxiterations(10000000);
//        nr.setPrecision(Float64.valueOf(0.00000001));
//        
//        System.out.println(nr.minimize(lf, Float64.valueOf(0.8)));
//        
//    }
    
    public double findMinimum(Function p, double e, double n, double t) throws Exception {

        if (e < 0 || e > 1.0) {

            throw new Exception("E deve ser entre 0 e 1");

        }

        if (n < 1) {

            throw new Exception("N deve ser maior que 1");

        }

        double tmin = 0;
        double tmax = Double.MAX_VALUE;

        if (p.evaluate(t) <= p.evaluate(0) + e * p.derivative(0) * t) {

            while (p.evaluate(t) <= p.evaluate(0) + e * p.derivative(0) * t) {

                tmin = t;
                t = n * t;

            }

            tmax = t;
            t = t / n;



        } else {

            if (t > tmax) {

                t = tmax;

            } else {

                while (p.evaluate(t) > p.evaluate(0) + e * p.derivative(0) * t) {

                    t = t / n;
                    tmax = t;

                }

            }

        }

        return (tmax + tmin) / 2.0;

    }
    
    
}
