package com.example.ipinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPress(view);
            }
        });
    }

    public void buttonPress(View view)
    {
        if(checkIP())
            getIPInfo();
    }

    private boolean checkIP() {
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText e1 = (EditText) findViewById(R.id.ip1);
        if(e1.getText().toString().matches(""))
        {
            textView.setText("Type something in first area");
            return false;
        }
        if(Integer.valueOf(e1.getText().toString())<0||Integer.valueOf(e1.getText().toString())>255)
        {
            textView.setText("Wrong ip, set between 0-255 in first area");
            return false;
        }
        EditText e2 = (EditText) findViewById(R.id.ip2);
        if(e2.getText().toString().matches(""))
        {
            textView.setText("Type something in second area");
            return false;
        }
        if(Integer.valueOf(e2.getText().toString())<0||Integer.valueOf(e2.getText().toString())>255)
        {
            textView.setText("Wrong ip, set between 0-255 in second area");
            return false;
        }
        EditText e3 = (EditText) findViewById(R.id.ip3);
        if(e3.getText().toString().matches(""))
        {
            textView.setText("Type something in third area");
            return false;
        }
        if(Integer.valueOf(e3.getText().toString())<0||Integer.valueOf(e3.getText().toString())>255)
        {
            textView.setText("Wrong ip, set between 0-255 in third area");
            return false;
        }
        EditText e4 = (EditText) findViewById(R.id.ip4);
        if(e4.getText().toString().matches(""))
        {
            textView.setText("Type something in last area");
            return false;
        }
        if(Integer.valueOf(e4.getText().toString())<0||Integer.valueOf(e4.getText().toString())>255)
        {
            textView.setText("Wrong ip, set between 0-255 in last area");
            return false;
        }


        return true;
    }

    private String getIP() {
        EditText e1 = (EditText) findViewById(R.id.ip1);
        EditText e2 = (EditText) findViewById(R.id.ip2);
        EditText e3 = (EditText) findViewById(R.id.ip3);
        EditText e4 = (EditText) findViewById(R.id.ip4);

        String ip = e1.getText().toString() + "." + e2.getText().toString() + "." +
                e3.getText().toString() + "." + e4.getText().toString();

        return ip;
    }

    private void printInfo(IPInfo info) {
        TextView textView = (TextView) findViewById(R.id.textView);

        String s;

        if(info == null) s = "Faild";
        else {
            s = "ip: " + info.getIp() + "\n" +
                    "hostname: " + info.getHostname() + "\n" +
                    "city: " + info.getCity() + "\n" +
                    "region: " + info.getHostname() + "\n" +
                    "country: " + info.getHostname() + "\n" +
                    "loc: " + info.getHostname() + "\n" +
                    "org: " + info.getHostname() + "\n" +
                    "postal: " + info.getHostname() + "\n" +
                    "timezone: " + info.getHostname() + "\n" +
                    "readme: " + info.getHostname();
        }
        textView.setText(s);
    }


    private void getIPInfo(){

        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class, getIP());

        Call<IPInfo> call = apiInterface.getIPInfo();

        call.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {
                printInfo(response.body());
            }

            @Override
            public void onFailure(Call<IPInfo> call, Throwable t) {
                printInfo(null);
            }
        });
    }

}