package com.lucas;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {

    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        Request request = new Request.Builder().url("http://localhost:8801/").build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            // Todo handle the response
        } else {
            throw new IOException("Response code " + response.code());
        }
    }
}
