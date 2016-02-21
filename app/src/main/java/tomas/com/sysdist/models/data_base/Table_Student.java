package tomas.com.sysdist.models.data_base;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;

import tomas.com.sysdist.models.objects.Student;
/**
 * Tomas Yussef Galicia Guzman
 */
public class Table_Student
{
    private SQLiteDatabase sqLiteDatabase;
    public Table_Student(SQLiteDatabase sqLiteDatabase)
    {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insertIntoTableStudent(long id, String name, String surname, String school,
                                       String career, String level, String phone , String email)throws SQLiteConstraintException
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema._ID, id);
        contentValues.put(Schema.COLUMN_NAME, name);
        contentValues.put(Schema.COLUMN_SURNAME, surname);
        contentValues.put(Schema.COLUMN_SCHOOL, school);
        contentValues.put(Schema.COLUMN_CAREER, career);
        contentValues.put(Schema.COLUMN_LEVEL, level);
        contentValues.put(Schema.COLUMN_PHONE, phone);
        contentValues.put(Schema.COLUMN_EMAIL, email);

        this.sqLiteDatabase.insertOrThrow(Schema.TABLE_STUDENT, Schema.COLUMN_NULL, contentValues);

    }

    public Cursor readDataIntoTableSudent()throws SQLiteException
    {
        final String[] COLUMNS =
                                {
                                        Schema._ID,
                                        Schema.COLUMN_NAME,
                                        Schema.COLUMN_SURNAME,
                                        Schema.COLUMN_SCHOOL,
                                        Schema.COLUMN_CAREER,
                                        Schema.COLUMN_LEVEL,
                                        Schema.COLUMN_PHONE,
                                        Schema.COLUMN_EMAIL
                                };

        return this.sqLiteDatabase.query(Schema.TABLE_STUDENT,
                                            COLUMNS,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null);

    }
    public Student readTheCursorStudent(Cursor cursor)throws SQLiteException
    {
        Student student = new Student();
        student.setNumber(cursor.getLong(cursor.getColumnIndexOrThrow(Schema._ID)));
        student.setNameStudent(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_NAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_SURNAME)));
        student.setSchool(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_SCHOOL)));
        student.setRace(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_CAREER)));
        student.setLevel(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_LEVEL)));
        student.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_PHONE)));
        student.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_EMAIL)));
        return student;
    }
    public void removeElement(String id)
    {
        final String ARGS []= {id};
        this.sqLiteDatabase.delete(Schema.TABLE_STUDENT, Schema._ID + "=?", ARGS);
    }
    public void removeElement()
    {
        this.sqLiteDatabase.execSQL(Schema.DELETE_TABLE_STUDENT);
        this.sqLiteDatabase.execSQL(Schema.CREATE_TABLE_STUDENT);
    }
}
