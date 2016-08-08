package org.hsweb.expands.request.http.simple;

import org.apache.http.HttpResponse;
import org.hsweb.expands.request.http.Response;

import java.io.IOException;

public class SimpleHttpRequest extends AbstractHttpRequest {

    public SimpleHttpRequest(String url) {
        super(url);
    }

    @Override
    protected Response getResultValue(HttpResponse res) throws IOException {
        return new SimpleResponse(res);
    }
}
