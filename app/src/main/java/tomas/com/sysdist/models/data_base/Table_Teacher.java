package tomas.com.sysdist.models.data_base;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;

import tomas.com.sysdist.models.objects.Teacher;


/**
 * Tomas Yussef Galicia Guzman
 */
public class Table_Teacher
{
    private SQLiteDatabase sqLiteDatabase;
    public Table_Teacher(SQLiteDatabase database)
    {
        this.sqLiteDatabase = database;
    }

    public void insertIntoTableTeacher(long id, String name, String surname, String career,
                                       String phone, String email)throws SQLiteException
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema._ID, id);
        contentValues.put(Schema.COLUMN_NAME, name);
        contentValues.put(Schema.COLUMN_SURNAME, surname);
        contentValues.put(Schema.COLUMN_CAREER, career);
        contentValues.put(Schema.COLUMN_PHONE, phone);
        contentValues.put(Schema.COLUMN_EMAIL, email);
        this.sqLiteDatabase.insertOrThrow(Schema.TABLE_TEACHER, Schema.COLUMN_NULL, contentValues);


    }
    public Cursor readDataIntoTableTeacher()throws SQLiteException
    {
        final String [] COLUMNS =
                                {
                                        Schema._ID,
                                        Schema.COLUMN_NAME,
                                        Schema.COLUMN_SURNAME,
                                        Schema.COLUMN_CAREER,
                                        Schema.COLUMN_PHONE,
                                        Schema.COLUMN_EMAIL
                                };
        return this.sqLiteDatabase.query(Schema.TABLE_TEACHER,
                                            COLUMNS,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null);
    }

    public Teacher readTheCursorTeacher(Cursor cursor)throws SQLiteException
    {
        Teacher teacher = new Teacher();
        teacher.setNumber(cursor.getLong(cursor.getColumnIndexOrThrow(Schema._ID)));
        teacher.setNameTeacher(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_NAME)));
        teacher.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_SURNAME)));
        teacher.setRace(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_CAREER)));
        teacher.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_PHONE)));
        teacher.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_EMAIL)));
        return teacher;
    }
    public void removeElement(String id)
    {
        final String ARGS []= {id};
        this.sqLiteDatabase.delete(Schema.TABLE_TEACHER, Schema._ID + "=?", ARGS);
    }
    public void removeElement()
    {
        this.sqLiteDatabase.execSQL(Schema.DELETE_TABLE_TEACHER);
        this.sqLiteDatabase.execSQL(Schema.CREATE_TABLE_TEACHER);
    }
}
