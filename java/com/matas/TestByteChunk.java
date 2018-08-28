package com.matas;

import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.MessageBytes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author matas
 * @date 2018/8/28 10:26
 * @email mataszhang@163.com
 */
public class TestByteChunk implements ByteChunk.ByteOutputChannel {
    private ByteChunk byteChunk;
    private FileOutputStream outputStream;

    public TestByteChunk() {
        byteChunk = new ByteChunk();
        byteChunk.setByteOutputChannel(this);
        byteChunk.allocate(3, 7); //初始3,最大缓冲7

        try {
            outputStream = new FileOutputStream(new File("d:/hello.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void realWriteBytes(byte[] buf, int off, int len) throws IOException {
        log(buf, off, len);
        outputStream.write(buf, off, len);
    }

    private void log(byte[] buf, int off, int len) {
        MessageBytes messageBytes = MessageBytes.newInstance();
        messageBytes.setBytes(buf, off, len);
        messageBytes.toChars();
        System.out.println("真实写入=>" + messageBytes.getString());
    }


    public void flush() throws IOException {
        byteChunk.flushBuffer();
    }

    public void doWrite(byte[] bytes) throws IOException {
        byteChunk.append(bytes, 0, bytes.length);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        TestByteChunk testByteChunk = new TestByteChunk();
        byte[] bytes = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};// 8个
        testByteChunk.doWrite(bytes); //超过limit,会调用flushBuffer，进行一次realWriteBytes
        TimeUnit.SECONDS.sleep(10);
        testByteChunk.flush();
    }
}
