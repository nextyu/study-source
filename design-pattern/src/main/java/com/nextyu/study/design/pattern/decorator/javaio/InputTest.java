package com.nextyu.study.design.pattern.decorator.javaio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author nextyu
 * @version 1.0
 */
public class InputTest {
    public static void main(String[] args) throws Exception {
        int c = 0;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("F:\\test.txt")));

        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }

        in.close();
    }
}
