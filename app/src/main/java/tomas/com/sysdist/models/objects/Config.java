package tomas.com.sysdist.models.objects;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Config
{
    private String ip, status, err;
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    public String getIp()
    {
        return this.ip;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return this.status;
    }
    public void setErro(String e)
    {
        this.err = e;
    }
    public String getErr()
    {
        return this.err;
    }

}
