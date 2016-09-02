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


package br.ufrj.cos.nlptoolbox.restrictions.penalty;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.restrictions.RestrictionMinimizationMethod;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PenaltyMinimizationMethod<F extends Field<F>> extends RestrictionMinimizationMethod {
    
    private F cfactor=null;
    private F initialc = null;
    
    Comparator comparator = new Comparator();
    
    public PenaltyMinimizationMethod(MinimizationMethod<F> mm, Function<F>[] inequalityrestrictions, Function<F>[] equalityrestrictions) {
        super(mm,inequalityrestrictions, equalityrestrictions);
    }

    public F getCfactor() {
        return cfactor;
    }

    public void setCfactor(F c) {
        this.cfactor = c;
    }

    public F getInitialc() {
        return initialc;
    }

    public void setInitialc(F initialc) {
        this.initialc = initialc;
    }

    @Override
    public Vector minimize(Function f) throws Exception {
        
        long nits = 0;
        
        F c = initialc;
        
        Vector ret = null;
        
        do {
            
            //err = norm.norm(xk.minus(oldxk));
            
            ret = minimizationmethod.minimize(new PenaltyCompositeFunction(f,getInequalityrestrictions(), c, numberfactory));
            
            c = c.times(cfactor);
            
            nits++;
        } while (/*comparator.compare(getErr(), precision) > 0 && */nits < maxiterations);
        
        return ret;
    }

    
}
