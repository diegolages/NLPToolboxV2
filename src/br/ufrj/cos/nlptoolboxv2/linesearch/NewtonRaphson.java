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
public class NewtonRaphson implements LineSearch {

    Comparator comparator = new Comparator();
    private long maxiterations;
    private double precision;
    
    @Override
    public double minimize(LinearFunction f, double initialx ) throws Exception {
        
        long j = 0;
        
        double xn = initialx;
        
        double err;
        
        do {

            xn = xn + ( f.evaluate(xn) * (  -1.0 / f.derivative(xn) ) );
            
            err = f.evaluate(xn);
            
            err = err * err;
            j++;
        } while (comparator.compare(err,getPrecision()) > 0 && j < getMaxiterations());
        
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
