package tomas.com.sysdist.controller.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.content.Intent;
import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.network.handle_sockets.Services_Push;
import tomas.com.sysdist.controller.view.Dialog.DialogActivity;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;

/**
 * Tomas Yussef Galicia Guzman
 */
public class MainActivity extends AppCompatActivity
{
    private ImageButton list, settings, register;
    private Sqlite_Manager sqlite_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.sqlite_manager = new Sqlite_Manager(this.getApplicationContext());
        this.settings = (ImageButton) this.findViewById(R.id.settingsXML);
        this.list = (ImageButton) this.findViewById(R.id.listXML);
        this.register = (ImageButton) this.findViewById(R.id.registerXML);
        this.startService(new Intent(this.getApplicationContext(), Services_Push.class));

    }

    /*
            Eventos
     */

    public void onClickSettings(View view)
    {
        this.startActivity(new Intent(this.getApplicationContext(), Settings.class));
    }

    public void onClickList(View view)
    {
        Log.e("Iniciado servies", ".....");
        this.services();
    }

    public void onClickRegister(View view)
    {
        this.startActivity(new Intent(this.getApplicationContext(), Register.class));

    }

    private void services()
    {
        Log.e("Iniciado servies", "start");
        this.startActivity(new Intent(this.getApplicationContext(), List.class));
        Log.e("Iniciado servies", ".....");
    }


}
