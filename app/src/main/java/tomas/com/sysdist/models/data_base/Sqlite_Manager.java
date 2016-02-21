package tomas.com.sysdist.models.data_base;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;
import tomas.com.sysdist.models.objects.Student;
import tomas.com.sysdist.models.objects.Teacher;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Sqlite_Manager implements ConstantObjects
{
    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;
    private Table_IP table_ip;
    private Table_Student table_student;
    private Table_Teacher table_teacher;
    private final String TAG = "Sqlite_Manager";

    public Sqlite_Manager(Context context)
    {
        this.dataBase = new DataBase(context);
        this.sqLiteDatabase = this.dataBase.getWritableDatabase();
        this.table_ip = new Table_IP(this.sqLiteDatabase);
        this.table_teacher = new Table_Teacher(this.sqLiteDatabase);
        this.table_student = new Table_Student(this.sqLiteDatabase);
    }
    public String readDataInJSON(int objects) throws JSONException, SQLiteException
    {
        String object = null;
        if(objects == STUDENTS)
        {
            object = this.readObjectStudentsInJSON();

        }else if(objects == TEACHER)
        {
            object = this.readObjectTeacherInJSON();
        }
        return object;
    }
    public int countDataTeacher()
    {
        Cursor cursor = this.sqLiteDatabase.rawQuery(Schema.COUNT_TEACHER, null);
        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndexOrThrow(Schema.COLUMN_NUMBER));
        cursor.close();
        return count;
    }
    public int countDataStudent()
    {
        Cursor cursor = this.sqLiteDatabase.rawQuery(Schema.COUNT_STUDENTS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndexOrThrow(Schema.COLUMN_NUMBER));
        cursor.close();
        return count;
    }
    public int countDataIP()
    {
        Cursor cursor = this.sqLiteDatabase.rawQuery(Schema.COUNT_IP, null);
        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndexOrThrow(Schema.COLUMN_NUMBER));
        cursor.close();
        return count;
    }
    public void insertObjectsIP(Config config)throws SQLiteException
    {
        try
        {
            final int id = 0;
            this.table_ip.insertIntoTableIp(id, config.getIp(), config.getStatus(), config.getErr());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void  insertObjectsStudents(Student student)throws SQLiteException, NullPointerException
    {
        if(student.getNameStudent().equals("") ||
              student.getLastName().equals("") ||
              student.getSchool().equals("") ||
              student.getRace().equals("") ||
              student.getLevel().equals("")||
              student.getPhone().equals("") ||
              student.getEmail().equals(""))throw new NullPointerException();
        else this.insertStudent(student);


    }
    public void inserObjectsTeacher(Teacher teacher)throws SQLiteException, NullPointerException
    {
        if(teacher.getNameTeacher().equals("") ||
               teacher.getLastName().equals("") ||
               teacher.getRace().equals("") ||
               teacher.getPhone().equals("") ||
               teacher.getEmail().equals(""))throw new NullPointerException();
        else this.insertTeacher(teacher);
    }
    public Config readObjectIP()throws SQLiteException
    {
        return this.table_ip.readDataIntoTableIp();
    }
    public ArrayList<Student> readObjectStudents()throws SQLiteException
    {
        ArrayList<Student> list = new ArrayList<>();
        Cursor cursor = this.table_student.readDataIntoTableSudent();
        cursor.moveToFirst();
        for(int count = 0; count < cursor.getCount(); count++)
        {
            list.add(this.table_student.readTheCursorStudent(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        if(list.size()<=0) list = null;
        return list;
    }
    public ArrayList<Teacher> readObjectTeacher() throws SQLiteException
    {
        ArrayList<Teacher> list = new ArrayList<>();
        Cursor cursor = this.table_teacher.readDataIntoTableTeacher();
        cursor.moveToFirst();
        for(int count = 0; count < cursor.getCount(); count++)
        {
            list.add(this.table_teacher.readTheCursorTeacher(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        if(list.size()<=0) list = null;
        return list;
    }
    /*
        JSON...{"students":{{"key": "value","key":"value"},
                            {"key": "value","key":"value"},
                            {"key": "value","key":"value"}}}
    */
    private String readObjectStudentsInJSON()
    {
        String json = null;
        try
        {
            ArrayList<Student> list = this.readObjectStudents();
            if(list != null)
            {
                json = "{\"students\":[";
                int count = 0;
                for(Student student: list)
                {
                    json += "{\"" + Schema._ID + "\":\"" + student.getNumber() + "\",";
                    json += "\"" + Schema.COLUMN_NAME + "\":\"" + student.getNameStudent() + "\",";
                    json += "\"" + Schema.COLUMN_SURNAME + "\":\"" + student.getLastName() + "\",";
                    json += "\"" + Schema.COLUMN_SCHOOL + "\":\"" + student.getSchool() + "\",";
                    json += "\"" + Schema.COLUMN_CAREER + "\":\"" + student.getRace() + "\",";
                    json += "\"" + Schema.COLUMN_LEVEL + "\":\"" + student.getLevel() + "\",";
                    json += "\"" + Schema.COLUMN_PHONE + "\":\"" + student.getPhone() + "\",";
                    json += "\"" + Schema.COLUMN_EMAIL + "\":\"" + student.getEmail() + "\"";
                    Log.e(String.valueOf(count),String.valueOf(list.size()));

                    if(count < list.size()-1)
                        json += "},";
                    else json += "}";
                    count++;
                }
                json += "]}";
                Log.e("", json);

            }
        }catch(CursorIndexOutOfBoundsException e)
        {}
        finally
        {
            return json;
        }
    }
    /*
        JSON...{"teacher":{{"key": "value","key":"value"},
                            {"key": "value","key":"value"},
                            {"key": "value","key":"value"}}}
    */
    public String readObjectTeacherInJSON()
    {
        String json = null;
        try
        {
            ArrayList<Teacher> list = this.readObjectTeacher();
            if(list != null)
            {
                json = "{\"teacher\":[";
                int count = 0;
                for(Teacher teacher: list)
                {
                    json += "{\""+Schema._ID+"\":\"" + teacher.getNumber()+ "\",";
                    json += "\""+Schema.COLUMN_NAME+"\":\"" + teacher.getNameTeacher()+ "\",";
                    json += "\""+Schema.COLUMN_SURNAME+"\":\"" + teacher.getLastName()+ "\",";
                    json += "\""+Schema.COLUMN_CAREER+"\":\"" + teacher.getRace()+ "\",";
                    json += "\""+Schema.COLUMN_PHONE+"\":\"" + teacher.getPhone()+ "\",";
                    json += "\""+Schema.COLUMN_EMAIL+"\":\"" + teacher.getEmail()+ "\"";
                    Log.e(String.valueOf(count),String.valueOf(list.size()));
                    if(count < list.size()-1)
                        json += "},";
                    else json += "}";
                    count++;
                }
                json += "]}";
                Log.e("", json);
            }
        }catch(CursorIndexOutOfBoundsException e)
        {}
        finally
        {
            return json;
        }
    }

    public void insertDataOfIntoTableTeacher(String json)
    {
        try
        {
            int countTeacher = this.countDataTeacher();
            JSONObject jsonObject = new JSONObject(json.replace("\n",""));
            JSONArray array = jsonObject.getJSONArray("Teacher");
            if(countTeacher < array.length())
            {
                this.deleteElement(TEACHER);
                for (int count = 0; count < array.length(); count++)
                {
                    JSONObject jsonObject1 = array.getJSONObject(count);
                    Teacher teacher = new Teacher();
                    teacher.setNumber(jsonObject1.getLong(Schema._ID));
                    teacher.setLastName(jsonObject1.getString(Schema.COLUMN_SURNAME));
                    teacher.setRace(jsonObject1.getString(Schema.COLUMN_CAREER));
                    teacher.setPhone(jsonObject1.getString(Schema.COLUMN_PHONE));
                    teacher.setEmail(jsonObject1.getString(Schema.COLUMN_EMAIL));
                    this.insertTeacher(teacher);
                }
            }
            /*

                    {"Teacher":[{"_id":"845","name":"xwh","last_name":"cdcb",
                    "r0ace":" CF nff","phone":"49575","email":"xavhre"}]}

             */
        }catch (JSONException e)
        {

        }catch (NullPointerException e)
        {
            Log.e("NullPointerException", "Posible referencia nulla del arrayJSON");
        }

    }
    public void insertDataOfIntoTableStudent(String json)
    {
        try
        {
            int countStudents = this.countDataStudent();
            JSONObject jsonObject = new JSONObject(json.replace("\n",""));
            JSONArray array = jsonObject.getJSONArray("Student");
            if(countStudents < array.length())
            {
                this.deleteElement(STUDENTS);
                for (int count = 0; count < array.length(); count++)
                {
                    JSONObject jsonObject1 = array.getJSONObject(count);
                    Student student = new Student();
                    student.setNumber(jsonObject1.getLong(Schema._ID));
                    student.setNameStudent(jsonObject1.getString(Schema.COLUMN_NAME));
                    student.setLastName(jsonObject1.getString(Schema.COLUMN_SURNAME));
                    student.setSchool(jsonObject1.getString(Schema.COLUMN_SCHOOL));
                    student.setRace(jsonObject1.getString(Schema.COLUMN_CAREER));
                    student.setLevel(jsonObject1.getString(Schema.COLUMN_LEVEL));
                    student.setPhone(jsonObject1.getString(Schema.COLUMN_PHONE));
                    student.setEmail(jsonObject1.getString(Schema.COLUMN_EMAIL));
                    this.insertStudent(student);
                }
            }
        }catch (JSONException e)
        {

        }catch (NullPointerException e)
        {
            Log.e("NullPointerException", "Posible referencia nulla");
        }
    }
    public void deleteElement(int in)
    {
        if(TEACHER == in)
        {
            this.table_teacher.removeElement();
        }else
        {
            this.table_student.removeElement();
        }
    }
    public void deleteElementsJSON(String json, int in)
    {
        if(TEACHER == in)
        {
            this.table_teacher.removeElement();
        }else if(STUDENTS == in)
        {
            this.table_student.removeElement();
        }
    }
    private void insertStudent(Student student)
    {
        this.table_student.insertIntoTableStudent(student.getNumber(),
                student.getNameStudent(),
                student.getLastName(),
                student.getSchool(),
                student.getRace(),
                student.getLevel(),
                student.getPhone(),
                student.getEmail());
    }
    private void insertTeacher(Teacher teacher)
    {
        this.table_teacher.insertIntoTableTeacher(teacher.getNumber(),
                teacher.getNameTeacher(),
                teacher.getLastName(),
                teacher.getRace(),
                teacher.getPhone(),
                teacher.getEmail());
    }

}
