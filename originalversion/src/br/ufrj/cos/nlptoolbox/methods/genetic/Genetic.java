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

package br.ufrj.cos.nlptoolbox.methods.genetic;

import br.ufrj.cos.nlptoolbox.Comparator;
import br.ufrj.cos.nlptoolbox.Function;
import br.ufrj.cos.nlptoolbox.Norm;
import br.ufrj.cos.nlptoolbox.NumberFactory;
import br.ufrj.cos.nlptoolbox.linesearch.LineSearch;
import br.ufrj.cos.nlptoolbox.methods.MinimizationMethod;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

/**
 *
 * @author Diego
 */
public class Genetic<F extends Field<F>> implements MinimizationMethod<F> {

    Random random = new Random();

    Vector<F> initialpoint = null;
    F gamma = null;
    F precision = null;
    long maxiterations = -1;
    Norm<F> norm = null;
    F err = null;
    long nits = 0;
    int  populationsize=20;
    double mutationrate=0.40;
    double mutationsize=0.40;

    F zero = null;

    double scale = 4;

    Comparator<F> comparator = new Comparator<F>();
    
    private NumberFactory<F> numberfactory = null;
    
    
    public void setInitialPoint(Vector<F> variables) {
        initialpoint = variables;
    }
    
    public long getIterationscount() {
        return nits;
    }

    private Vector<F> generateRandom() {
        
        java.util.List r = new java.util.Vector();

        for (int i=0;i<initialpoint.getDimension();i++) {
            r.add(numberfactory.makeNumber(scale * (random.nextDouble()-0.5)));
        }

        return DenseVector.valueOf(r);
    }

    private java.util.List<Vector<F>> mutate(java.util.List<Vector<F>> population) {

        java.util.List l = new java.util.Vector();

        for (Vector<F> v : population) {
            Vector<F> v2 = v;
            if (random.nextDouble() < mutationrate) {
               v2 = mutate(v);
            }
            l.add(v2);
        }

        return l;

    }

    private Vector<F> mutate(Vector<F> v) {
        

        java.util.List l = new java.util.Vector();

        for (int i=0;i<v.getDimension();i++) {
            if (random.nextDouble() < mutationsize) {
                F diff = numberfactory.makeNumber(scale*(random.nextDouble()-0.5));
                //v.get(i).plus(diff);
                l.add(diff);
            } else {
                l.add(zero);
            }
        }
        return v.plus(DenseVector.valueOf(l));
    }

    private java.util.List<Vector<F>> crossover(java.util.List<Vector<F>> population) {

        java.util.List<Vector<F>> ret = new java.util.Vector();

        for (Vector<F> f1 : population) {
            for (Vector<F> f2 : population) {
                if (!f1.equals(f2)) {
                    ret.add(crossover(f1,f2));
                }
            }
        }

        return ret;
    }

    private Vector<F> crossover(Vector<F> v1, Vector<F> v2) {
        
        java.util.List r = new java.util.Vector();

        for (int i=0;i<v1.getDimension();i++) {
            if (random.nextDouble() < 0.5) {
                r.add(v1.get(i));
            } else {
                r.add(v2.get(i));
            }
        }
        return DenseVector.valueOf(r);
    }

    public java.util.List<Vector<F>> selectBest(java.util.List<Vector<F>> population, final Function<F> f) {

        java.util.Comparator<Vector<F>> comp = new java.util.Comparator<Vector<F>>() {

            public int compare(Vector<F> oleft, Vector<F> oright) {
                try {
                    F fevalleft = f.evaluate(oleft);
                    F fevalright = f.evaluate(oright);

                    return comparator.compare(fevalleft, fevalright);

                } catch (Exception ex) {
                    return 0;
                }
                
            }

        };

        // eliminate equals
        java.util.List l = new java.util.Vector();
        for (Vector<F> v : population) {
            if (!l.contains(v)) {
                l.add(v);
            }
        }

        Collections.sort(l,comp);

        return l.subList(0, populationsize);

    }

    public Vector<F> minimize(Function<F> f) throws Exception {

        zero = numberfactory.makeNumber(0);
        
        
        java.util.List<Vector<F>> population = new java.util.Vector();

        for (int i=0;i<populationsize;i++) {
            population.add(initialpoint.plus(generateRandom()));
        }

        Vector<F> xn = initialpoint;
//
        Vector<F> xn1 = null;
//
//
//
        do {
//            Matrix ihessian = f.hessian(xn).inverse();
//            Vector<F> g = f.gradient(xn);
//
//            Vector m = ihessian.times(g);
//
//            xn1 = xn.minus(m.times(gamma));
//
           
//
//            xn = xn1;

            population = mutate(population);

            java.util.List<Vector<F>> newpopulation = crossover(population);
            
            population = selectBest(newpopulation,f);

            xn1 = population.get(0);

            err = norm.norm(xn1.minus(xn));
//
            nits++;

//
        } while (comparator.compare(err, precision) > 0 && nits < maxiterations);
//
        return population.get(0);
    }
    
    

    public void setParameters(F... p) {
       // gamma = p[0];
    }

    public void setPrecision(F f) {
        precision = f;
    }

    public void setMaxiterations(long i) {
        maxiterations = i;
    }

    public void setNorm(Norm<F> n) {
       norm = n;
    }

    public F getErr() {
        return err;
    }
    
    public NumberFactory<F> getNumberfactory() {
        return numberfactory;
    }

    public void setNumberfactory(NumberFactory<F> numberfactory) {
        this.numberfactory = numberfactory;
    }

    @Override
    public void setLinesearch(LineSearch linesearch) {
        
    }

    @Override
    public void resetIterationscount() {
        nits=0;
    }

}
