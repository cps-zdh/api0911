package com.sen.api.functions;

import com.sen.api.functions.Function;

import java.util.UUID;

public class UUIDFunction implements Function {
    @Override
    public String execute(String[] args) {

        return UUID.randomUUID().toString();
    }

    @Override
    public String getReferenceKey() {
        return "uuid";
    }

    public static void main(String[] args) {
        System.out.println("格式前的UUID ： " + UUID.randomUUID().toString());
        System.out.println("格式化后的UUID ：" + UUID.randomUUID().toString().replace("-", ""));
    }
}
