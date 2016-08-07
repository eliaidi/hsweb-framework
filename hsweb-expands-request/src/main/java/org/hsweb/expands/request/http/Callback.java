package org.hsweb.expands.request.http;

import org.apache.http.HttpMessage;

public interface Callback<C extends HttpMessage> {

    void accept(C message);
}
