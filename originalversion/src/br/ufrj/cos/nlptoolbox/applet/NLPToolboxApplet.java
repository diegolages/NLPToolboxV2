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


package br.ufrj.cos.nlptoolbox.applet;

import br.ufrj.cos.nlptoolbox.gui.MainJPanel;
import javax.swing.JApplet;

/**
 *
 * @author Diego
 */
public class NLPToolboxApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void start() {
        this.add(new MainJPanel());
    }

    @Override
    public void stop() {
        super.stop();
    }

    // TODO overwrite start(), stop() and destroy() methods
    
    

}
