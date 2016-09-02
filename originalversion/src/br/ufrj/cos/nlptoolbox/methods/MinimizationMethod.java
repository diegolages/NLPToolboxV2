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


package br.ufrj.cos.nlptoolbox.methods;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public interface MinimizationMethod<F extends Field<F>> {

    public void setInitialPoint(Vector<F> variables);
    
    public void setParameters(F ... p);
    
    public void setPrecision(F f);
    
    public void setMaxiterations(long i);
    
    public void setNorm(Norm<F> n);
    
    public void setNumberfactory(NumberFactory<F> numberfactory);
    
    public long getIterationscount();

    public void resetIterationscount();
    
    public F getErr();
    
    public Vector<F> minimize(Function<F> f) throws Exception;
    
    public void setLinesearch(LineSearch linesearch);
}
