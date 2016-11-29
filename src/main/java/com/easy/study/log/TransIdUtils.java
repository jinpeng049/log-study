package com.easy.study.log;

import com.google.common.base.Strings;

import java.util.UUID;

public class TransIdUtils {

    private final ThreadLocal<String> threadLocalString = new ThreadLocal<>();

    private static TransIdUtils transIdUtils = null;

    private TransIdUtils() {

    }

    private final static TransIdUtils getInstance() {
        if (transIdUtils == null)
            transIdUtils = new TransIdUtils();
        return transIdUtils;
    }

    public static String get() {
        String transactionid = TransIdUtils.getInstance().threadLocalString.get();
        if (Strings.isNullOrEmpty(transactionid)) {
            transactionid = UUID.randomUUID().toString();
            TransIdUtils.getInstance().threadLocalString.set(transactionid);
        }
        return transactionid;
    }

    public static void main(String[] args) {
//        for (Integer i = 0; i < 10; i++) {
//            Thread1 t = new Thread1();
//            t.start();
//
//        }
        System.err.println(Strings.isNullOrEmpty(null));
    }

}

class Thread1 extends Thread {

    @Override
    public void run() {
        System.err.println(TransIdUtils.get());
    }
}