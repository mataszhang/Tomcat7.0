package com.matas;

import org.apache.tomcat.util.buf.MessageBytes;

/**
 * @author matas
 * @date 2018/8/28 10:14
 * @email mataszhang@163.com
 */
public class TestMessageBytes {
    public static void main(String[] args) {
        byte[] buf = new byte[]{1, 2, 3, 4, 'a', 'b', 'c'};
        MessageBytes messageBytes = MessageBytes.newInstance();
        messageBytes.setBytes(buf, 4, 3);
        messageBytes.toChars();
        System.out.println(messageBytes.getType());
        System.out.println(messageBytes.getString());
    }
}
