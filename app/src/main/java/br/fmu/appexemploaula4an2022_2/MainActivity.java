package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirDesenho(View view) {
        Intent intent = new Intent( this, DesenhoActivity.class);
        startActivity(intent);
    }

    public void abrirTesteSensores(View view) {
        Intent intent = new Intent( this, SensoresActivity.class);
        startActivity(intent);
    }

    public void abrirTesteBD(View view) {
        Intent intent = new Intent( this, TesteBDActivity.class );
        startActivity(intent);
    }

    public void abrirTesteTcp(View view) {
        Intent intent = new Intent(this, TesteTcpActivity.class);
        startActivity(intent);
    }

    public void abrirTesteUdp(View view) {
        Intent intent = new Intent(this, TesteUdpActivity.class);
        startActivity(intent);
    }

    public void abrirTesteHttp(View view) {
        Intent intent = new Intent(this, HttpTestActivity.class );
        startActivity(intent);
    }
}