package com.krjian.inner.simplelang;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhujian on 10/3/14.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String fileName = "program.sl";
        Executor program = new Executor(Executor.loadCmd(new File(fileName)));
        while(program.exec()){};
    }
}
