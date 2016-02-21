package tomas.com.sysdist.controller.network.handle_sockets;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import tomas.com.sysdist.controller.network.sockets.Connect_Push;
import tomas.com.sysdist.controller.view.Dialog.DialogActivity;
import tomas.com.sysdist.controller.view.Dialog.DialogConnect;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;

/**
 * Created by Tomas on 12/10/2015.
 */
public class Services_Push extends IntentService
{
    private Connect_Push connect_push;
    private Config config;
    private Sqlite_Manager sqlite_manager;
    private boolean flag;
    private String TAG = "Services_Push";

    public Services_Push()
    {
        super("PUSH");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        try
        {
            this.instace();
            synchronized (this)
            {
                Log.w(this.TAG, "Thread......");

                if (this.isConnected())
                    this.connect_push.responseServer(this.sqlite_manager);
                else
                    Log.w(this.TAG, "Red inabilitada");
            }
        } catch (UnknownHostException e)
        {
            Log.e(this.TAG, "Posiblemente la ip este mal....");
        } catch (ConnectException err)
        {
            Log.e(this.TAG, "Error en la conexion....");
        } catch (Exception e)
        {
            Log.e(this.TAG, "Error en onHandleIntent()");
            e.printStackTrace();
            this.flag = false;
        }
    }
    public boolean isConnected()
    {
        boolean flag = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info == null || !info.isConnected() || !info.isAvailable())
        {
            flag = false;
        }
        return flag;
    }
    public boolean onStart(Class<?> serviceClass, Context context)
    {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE))
        {
            if(serviceClass.getName().equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }
    private void instace()throws IOException
    {
        Log.i(TAG, "onHandleIntent()");
        this.flag = true;
        this.sqlite_manager = new Sqlite_Manager(this.getApplicationContext());
        this.config = this.sqlite_manager.readObjectIP();
        this.connect_push = new Connect_Push(config.getIp());
    }
}
