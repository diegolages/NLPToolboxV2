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

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.restrictions.penalty.PenaltyFunction;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class PenaltyCompositeFunction<F extends Field<F>> implements Function<F> {

    Function<F>       function=null;
    Function<F>[] functions=null;
    PenaltyFunction<F>   penaltyfunction=null;
    F                 c=null;
    
    public PenaltyCompositeFunction(Function<F> f, Function<F>[] fs,F _c,NumberFactory<F> nf) {
        function = f;
        functions = fs;
        penaltyfunction = new PenaltyFunction<F>(fs, nf);
        c = _c;
    }
    
    @Override
    public F evaluate(Vector<F> variables) throws Exception {
        
        return function.evaluate(variables).plus(penaltyfunction.evaluate(variables).times(c));
        
    }

    @Override
    public Vector<F> gradient(Vector<F> variables) throws Exception {
        return function.gradient(variables).plus(penaltyfunction.gradient(variables).times(c));
    }

    @Override
    public Matrix hessian(Vector<F> variables) throws Exception {
        return function.hessian(variables).plus(penaltyfunction.hessian(variables).times(c));
    }

}
