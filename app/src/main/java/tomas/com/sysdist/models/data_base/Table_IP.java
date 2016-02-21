package tomas.com.sysdist.models.data_base;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.util.Log;

import tomas.com.sysdist.models.objects.Config;
/**
 * Tomas Yussef Galicia Guzman
 */
public class Table_IP
{
    private SQLiteDatabase sqLiteDatabase;

    public Table_IP(SQLiteDatabase sqLiteDatabase)
    {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insertIntoTableIp(int id, String ip, String status, String err)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema._ID, id);
        contentValues.put(Schema.COLUMN_IP, ip);
        contentValues.put(Schema.COLUMN_STATUS, status);
        contentValues.put(Schema.COLUMN_ERROR_SERVER, err);
        this.removeElement("0");
        this.sqLiteDatabase.insert(Schema.TABLE_IP, Schema.COLUMN_NULL, contentValues);

    }
    public Config readDataIntoTableIp()
    {
        final String [] COLUMNS =
                {
                        Schema._ID,
                        Schema.COLUMN_IP,
                        Schema.COLUMN_STATUS,
                        Schema.COLUMN_ERROR_SERVER
                };
        return this.readTheCursorIp(this.sqLiteDatabase.query(
                                                            Schema.TABLE_IP,
                                                            COLUMNS,
                                                            null,
                                                            null,
                                                            null,
                                                            null,
                                                            null));

    }
    public void removeElement(String id)
    {
        final String ARGS []= {id};
        this.sqLiteDatabase.delete(Schema.TABLE_IP, Schema._ID + "=?", ARGS);
    }
    private Config readTheCursorIp(Cursor cursor)
    {
        Config config = new Config();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            config.setIp(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_IP)));
            config.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_STATUS)));
            config.setErro(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_ERROR_SERVER)));
        }
        cursor.close();
        return config;
    }
}
