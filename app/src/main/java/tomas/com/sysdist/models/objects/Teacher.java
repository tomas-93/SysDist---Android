package tomas.com.sysdist.models.objects;


/**
 * Tomas Yussef Galicia Guzman
 */
public class Teacher
{
    private long number;
    private String nameTeacher, lastName, race, email, phone;
    public Teacher(long number, String name, String lastName, String race,
                    String email, String phone)
    {
        this.number = number;
        this.phone = phone;
        this.nameTeacher = name;
        this.lastName = lastName;
        this.race = race;
        this.email = email;
    }
    public Teacher()
    {

    }
    public void setNumber(Long number)
    {
        this.number = number;
    }
    public void setNameTeacher(String s)
    {
        this.nameTeacher = s;
    }
    public void setLastName(String s)
    {
        this.lastName = s;
    }
    public void setRace(String s)
    {
        this.race = s;
    }
    public void setPhone(String s)
    {
        this.phone = s;
    }
    public void setEmail(String s)
    {
        this.email = s;
    }
    public long getNumber()
    {
        return this.number;
    }
    public String getNameTeacher()
    {
        return this.nameTeacher;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getRace()
    {
        return this.race;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getEmail()
    {
        return this.email;
    }
}
