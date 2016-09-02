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

package br.ufrj.cos.nlptoolboxv2.functions;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Cache<K,E> extends LinkedHashMap<K,E> {

    private final int capacity;

    public Cache(int capacity) {
        super(capacity + 1, 1.1f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Entry eldest) {
        return size() > capacity;
    }
}