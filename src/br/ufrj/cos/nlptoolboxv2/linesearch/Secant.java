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
public class Secant implements LineSearch {

    Comparator    comparator = new Comparator();
    private long maxiterations;
    private double precision;
    
    @Override
    public double minimize(LinearFunction f, double initialx ) throws Exception {
        
        long j = 0;
        
        double xn = initialx;
        double xn_1 = initialx*2.0;
        
        
        double err = 0.0;
        
        do {

           // xn = xn.plus( f.evaluate(xn).times(  f.derivative(xn).inverse() ).opposite()  );
            
            double part = xn+(xn_1*(-1.0))*(f.evaluate(xn)+(f.evaluate(xn_1)*(1.0)));

            xn_1 = xn;

            xn = xn+(part*(f.evaluate(xn))*(-1.0));
            
            err = f.evaluate(xn);
            //err = f.evaluate(xn).plus(f.evaluate(xn_1).opposite());
            
            err = err*(err);
            j++;
        } while (comparator.compare(err,getPrecision()) > 0 && j < getMaxiterations());
        
        return xn;
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
    
}
