package com.jf.shop.login.MapTest;

import java.util.Objects;

public class HashMaps {

    private int count;

    public HashMaps(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashMaps hashMaps = (HashMaps) o;
        return count == hashMaps.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    /*@Override
    public String toString() {
        return "HashMaps{" +
                "count=" + count +
                '}';
    }*/
}
