package com.example.elbagory.orthodroid.utils;

import androidx.annotation.Nullable;

public class Pair<F, S> {

    F first;
    S second;

    public Pair() {

    }

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof Pair) {

            Pair pair = (Pair) obj;
            if (pair.first.equals(this.first) && pair.second.equals(this.second)) return true;
            else return false;
        } else return false;

    }
}
