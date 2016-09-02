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
package br.ufrj.cos.nlptoolboxv2.tests;

import br.ufrj.cos.nlptoolboxv2.Function;
import br.ufrj.cos.nlptoolboxv2.QuadraticNorm;
import br.ufrj.cos.nlptoolboxv2.functions.ExpressionFunctionFactory;
import br.ufrj.cos.nlptoolboxv2.linesearch.NewtonRaphson;
import br.ufrj.cos.nlptoolboxv2.methods.Newton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zcp7
 */
public class NewtonExpressionTest {
    
    public NewtonExpressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * .
     */
    @Test
    public void testSimpleFunction() throws Exception {

        Function function = ExpressionFunctionFactory.makeExpressionFunction("x1*x1+x2*x2");
    
        double [] iv = new double [] { -2.0, 1.0 };
        
        Newton n = new Newton();
        n.setInitialPoint(iv);
        n.setMaxiterations(10000);
        n.setNorm(new QuadraticNorm());
        n.setParameters(0.9);
        n.setPrecision(0.000000001);
        
        NewtonRaphson linesearch = new NewtonRaphson();
        linesearch.setMaxiterations(20);
        linesearch.setPrecision(0.000000001);
        n.setLinesearch(linesearch);
        
        double [] min = n.minimize(function);
        
        assertTrue(Math.abs(min[0] - 0.0) < 0.00001);
        assertTrue(Math.abs(min[1] - 0.0) < 0.00001);
        
        
    }
    
    @Test
    public void testBananaFunction() throws Exception {

        Function function = ExpressionFunctionFactory.makeExpressionFunction("(1.0 - x1)*(1.0 - x1) + 100.0*(x2 - x1*x1)*(x2 - x1*x1)");
    
        double [] iv = new double [] { -2.0, 1.0 };
        
        Newton n = new Newton();
        n.setInitialPoint(iv);
        n.setMaxiterations(10000);
        n.setNorm(new QuadraticNorm());
        n.setParameters(0.9);
        n.setPrecision(0.000000001);
        
        NewtonRaphson linesearch = new NewtonRaphson();
        linesearch.setMaxiterations(20);
        linesearch.setPrecision(0.000000001);
        n.setLinesearch(linesearch);
        
        double [] min = n.minimize(function);
        
        assertTrue(Math.abs(min[0] - 1.0) < 0.00001);
        assertTrue(Math.abs(min[1] - 1.0) < 0.00001);
        
        
    }
    
}
