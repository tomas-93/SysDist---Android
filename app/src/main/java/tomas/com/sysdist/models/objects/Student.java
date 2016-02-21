package tomas.com.sysdist.models.objects;

import android.util.Log;
/**
 * Tomas Yussef Galicia Guzman
 */
public class Student
{
    private long number;
    private String nameStudent, lastName, school, race, level, email, phone;
    public Student(long number, String name, String lastName, String race,
                    String school, String level, String email, String phone)
    {
        this.number = number;
        this.phone = phone;
        this.nameStudent = name;
        this.lastName = lastName;
        this.school = school;
        this.race = race;
        this.level = level;
        this.email = email;
    }
    public Student()
    {

    }
    public long getNumber()
    {
        return this.number;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getNameStudent()
    {
        return this.nameStudent;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getSchool()
    {
        return this.school;
    }
    public String getRace()
    {
        return this.race;
    }
    public String getLevel()
    {
        return this.level;
    }
    public String getEmail()
    {
        return this.email;
    }

    public void setNumber( Long number)
    {
         this.number = number;
    }
    public void setPhone(String s)
    {
         this.phone = s;
    }
    public void setNameStudent(String s)
    {
         this.nameStudent= s;
    }
    public void setLastName(String s)
    {
         this.lastName= s;
    }
    public void setSchool(String s)
    {
         this.school= s;
    }
    public void setRace(String s)
    {
         this.race= s;
    }
    public void setLevel(String s)
    {
         this.level= s;
    }
    public void setEmail(String s)
    {
         this.email= s;
    }
}
