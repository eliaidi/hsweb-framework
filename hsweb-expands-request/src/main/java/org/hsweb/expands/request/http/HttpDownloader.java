package org.hsweb.expands.request.http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface HttpDownloader<R> {

    HttpDownloader<R> get() throws IOException;

    HttpDownloader<R> post() throws IOException;

    R write(File file) throws IOException;

    R write(OutputStream outputStream) throws IOException;
}
