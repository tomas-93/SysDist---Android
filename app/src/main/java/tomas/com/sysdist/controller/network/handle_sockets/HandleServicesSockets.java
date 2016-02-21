package tomas.com.sysdist.controller.network.handle_sockets;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import tomas.com.sysdist.controller.network.sockets.Connect;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;

/**
 * Tomas Yussef Galicia Guzman
 */
public class HandleServicesSockets extends IntentService implements ConstantObjects
{
    public String TAG = "HandleServicesSockets";
    private Connect connect;
    private Sqlite_Manager sqlite_manager;


    public HandleServicesSockets()
    {
        super("HandleServicesSockets");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        try
        {
            this.instace();
            synchronized (this)
            {
                Log.e(this.TAG, "HandleServer Thread......");
                do
                {
                    String jsonStudent = this.sqlite_manager.readDataInJSON(STUDENTS);
                    String jsonTeacher = this.sqlite_manager.readDataInJSON(TEACHER);
                    if (jsonStudent != null)
                    {
                        this.connect.sendData(jsonStudent + "\n" + TAG + "\n");
                        Log.e(this.TAG, "Fin del Proceso");

                    }
                    if (jsonTeacher != null)
                    {
                        this.connect.sendData(jsonTeacher + "\n" + TAG + "\n");
                        Log.e(this.TAG, "Fin del proceso");

                    }
                }while (!isConnected());
                this.connect.theServerClose();
            }

        }catch (UnknownHostException e)
        {
            Log.e(this.TAG, "Posiblemente la ip este mal....");
        }catch(ConnectException err)
        {
            Log.e(this.TAG,"Error en la conexion....");
        }catch (SocketException e)
        {
            Log.e(this.TAG, "Se perdio la conexion del servidor ");
        }catch(Exception e) {
            Log.e(this.TAG,"Error en onHandleIntent()");
            e.printStackTrace();
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
        //True si esta corriendo el servicio, false si esta muerto
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

    private void instace()throws Exception
    {
        this.sqlite_manager = new Sqlite_Manager(this.getApplicationContext());
        Log.i(this.TAG, "Instacia de connect a " + this.sqlite_manager.readObjectIP().getIp());
        this.connect = new Connect(this.sqlite_manager.readObjectIP().getIp());
    }

}
