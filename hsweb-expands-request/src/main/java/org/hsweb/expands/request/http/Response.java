package org.hsweb.expands.request.http;

import java.io.IOException;
import java.io.InputStream;

public interface Response {
    int getCode();

    String asString() throws IOException;

    byte[] asBytes() throws IOException;

    InputStream asStream() throws IOException;
}
