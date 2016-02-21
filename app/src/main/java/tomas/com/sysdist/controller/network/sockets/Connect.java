package tomas.com.sysdist.controller.network.sockets;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Connect extends Socket
{

    private DataOutputStream output;
    private BufferedReader readInput;
    private String ipAdd;
    private final String TAG = "Connect ";
    private static final int PORT = 65535;


    public Connect(String ip) throws Exception
    {
        super(ip, PORT);
        this.ipAdd = ip;
        this.connectClient();
    }
    public String getIP()
    {
        return this.ipAdd;
    }

    private void connectClient()
    {
        try
        {
            Log.e(TAG,"connectClient()");
            this.output = new DataOutputStream(this.getOutputStream());
            this.readInput = new BufferedReader(new InputStreamReader(this.getInputStream()));
        }catch (Exception e)
        {
            Log.e(TAG,"error en connectClient()");
            e.printStackTrace();
        }
    }
    public void sendData(String data) throws Exception
    {
        Log.e(TAG, "sendData()");
        this.output.writeBytes(data);
    }

    public void responseServer() throws SQLiteConstraintException, Exception
    {
        Log.i(TAG, "responseServer()");
        String inputString = this.readInput.readLine();
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
