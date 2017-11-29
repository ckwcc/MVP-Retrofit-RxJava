package com.ckw.zfsoft.simplemvp.netloader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * BaseInterceptor
 * Created by LIUYONGKUI726 on 2016-06-30.
 * {@link # https://github.com/NeglectedByBoss/RetrofitClient}
 */
public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //封装headers
        Request request = chain.request().newBuilder()
                .build();
        Headers headers = request.headers();
        //获取请求url地址
        String requestUrl = request.url().toString();
        //获取请求方式
        String methodStr = request.method();
        //获取请求body
        RequestBody body = request.body();
        String bodyStr = (body==null?"":body.toString());
        Response response = chain.proceed(request);
        return response;
    }
}