package com.fyp.wsn.Entity;

/**
 * Created by Nadith Premaratne on 23/05/2017.
 */
public class Pair {

    String pre;
    String now;

    public Pair(String pre, String now) {
        this.pre = pre;
        this.now = now;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
}
