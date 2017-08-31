package com.jingle.wallpaperswitcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * @author FBJH73
 */
public class HttpUtil {
    static String response = null;

    public static void sendHttpRequestByConnection(final String address) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    // connection.setDoOutput(true);//这一句不能写，否则会出错
                    InputStream in = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    response = responseBuilder.toString();

                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("exception", "网络错误");
                } finally {
                    if (connection != null) {
                        try {
                            connection.disconnect();
                        } catch (Exception e2) {
                            // TODO: handle exception
                            e2.printStackTrace();
                        }
                    }

                }
            }
        }).start();

    }

    public static void sendRequestByHttpClient(final String address) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(address);
                    HttpResponse httpResponse = client.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        response = EntityUtils.toString(entity, "utf-8");
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("exception", "网络错误");
                }

            }
        }).start();

    }

}
