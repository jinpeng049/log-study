package com.easy.study.log;


import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggerFactory;

import java.util.Random;

public class Log extends Logger {
    protected Log(String name) {
        super(name);
    }

    public static Log getLogger(String name) {
        return (Log) LogManager.getLogger(name, new LogLoggerFactory());
    }

    public static Log getLogger(Class clazz) {
        return (Log) LogManager.getLogger(clazz.getName(),
                new LogLoggerFactory());
    }

    /**
     * DEBUG级别日志输出<br/>
     * 日志内容包含存于线程空间中的LogInfo的值，输出格式：<br/>
     * <code>[type:sid][key1:value1,...,keyN:valueN]message</code>
     *
     * @param message 日志主体内容
     */
    public void debug(Object message) {
        if (repository.isDisabled(Level.DEBUG_INT))//判断该日志级别是否启用
            return;
        if (Level.DEBUG.isGreaterOrEqual(getEffectiveLevel()))
            forcedLog(FQCN, Level.DEBUG, message, null);
    }

    /**
     * INFO级别日志输出<br/>
     * 日志内容包含存于线程空间中的LogInfo的值，输出格式：<br/>
     * <code>[type:sid][key1:value1,...,keyN:valueN]message</code>
     *
     * @param message 日志主体内容
     */
    public void info(Object message) {
        if (repository.isDisabled(Level.INFO_INT))//判断该日志级别是否启用
            return;
        if (Level.INFO.isGreaterOrEqual(getEffectiveLevel()))
            forcedLog(FQCN, Level.INFO, message, null);
    }

    /**
     * WARN级别日志输出<br/>
     * 日志内容包含存于线程空间中的LogInfo的值，输出格式：<br/>
     * <code>[type:sid][key1:value1,...,keyN:valueN]message</code>
     *
     * @param message 日志主体内容
     */
    public void warn(Object message) {
        if (repository.isDisabled(Level.WARN_INT))//判断该日志级别是否启用
            return;
        if (Level.WARN.isGreaterOrEqual(getEffectiveLevel()))
            forcedLog(FQCN, Level.WARN, message, null);
    }

    /**
     * ERROR级别日志输出<br/>
     * 日志内容包含存于线程空间中的LogInfo的值，输出格式：<br/>
     * <code>[type:sid][key1:value1,...,keyN:valueN]message</code>
     *
     * @param message 日志主体内容
     */
    public void error(Object message) {
        if (repository.isDisabled(Level.ERROR_INT))//判断该日志级别是否启用
            return;
        if (Level.ERROR.isGreaterOrEqual(getEffectiveLevel()))
            forcedLog(FQCN, Level.ERROR, message, null);
    }

    /**
     * ERROR级别日志输出，用于输出异常日志<br/>
     * 日志内容包含存于线程空间中的LogInfo的值，输出格式：<br/>
     * <code>[type:sid][key1:value1,...,keyN:valueN]message</code>
     *
     * @param message 日志主体内容
     * @param t       Throwable对象
     */
    public void error(Object message, Throwable t) {
        if (repository.isDisabled(Level.ERROR_INT))//判断该日志级别是否启用
            return;
        if (Level.ERROR.isGreaterOrEqual(getEffectiveLevel()))
            forcedLog(FQCN, Level.ERROR, message, t);
    }

    @Override
    protected void forcedLog(String fqcn, Priority level, Object message,
                             Throwable t) {
        super.forcedLog(fqcn, level, formatLogMessage(message), t);
    }

    protected String generateSessionId() {
        Random rand = new Random();
        StringBuffer sid = new StringBuffer();

        sid.append(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            sid.append((char) ('a' + rand.nextInt(26)));
        }

        return sid.toString();
    }

    protected Object formatLogMessage(Object message) {
        if (message != null && message instanceof String) {

            String rawText = message.toString();

            StringBuffer newText = new StringBuffer();
            newText.append("[transid:" + TransIdUtils.get() + "]  ");

            newText.append(rawText);

            return newText.toString();
        }

        return message;
    }

    static class LogLoggerFactory implements LoggerFactory {
        public Logger makeNewLoggerInstance(String s) {
            return new Log(s);
        }
    }


    private static final String FQCN;

    static {
        FQCN = (Log.class).getName();
    }

}