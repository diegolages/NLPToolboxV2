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


package br.ufrj.cos.nlptoolboxv2.methods;

import br.ufrj.cos.nlptoolboxv2.Function;
import br.ufrj.cos.nlptoolboxv2.Norm;
import br.ufrj.cos.nlptoolboxv2.jamaextensions.Vector;
import br.ufrj.cos.nlptoolboxv2.linesearch.LineSearch;

/**
 *
 * @author Diego
 */
public interface MinimizationMethod {

    public void setInitialPoint(double [] variables);
    
    public void setParameters(double ... p);
    
    public void setPrecision(double f);
    
    public void setMaxiterations(long i);
    
    public void setNorm(Norm n);
    
    
    public long getIterationscount();

    public void resetIterationscount();
    
    public double getErr();
    
    public double [] minimize(Function f) throws Exception;
    
    public void setLinesearch(LineSearch linesearch);
}
