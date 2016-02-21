package tomas.com.sysdist.models.data_base;

import android.provider.BaseColumns;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Schema implements BaseColumns
{
    //Tables
    public static final String TABLE_IP = "ip";
    public static final String TABLE_TEACHER = "teacher";
    public static final String TABLE_STUDENT = "student";

    //Columns
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_SCHOOL = "school";
    public static final String COLUMN_CAREER = "career";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IP = "octetos";
    public static final String COLUMN_NULL = null;
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_ERROR_SERVER = "error";

    //CREATE TABLE
    protected static final String CREATE_TABLE_IP= "CREATE TABLE " + TABLE_IP + "("+
                                                    BaseColumns._ID + " INTEGER PRIMARY KEY, "+
                                                    COLUMN_STATUS + " TEXT, "+
                                                    COLUMN_ERROR_SERVER + " TEXT, "+
                                                    COLUMN_IP + " TEXT);";
    protected static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("+
                                                            BaseColumns._ID + " INTEGER PRIMARY KEY, "+
                                                            COLUMN_NAME + " TEXT, " +
                                                            COLUMN_SURNAME + " TEXT, "+
                                                            COLUMN_SCHOOL + " TEXT, "+
                                                            COLUMN_CAREER + " TEXT, "+
                                                            COLUMN_LEVEL + " TEXT, "+
                                                            COLUMN_PHONE + " TEXT, "+
                                                            COLUMN_EMAIL + " TEXT );";
    protected static final String CREATE_TABLE_TEACHER ="CREATE TABLE " + TABLE_TEACHER + "("+
                                                            BaseColumns._ID + " INTEGER PRIMARY KEY, "+
                                                            COLUMN_NAME + " TEXT, " +
                                                            COLUMN_SURNAME + " TEXT, "+
                                                            COLUMN_CAREER + " TEXT, "+
                                                            COLUMN_PHONE + " TEXT, "+
                                                            COLUMN_EMAIL + " TEXT );";
    //DELETE TABLE
    protected  static final String DELETE_TABLE_TEACHER = "DROP TABLE IF EXISTS "+ TABLE_TEACHER;
    protected  static final String DELETE_TABLE_STUDENT = "DROP TABLE IF EXISTS "+ TABLE_STUDENT;
    protected  static final String DELETE_TABLE_IP = "DROP TABLE IF EXIST "+ TABLE_IP;

    //COUNT
    protected  static final String COUNT_STUDENTS = "SELECT COUNT(*) AS "+COLUMN_NUMBER+" FROM "+TABLE_STUDENT;
    protected  static final String COUNT_TEACHER = "SELECT COUNT(*) AS "+COLUMN_NUMBER+" FROM "+TABLE_TEACHER;
    protected  static final String COUNT_IP = "SELECT COUNT(*) AS "+COLUMN_NUMBER+" FROM "+TABLE_IP;


}
