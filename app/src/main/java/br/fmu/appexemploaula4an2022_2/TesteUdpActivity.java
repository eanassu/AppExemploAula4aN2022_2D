package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TesteUdpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_udp);
        ThreadUDP udp = new ThreadUDP();
        udp.start();
    }

    class ThreadUDP extends Thread {
        String SERVER = "10.180.197.9";
        int PORT_UDP = 9008;
        private DatagramSocket socket;
        private DatagramPacket msgRequest;
        private String msgReturn = "";
        private String error= "";

        public void run() {
            try {
                String msgUDP = "mensagem UDP";

                socket = new DatagramSocket();

                msgRequest = new DatagramPacket(msgUDP.getBytes(), msgUDP.getBytes().length,
                        InetAddress.getByName(SERVER), PORT_UDP);

                socket.send(msgRequest);

                DatagramPacket msgResponse = new DatagramPacket(new byte[1024], 1024);

                socket.receive(msgResponse);
                msgReturn = new String(msgResponse.getData()).trim();

                TesteUdpActivity.this.runOnUiThread( new Runnable() {
                    public void run() {
                        System.out.println("UDP Socket received: " + msgReturn);
                    }
                });

                socket.close();

            } catch (Exception e) {
                error = e.getMessage();

                TesteUdpActivity.this.runOnUiThread( new Runnable() {
                    public void run() {
                        System.out.println("UDP Socket error: " + error);
                    }
                });
            }
        }//end of run method
    }//end of ThreadUDP


}