package org.ranasoftcraft.com.calender.jms.config;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class JsonMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        var bytes = inputMessage.getBody().readAllBytes();
        if (bytes.length == 0) {
            return null;
        }
        return super.read(type, contextClass, new HttpInputMessageWrapper(new ByteArrayInputStream(bytes), inputMessage.getHeaders()));
    }

    private static class HttpInputMessageWrapper implements HttpInputMessage {
        private final InputStream body;
        private final HttpHeaders headers;

        private HttpInputMessageWrapper(InputStream body, HttpHeaders headers) {
            this.body = body;
            this.headers = headers;
        }

        @Override
        public @NotNull InputStream getBody() {
            return this.body;
        }

        @Override
        public @NotNull HttpHeaders getHeaders() {
            return this.headers;
        }
    }
}
