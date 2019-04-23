package com.linjunfeng.demo.thread.communication.waitAndNotify.demo7;

import java.io.PipedReader;

public class ThreadRead extends Thread {
    private ReadData read;
    private PipedReader input;

    public ThreadRead(ReadData read, PipedReader input) {
        super();
        this.read = read;
        this.input = input;
    }

    @Override
    public void run() {
        read.readMethod(input);
    }
}
