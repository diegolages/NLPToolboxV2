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


package br.ufrj.cos.nlptoolbox.restrictions;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import java.util.List;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public abstract class RestrictionMinimizationMethod<F extends Field<F>> implements MinimizationMethod<F> {

    protected Vector<F>          initialpoint = null;
    protected F[]                parameters = null;
    protected F                  precision = null;
    protected long               maxiterations = -1;
    protected Norm<F>            norm = null;
    protected NumberFactory<F>   numberfactory = null;
    
    protected MinimizationMethod<F> minimizationmethod = null;
    
    private Function<F>[] inequalityrestrictions=null;
    private Function<F>[] equalityrestrictions=null;
    
    
   protected RestrictionMinimizationMethod() {}
    
  //  private RestrictionMinimizationMethod() {}
    
    public RestrictionMinimizationMethod(MinimizationMethod<F> mm, Function<F>[] _inequalityrestrictions, Function<F>[] _equalityrestrictions) {
        this.minimizationmethod = mm;
        inequalityrestrictions = _inequalityrestrictions;
        equalityrestrictions = _equalityrestrictions;
    }
    
    @Override
    public void setInitialPoint(Vector<F> variables) {
        initialpoint = variables;
        minimizationmethod.setInitialPoint(variables);
    }

    @Override
    public void setParameters(F... p) {
        parameters = p;
        minimizationmethod.setParameters(p);
    }

    @Override
    public void setPrecision(F f) {
        precision = f;
        minimizationmethod.setPrecision(f);
    }

    @Override
    public void setMaxiterations(long i) {
        maxiterations = i;
        minimizationmethod.setMaxiterations(i);
    }

    @Override
    public void setNorm(Norm<F> n) {
        norm = n;
        minimizationmethod.setNorm(n);
    }

    @Override
    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
        minimizationmethod.setNumberfactory(numberfactory);
    }

    @Override
    public long getIterationscount() {
        return minimizationmethod.getIterationscount();
    }

    @Override
    public F getErr() {
        return minimizationmethod.getErr();
    }

    @Override
    public abstract Vector<F> minimize(Function<F> f) throws Exception ;

    @Override
    public void setLinesearch(LineSearch linesearch) {
        minimizationmethod.setLinesearch(linesearch);
    }

    /**
     * @return the inequalityrestrictions
     */
    public Function<F>[] getInequalityrestrictions() {
        return inequalityrestrictions;
    }

    /**
     * @param inequalityrestrictions the inequalityrestrictions to set
     */
    public void setInequalityrestrictions(Function<F>[] inequalityrestrictions) {
        this.inequalityrestrictions = inequalityrestrictions;
    }

    /**
     * @return the equalityrestrictions
     */
    public Function<F>[] getEqualityrestrictions() {
        return equalityrestrictions;
    }

    /**
     * @param equalityrestrictions the equalityrestrictions to set
     */
    public void setEqualityrestrictions(Function<F>[] equalityrestrictions) {
        this.equalityrestrictions = equalityrestrictions;
    }

    @Override
    public void resetIterationscount() {
       
    }
    
    
}
