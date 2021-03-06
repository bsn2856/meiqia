package com.meiqia.meiqia.common.struct;

import java.io.Serializable;

public class Pair<K,V> implements Serializable {
    private K key;

    private V value;

    public Pair() {
    }

    public static <K,V> Pair<K,V> of(K k, V v){
        return  new Pair<>(k,v);
    }


    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
