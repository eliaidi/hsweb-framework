package org.hsweb.expands.shell;

import org.hsweb.commons.file.FileUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void testPing() throws Exception {
        int[] count = new int[1];
        Shell.build("ping", "www.baidu.com").onProcess((line, helper) -> {
            System.out.println(line);
            if (count[0]++ > 10) helper.kill();
        }).exec();
    }

    @Test
    public void testLs() throws Exception {
        Shell.build("ls")
                .onProcess((line, helper) -> System.out.println(line))
                .exec();
    }

    @Test
    public void testShellFile() throws Exception {
        Shell.build("bash", FileUtils.getResourceAsFile("test.sh").getAbsolutePath())
                .onProcess((line, helper) -> System.out.println(line))
                .exec();
    }

    @Test
    public void testText() throws Exception {
        Shell.buildText("echo helloShell \n ls").dir("/")
                .onProcess((line, helper) -> System.out.println(line))
                .exec();
    }
}