package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TesteTcpActivity extends AppCompatActivity {
    private String SERVER = "10.180.197.9";
    private static int PORT_TCP = 9009;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_tcp);
        ThreadTCP tcp = new ThreadTCP();
        tcp.start();
    }

    class ThreadTCP extends Thread {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String msgResponse = "";
        private String error = "";
        public void run() {
            String msgTCP = "Enviando mensagem por TCP";
            try {
                socket = new Socket(SERVER,PORT_TCP);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                out.writeUTF(msgTCP);
                msgResponse = in.readUTF();
                TesteTcpActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Recebido:" + msgResponse);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}