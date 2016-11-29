package com.easy.study.log;


//import org.apache.log4j.Logger;

public class Test {
//    private static Logger log = Log.getLogger(Test.class);

//    private static Logger log = new LogA().getLogger(Test.class.getName());

    static Log log = Log.getLogger(Test.class);

    //    private static Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {

        //第一种使用方法（效率低）
        log.debug("我是一条debug消息:{};");


//        //第二种使用方法
//        Log.debug(log, "我是一条debug消息 {} {}", "参数1", "参数2");
//
//        RuntimeException e = new RuntimeException("错误");
        try {
            String e = null;
            e.equals("a");
        } catch (Exception e1) {
            log.error("我是一条error消息", e1);
        }

//
//        //第一种使用方法（效率低）

//
//        //第二种使用方法
//        Log.error(log, e, "<-异常对象放前面, 我是一条带参数的error消息 {} {}", "参数1", "参数2");
    }
}