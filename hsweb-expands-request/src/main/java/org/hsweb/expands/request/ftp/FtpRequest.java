package org.hsweb.expands.request.ftp;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Consumer;

public interface FtpRequest {

    void encode(String encode);

    boolean login() throws IOException;

    boolean logout() throws IOException;

    List<FTPFile> lsDir() throws IOException;

    List<FTPFile> lsDir(String path) throws IOException;

    List<FTPFile> ls() throws IOException;

    List<FTPFile> ls(String path) throws IOException;

    boolean mkdir(String path) throws IOException;

    FtpRequest cd(String path) throws IOException;

    boolean rename(String oldName, String newName) throws IOException;

    boolean rm(String name) throws IOException;

    boolean upload(String fileName, InputStream inputStream) throws IOException;

    boolean upload(File file) throws IOException;

    void download(String name, OutputStream outputStream) throws IOException;
}
