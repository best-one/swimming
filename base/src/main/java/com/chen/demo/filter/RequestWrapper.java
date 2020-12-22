package com.chen.demo.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @ClassName:  RequestWrapper   
 * @Description:请求包装器   
 * @author: jim
 * @date:   2020年12月22日 上午10:30:54      
 * @Copyright:
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    /**
     * 这个必须加,复制request中的bufferedReader中的值
     *
     * @param request
     * @throws IOException
     */
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        //body = StreamUtil.readBytes(request.getReader(), "UTF-8");
        body =IOUtils.toByteArray(request.getInputStream());
//        body = StreamUtil.getByteByStream();

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            public boolean isFinished() {
                return false;
            }

            public boolean isReady() {
                return false;
            }

            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}