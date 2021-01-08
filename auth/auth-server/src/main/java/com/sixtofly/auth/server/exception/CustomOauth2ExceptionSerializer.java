package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 自定义oauth2异常的序列化器
 * @author xie yuan bing
 * @date 2019-09-24 22:04
 * @description
 */
public class CustomOauth2ExceptionSerializer extends StdSerializer<CustomOauth2Exception> {

    public CustomOauth2ExceptionSerializer(){
        super(CustomOauth2Exception.class);
    }

    @Override
    public void serialize(CustomOauth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        //将状态码设为 200
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        response.setStatus(200);
        gen.writeStartObject();
        gen.writeStringField("resCode", value.getErrorCode());
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("obj", "");
        gen.writeEndObject();
    }
}
