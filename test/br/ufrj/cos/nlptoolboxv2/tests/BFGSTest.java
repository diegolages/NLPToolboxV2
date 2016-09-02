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

import br.ufrj.cos.nlptoolboxv2.QuadraticNorm;
import br.ufrj.cos.nlptoolboxv2.functions.BananaFunction;
import br.ufrj.cos.nlptoolboxv2.linesearch.NewtonRaphson;
import br.ufrj.cos.nlptoolboxv2.methods.quasinewton.BFGS;
import java.util.Arrays;
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
public class BFGSTest {
    
    public BFGSTest() {
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
    public void testBFGS() throws Exception {

        BananaFunction f2 = new BananaFunction();
        
        double [] iv = new double [] { 1.2, 1.2 };
        
        
        BFGS n = new BFGS();
        n.setInitialPoint(iv);
        n.setMaxiterations(10000);
        n.setNorm(new QuadraticNorm());
        n.setPrecision(0.000000001);
        
        NewtonRaphson linesearch = new NewtonRaphson();
        linesearch.setMaxiterations(10);
        linesearch.setPrecision(0.000000001);
        n.setLinesearch(linesearch);
        
        double [] min = n.minimize(f2);
        
        assertTrue(Math.abs(min[0] - 1.0) < 0.00001);
        assertTrue(Math.abs(min[1] - 1.0) < 0.00001);
        
    }
    
}
