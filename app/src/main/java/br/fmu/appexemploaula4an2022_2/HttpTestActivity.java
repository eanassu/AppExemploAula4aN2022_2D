package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        ThreadHTTP http = new ThreadHTTP();
        http.start();
    }

    class ThreadHTTP extends Thread{

        private String leitura = "";
        private String error = "";

        public void run(){
            HttpURLConnection httpConn = null;
            try {
                URL urlObj = new URL( "http://www.google.com");
                httpConn = (HttpURLConnection) urlObj.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

                String line;
                while( (line = rd.readLine()) != null ) {
                    System.out.println(line);
                }
            } catch ( Exception e ) {
                error = e.getMessage();
                System.out.println( "Http Error: " + error );
            } finally {
                httpConn.disconnect();

            }

        }//end of run method
    }//end of ThreadTCP

}