/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.cos.nlptoolbox.functions;

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