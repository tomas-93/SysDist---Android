package tomas.com.sysdist.controller.view.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.network.handle_sockets.HandleServicesSockets;
import tomas.com.sysdist.controller.view.Dialog.DialogMessage;
import tomas.com.sysdist.controller.view.activity.MainActivity;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.Student;
import tomas.com.sysdist.models.objects.Teacher;

/**
 * Tomas Yussef Galicia Guzman
 */
public class TeacherFragments extends Fragment implements View.OnClickListener
{
    private Animation animation;
    private ImageButton plus, returnB;
    private Sqlite_Manager sqlite_manager;
    private TextView number, name, surname, career, phone, email;
    private HandleServicesSockets handleServicesSockets;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.sqlite_manager = new Sqlite_Manager(this.getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        this.instance(view);
        this.returnB.setOnClickListener(this);
        this.plus.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view)
    {
        if (view.getId() == this.plus.getId())
        {
            this.plus.startAnimation(this.animation);
            this.processPlus(view);
        } else
        {
            this.returnB.startAnimation(this.animation);
            this.startActivity(new Intent(this.getActivity().getApplicationContext(), MainActivity.class));
        }
    }
    private void instance(View view)
    {
        this.animation = AnimationUtils.loadAnimation(this.getActivity().getApplicationContext(),
                R.anim.animator_button);
        this.plus = (ImageButton) view.findViewById(R.id.plusXMLfragmentTeacher);
        this.returnB = (ImageButton) view.findViewById(R.id.returnXMLfragmentTeacher);
        this.number = (TextView) view.findViewById(R.id.controlXMLteacher);
        this.name = (TextView) view.findViewById(R.id.nameXMLteacher);
        this.surname = (TextView) view.findViewById(R.id.last_nameXMLteacher);
        this.career = (TextView) view.findViewById(R.id.raceXMLteacher);
        this.phone = (TextView) view.findViewById(R.id.phoneXMLteacher);
        this.email = (TextView) view.findViewById(R.id.emailXMLteacher);
        this.handleServicesSockets = new HandleServicesSockets();

    }
    private void processPlus(View view)
    {
        try
        {
            Teacher teacher = new Teacher();
            this.sqlite_manager.inserObjectsTeacher(this.readData(teacher));
            Snackbar.make(view, "Se guardaron los datos. :) \n" +
                    " Pongame 100% :D",Snackbar.LENGTH_LONG).show();
            this.services();
        }catch (NullPointerException err)
        {
            Snackbar.make(view, "No pueden haber ni un campo vacio. :( \n Pongame 100% :D", Snackbar.LENGTH_LONG).show();
            err.printStackTrace();
        }catch (NumberFormatException err)
        {
            Snackbar.make(view, "Error numero de control no puede estar vacio. :( \n" +
                    " Pongame 100% :D",Snackbar.LENGTH_LONG).show();
        }
        catch (SQLiteConstraintException e)
        {
            Snackbar.make(view, "Error numero de control ya existe. :( \n" +
                    " Pongame 100% :D",Snackbar.LENGTH_LONG).show();
            DialogMessage dialogMessage = new DialogMessage();
            dialogMessage.show(this.getFragmentManager(), "Dialog");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private Teacher readData(Teacher teacher)
    {
        teacher.setNumber(Long.parseLong(this.number.getText().toString()));
        teacher.setNameTeacher(this.name.getText().toString());
        teacher.setLastName(this.surname.getText().toString());
        teacher.setRace(this.career.getText().toString());
        teacher.setPhone(this.phone.getText().toString());
        teacher.setEmail(this.email.getText().toString());
        return teacher;

    }
    private void services()
    {
        if(!this.handleServicesSockets.onStart(HandleServicesSockets.class,this.getActivity().getApplicationContext()))
            this.getActivity().startService(new Intent(this.getActivity().getApplicationContext(), HandleServicesSockets.class));
    }
}
