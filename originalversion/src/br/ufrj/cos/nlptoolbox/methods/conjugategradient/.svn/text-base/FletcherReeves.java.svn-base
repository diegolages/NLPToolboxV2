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


package br.ufrj.cos.nlptoolbox.methods.conjugategradient;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import br.ufrj.cos.nlptoolbox.methods.conjugategradient.ConjugateGradient;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class FletcherReeves<F extends Field<F>> extends ConjugateGradient<F> implements MinimizationMethod<F> {

    protected F getBeta(Vector<F> deltaxn, Vector<F> deltaxn_1,Vector<F> lambdaxn_1,Function<F> function, Vector<F> xn) {
    
        // fletcher-reeves
        
        return deltaxn.times(deltaxn).times(  deltaxn_1.times(deltaxn_1).inverse()    );
        
    }

}
