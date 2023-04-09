package com.oneconstant.safechat.auth;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.*;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class AadharVerification {

    String res;

    String secretKey = "I7xD4pPQhpvRPAUCHtdjopwUdUpqgrts4mQna49KZE1st0OdMBGtN105Fud8rSSeV";
    String clientId = "dc19c7c85429c01171ffa1e85e94af78:4ba0c648a931f55f0f2544eb94ca5208";

    public String makeCall(String url, String requestBody) {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(requestBody, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("secretKey", secretKey)
                .addHeader("clientId", clientId)
                .addHeader("Content-Type", "application/json")
                .build();

        Log.e("api_call_req", request.toString());


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("api_call", "api_call");
                // Handle API request failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.e("api_call_r", responseBody.toString());
               res = responseBody.toString();
                try {
                    Gson gson = new Gson();
                    JSONObject json = new JSONObject(responseBody.toString());
                    JSONObject result = (JSONObject) json.get("result");
                    JSONObject data = (JSONObject) result.get("data");
                    String client_id = (String) data.get("client_id");
                    Log.d("DATA", client_id);
                } catch (Exception e) {
                    Log.e("api_call_e", e.getMessage());
                }
                // Process the API response
            }
        });
        return  res;
    }
}





