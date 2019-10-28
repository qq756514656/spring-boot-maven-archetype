package ${package}.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liusy
 */
@Slf4j
public final class OkHttpUtil {

    protected OkHttpUtil() {
    }

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .hostnameVerifier((hostname, session) -> true)
            .build();
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String SUCCESS = "0";

    /**
     * 下载文件
     *
     * @param url     下载链接
     * @param tempDir 保存文件夹
     * @return java.io.File
     */
    public static File download(String url, String tempDir) {
        Request requestData = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = CLIENT.newCall(requestData).execute();
            ResponseBody body = response.body();
            assert body != null;
            InputStream inputStream = body.byteStream();
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            byte[] by = new byte[1024];
            int len;
            while ((len = inputStream.read(by)) != -1) {
                data.write(by, 0, len);
            }
            inputStream.close();
            File file1 = new File(tempDir);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            File file = new File(tempDir + System.currentTimeMillis() + ".pdf");
            FileCopyUtils.copy(data.toByteArray(), file);
            return file;
        } catch (Exception e) {
            log.error("下载异常:", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成短链接
     *
     * @param url     短链接服务URL
     * @param urlText 需要生成短链接的连接
     * @return String 返回已生成的连接
     */
    public static String doShortenUrl(String url, String urlText) {
        try {
            Request requestData = new Request.Builder()
                    .url(url + urlText)
                    .build();
            Response response = CLIENT.newCall(requestData).execute();
            ResponseBody body = response.body();
            assert body != null;
            return body.string();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        if (StringUtils.isBlank(url)) {
            return "";
        }
        String result = null;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = CLIENT.newCall(request).execute();
            result = Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }
        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String get(String url, Map<String, String> heads) {
        if (heads == null || heads.isEmpty()) {
            return get(url);
        }
        String result = null;
        Request.Builder builder = new Request.Builder();
        for (String key : heads.keySet()) {
            if (StringUtils.isBlank(heads.get(key))) {
                continue;
            }
            builder.addHeader(key, heads.get(key));
        }
        Request request = builder.url(url).build();
        try {
            Response response = CLIENT.newCall(request).execute();
            result = Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url
     * @param data 提交的参数为key=value&key1=value1的形式
     */
    public static String post(String url, String data) {
        if (StringUtils.isBlank(url)) {
            return "";
        }
        String result = null;
        RequestBody requestBody = RequestBody.create(data, MediaType.parse("application/json;charset=utf-8"));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = CLIENT.newCall(request).execute();
            result = Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url
     * @param data 提交的参数为key=value&key1=value1的形式
     */
    public static String post(String url, String data, Map<String, String> heads) {
        if (heads == null || heads.isEmpty()) {
            return post(url, data);
        }
        String result = null;
        Request.Builder builder = new Request.Builder();
        for (String key : heads.keySet()) {
            if (StringUtils.isBlank(heads.get(key))) {
                continue;
            }
            builder.addHeader(key, heads.get(key));
        }
        RequestBody requestBody = RequestBody.create(data, MediaType.parse("application/json;charset=utf-8"));
        Request request = builder.url(url).post(requestBody).build();
        try {
            Response response = CLIENT.newCall(request).execute();
            result = Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        }
        return result;
    }
}
