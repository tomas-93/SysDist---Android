package tomas.com.sysdist.models.data_base;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Tomas Yussef Galicia Guzman
 */
public class DataBase extends SQLiteOpenHelper
{
    private static final String NAME_FILE = "SysDis.db";
    private static final int VERSION = 1;
    public DataBase(Context context)
    {
        super(context, NAME_FILE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(Schema.CREATE_TABLE_IP);
        database.execSQL(Schema.CREATE_TABLE_STUDENT);
        database.execSQL(Schema.CREATE_TABLE_TEACHER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL(Schema.DELETE_TABLE_IP);
        database.execSQL(Schema.DELETE_TABLE_STUDENT);
        database.execSQL(Schema.DELETE_TABLE_TEACHER);
        this.onCreate(database);
    }


}
