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

import br.ufrj.cos.nlptoolbox.LinearFunction;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import org.jscience.mathematics.structure.Field;

/**
 *
 * @author Diego
 */
public interface LineSearch<F extends Field<F>> {

    public F minimize(LinearFunction<F> f,F initialx) throws Exception ;
    
    public long getMaxiterations();

    public void setMaxiterations(long maxiterations);

    public F getPrecision();

    public void setPrecision(F precision);
    
    public NumberFactory<F> getNumberfactory();

    public void setNumberfactory(NumberFactory<F> numberfactory);
    
}
