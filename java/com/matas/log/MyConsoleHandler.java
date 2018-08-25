package com.matas.log;

import java.util.logging.ConsoleHandler;

/**
 * ConsoleHandler默认使用System.err输入日志。这里修改为使用System.out
 *
 * @author matas
 * @date 2018/8/25 14:10
 * @email mataszhang@163.com
 */
public class MyConsoleHandler extends ConsoleHandler {
    public MyConsoleHandler() {
        super();
        this.setOutputStream(System.out);
    }
}
