package com.thx.demo.test.prox;

/**
 * @Classname Dilireba
 * @Description
 * @Date 2021/9/13 20:23
 * @Created by thx
 */
public class Dilireba implements Advertisement {
    private int id;

    public Dilireba() {
    }

    public Dilireba(int id) {
        this.id = id;
    }

    @Override
    public void display() {

        System.out.println("大家好！我是迪丽热巴！");    }
}
