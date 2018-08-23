package com.aprendiz.ragp.turisapp9;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aprendiz.ragp.turisapp9.models.Lugares;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    List<Lugares> lugaresList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        inputData();

    }
    //Método para la obtención de datos del servicio web y splash
    private void inputData() {
        StrictMode.ThreadPolicy.Builder policy = new StrictMode.ThreadPolicy.Builder();
        StrictMode.setThreadPolicy(policy.detectAll().build());

        String lugares ="https://prueba-a473c.firebaseio.com/Lugares.json";

        URL url;
        HttpURLConnection connection;
        try{
            url = new URL(lugares);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine=bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            String json = response.toString();

            Gson gson = new Gson();

            Type type = new TypeToken<List<Lugares>>(){}.getType();
            lugaresList = gson.fromJson(json,type);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this,MenuT.class);
                    startActivity(intent);
                    finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask,2000);




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
