package com.nextyu.study.design.pattern.decorator.javaio;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义 Java I/o 装饰器.
 *
 * @author nextyu
 * @version 1.0
 */
public class LowerCaseInputStream extends FilterInputStream {
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1 ? c : Character.toLowerCase((char) c));

    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
