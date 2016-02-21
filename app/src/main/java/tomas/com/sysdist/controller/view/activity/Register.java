package tomas.com.sysdist.controller.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.view.Dialog.DialogMessage;
import tomas.com.sysdist.controller.view.fragments.StudentFragments;
import tomas.com.sysdist.controller.view.fragments.TeacherFragments;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;

/**
 * Tomas Yussef Galicia Guzman
 */
public class Register extends AppCompatActivity
{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private final StudentFragments studentFragment = new StudentFragments();
    private final TeacherFragments teacher = new TeacherFragments();
    private boolean flagTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register);
        this.manager = this.getFragmentManager();
        this.transaction = this.manager.beginTransaction();
        if(this.flagTeacher)
        {
            this.transaction.replace(R.id.fragment_content_component, this.teacher);
            this.transaction.commit();
        }else
        {
            this.transaction.replace(R.id.fragment_content_component, this.studentFragment);
            this.transaction.commit();
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
    public void onClickStudent(View view)
    {
        if(!this.studentFragment.isAdded())
        {
            this.flagTeacher = false;
            this.manager = this.getFragmentManager();
            this.transaction = this.manager.beginTransaction();
            this.transaction.replace(R.id.fragment_content_component, this.studentFragment);
            this.transaction.commit();
        }
    }

    public void onClickTeacher(View view)
    {
        if(!this.teacher.isAdded())
        {
            this.flagTeacher = true;
            this.manager = this.getFragmentManager();
            this.transaction = this.manager.beginTransaction();
            this.transaction.replace(R.id.fragment_content_component, this.teacher);
            this.transaction.commit();
        }
    }


}
