package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";
    String API_URL=BASE_URL+"";


    // Member Variables:
    TextView mPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner


        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("bitcoin", "onItemSelected: "+parent.getItemAtPosition(position));
                letsDoSomeNetworking(parent.getItemAtPosition(position).toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("bitcoin", "onNothingSelected: nothing is selected");

            }
        });
    }

    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {
        StringBuilder sb1 = new
                StringBuilder(BASE_URL);
        sb1.append(url);
        Log.d("bitcoin", "letsDoSomeNetworking: url sb1 is  "+sb1.toString());
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(sb1.toString(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                Log.d("a", "onSuccess: ");
                try {
                    String price = responseBody.getString("last");
                    mPriceTextView.setText(price);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });









//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(WEATHER_URL, params, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // called when response HTTP status is "200 OK"
//                Log.d("Clima", "JSON: " + response.toString());
//                WeatherDataModel weatherData = WeatherDataModel.fromJson(response);
//                updateUI(weatherData);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                Log.d("Clima", "Request fail! Status code: " + statusCode);
//                Log.d("Clima", "Fail response: " + response);
//                Log.e("ERROR", e.toString());
//                Toast.makeText(WeatherController.this, "Request Failed", Toast.LENGTH_SHORT).show();
//            }
//        });


    }


}
