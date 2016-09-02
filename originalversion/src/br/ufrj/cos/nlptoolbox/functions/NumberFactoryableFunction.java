/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.functions;

import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import org.jscience.mathematics.structure.Field;

/**
 *
 * @author lages
 */
public interface NumberFactoryableFunction<F extends Field<F>> extends Function<F> {

    public NumberFactory<F> getNumberfactory();

    public void setNumberfactory(NumberFactory<F> numberfactory);

}
