package tomas.com.sysdist.controller.view.fragments;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import android.support.design.widget.Snackbar;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.network.handle_sockets.HandleServicesSockets;
import tomas.com.sysdist.controller.view.Dialog.DialogMessage;
import tomas.com.sysdist.controller.view.activity.MainActivity;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.Student;

/**
 * Tomas Yussef Galicia Guzman
 */
public class StudentFragments extends Fragment implements View.OnClickListener
{
    private Animation animation;
    private ImageButton plus, returnB;
    private Sqlite_Manager sqlite_manager;
    private TextView number, name, surname, school, career, level, phone, email;
    private HandleServicesSockets handleServicesSockets;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.sqlite_manager = new Sqlite_Manager(this.getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
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
        this.plus = (ImageButton) view.findViewById(R.id.plusXMLfragment);
        this.returnB = (ImageButton) view.findViewById(R.id.returnXMLfragment);
        this.number = (TextView) view.findViewById(R.id.controlXML);
        this.name = (TextView) view.findViewById(R.id.nameXML);
        this.surname = (TextView) view.findViewById(R.id.last_nameXML);
        this.school = (TextView) view.findViewById(R.id.schoolXML);
        this.career = (TextView) view.findViewById(R.id.raceXML);
        this.level = (TextView) view.findViewById(R.id.levelXMLStudent);
        this.phone = (TextView) view.findViewById(R.id.phoneXML);
        this.email = (TextView) view.findViewById(R.id.emailXML);
        this.handleServicesSockets = new HandleServicesSockets();

    }
    private void processPlus(View view)
    {
        try
        {
            Student student = new Student();
            this.sqlite_manager.insertObjectsStudents(readData(student));
            Snackbar.make(view, "Se guardaron los datos. :) \n" +
                    "Pongame 100% :D ",Snackbar.LENGTH_LONG).show();
            this.services();
        }catch (NullPointerException err)
        {
            Snackbar.make(view, "No pueden haber ni un campo vacio. :( " +
                            "\n Pongame 100% :D", Snackbar.LENGTH_LONG).show();
        }catch (NumberFormatException err)
        {
            Snackbar.make(view, "Error numero de control no puede estar vacio. :( \n" +
                    "Pongame 100% :D",Snackbar.LENGTH_LONG).show();
        }
        catch (SQLiteConstraintException e)
        {
            Snackbar.make(view, "Error numero de control ya existe. :( \n" +
                    "Pongame 100% :D",Snackbar.LENGTH_LONG).show();
            Config config = this.sqlite_manager.readObjectIP();
            DialogMessage dialogMessage = new DialogMessage();
            dialogMessage.show(this.getFragmentManager(), "Dialog");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private Student readData(Student student) throws NullPointerException
    {
        student.setNumber(Long.parseLong(this.number.getText().toString()));
        student.setNameStudent(this.name.getText().toString());
        student.setLastName(this.surname.getText().toString());
        student.setSchool(this.school.getText().toString());
        student.setRace(this.career.getText().toString());
        student.setLevel(this.level.getText().toString());
        student.setPhone(this.phone.getText().toString());
        student.setEmail(this.email.getText().toString());
        return student;

    }
    private void services()
    {
        if(!this.handleServicesSockets.onStart(HandleServicesSockets.class,this.getActivity().getApplicationContext()))
            this.getActivity().startService(new Intent(this.getActivity().getApplicationContext(), HandleServicesSockets.class));
    }
}
