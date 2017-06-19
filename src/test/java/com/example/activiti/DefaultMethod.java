package com.example.activiti;


public class DefaultMethod implements D1, D2 {

    public static void main(String[] args) {
        new DefaultMethod().doSome();
    }

    @Override
    public void doSome() {

    }

    @Override
    public void test() {
    }
}

interface D1 {

    void doSome();

    default void test() {
        doSome();
    }
}

interface D2 {

    void doSome();

    default void test() {
        doSome();
    }
}
