package tomas.com.sysdist.controller.network.sockets;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;

/**
 * Created by Tomas on 13/10/2015.
 */
public class Connect_Push extends Socket implements ConstantObjects
{
    private static int PORT = 65534;
    private String TAG = "Connect_Push";
    private String ip ;
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;
    private String message;

    public Connect_Push(String ip)throws IOException
    {
        super(ip, PORT);
        this.ip = ip;
        connect();
    }

    public void connect()throws IOException
    {
        Log.i(TAG, "connectClient()");
        this.dataOutputStream = new DataOutputStream(this.getOutputStream());
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
    public void send(String data)throws IOException
    {
        Log.i(TAG, "sendData()");
        this.dataOutputStream.writeBytes(data);
    }
    public void responseServer(Sqlite_Manager sqlite_manager) throws  IOException
    {
        Log.i(TAG, "responseServer()");
        this.message ="";
        while((message = bufferedReader.readLine()) != null)
        {
            if(this.message.length() > 0)
            {
                sqlite_manager.insertDataOfIntoTableStudent(this.message);
                sqlite_manager.insertDataOfIntoTableTeacher(this.message);
            }
        }
    }
    public void theServerClose()
    {
        try
        {
            this.close();

        }catch (Exception e)
        {
            Log.e(TAG,"Respose Server");
            e.printStackTrace();
        }
    }
}
