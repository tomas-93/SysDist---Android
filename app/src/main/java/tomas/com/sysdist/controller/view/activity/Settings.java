package tomas.com.sysdist.controller.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.network.handle_sockets.HandleServicesSockets;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Settings extends AppCompatActivity
{
    private EditText octeto1, octeto2, octeto3, octeto4;
    private ImageButton returnButton, plus;
    private Animation anaimation;
    private TextView textView;
    private HandleServicesSockets services;
    private Sqlite_Manager sqlite_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_settings);
        this.services = new HandleServicesSockets();
        this.sqlite_manager = new Sqlite_Manager(this.getApplicationContext());
        this.returnButton = (ImageButton) this.findViewById(R.id.returnXML);
        this.plus = (ImageButton) this.findViewById(R.id.plusXML);
        this.textView = (TextView) this.findViewById(R.id.messageXML);
        this.octeto1 = (EditText) this.findViewById(R.id.octeto1XML);
        this.octeto2 = (EditText) this.findViewById(R.id.octeto2XML);
        this.octeto3 = (EditText) this.findViewById(R.id.octeto3XML);
        this.octeto4 = (EditText) this.findViewById(R.id.octeto4XML);
        this.verifyIP();
        this.anaimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.animator_button);
    }

    public void onClickReturn(View view)
    {
        this.returnButton.startAnimation(this.anaimation);
        this.startActivity(new Intent(this.getApplicationContext(), MainActivity.class));

    }
    public void onClickPlus(View view)
    {
        this.plus.startAnimation(this.anaimation);
        this.message();
    }


    private void message()
    {
        String ip = this.octeto1.getText() + "."+
                this.octeto2.getText()+"."+
                this.octeto3.getText()+"."+
                this.octeto4.getText();
        String message = "IP del Servidor\n" +ip;
        Config data = new Config();
        data.setIp(ip);
        data.setErro("false");
        data.setStatus("false");
        this.sqlite_manager.insertObjectsIP(data);
        this.textView.setText(message);

    }
    public void verifyIP()
    {
        Config config = this.sqlite_manager.readObjectIP();
        String message = "IP del Servidor\n" + config.getIp();
        this.textView.setText(message);

    }

}
