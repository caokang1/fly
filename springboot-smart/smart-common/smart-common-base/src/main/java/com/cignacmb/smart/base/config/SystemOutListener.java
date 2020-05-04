package com.cignacmb.smart.base.config;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.PrintStream;

/**
 * @Author: caokang
 * @Date: Created in 17:15 2020/4/21
 * @annotation:重写System.out.println方法
 * @version:1.0
 */
@Component
@Log
public class SystemOutListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent event) {

        PrintStream printStream = new PrintStream(System.out) {
            @Override
            public synchronized void  println(boolean x) {
                log.info(Convert.toStr(Boolean.valueOf(x)));
            }
            @Override
            public synchronized void println(char x) {
                log.info(Convert.toStr(Character.valueOf(x)));
            }
            @Override
            public synchronized void println(char[] x) {
                log.info(x == null ? null : new String(x));
            }
            @Override
            public synchronized void println(double x) {
                log.info(Convert.toStr(Double.valueOf(x)));
            }
            @Override
            public synchronized void println(float x) {
                log.info(Convert.toStr(Float.valueOf(x)));
            }
            @Override
            public synchronized void println(int x) {
                log.info(Convert.toStr(Integer.valueOf(x)));
            }
            @Override
            public synchronized void println(long x) {
                log.info(Convert.toStr(Long.valueOf(x)));
            }
            @Override
            public synchronized void println(Object x) {
                log.info(JSON.toJSONString(x));
            }
            @Override
            public synchronized void println(String x) {
                log.info(x);
            }
        };
        System.setOut(printStream);
        System.setErr(printStream);
    }
}
