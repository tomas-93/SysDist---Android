package tomas.com.sysdist.controller.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdist.controller.network.handle_sockets.Services_Push;
import tomas.com.sysdist.controller.view.Dialog.DialogMessage;
import tomas.com.sysdist.controller.view.adapter.OnItemClickListener;
import tomas.com.sysdist.models.data_base.Sqlite_Manager;
import tomas.com.sysdist.models.objects.Config;
import tomas.com.sysdist.models.objects.ConstantObjects;
import tomas.com.sysdist.models.objects.Student;
import tomas.com.sysdist.models.objects.Teacher;
import tomas.com.sysdist.controller.view.adapter.ListenerAdapter;
import tomas.com.sysdist.controller.view.adapter.AdapterList;

/**
 * Tomas Yussef Galicia Guzman
 */
public class List extends AppCompatActivity implements View.OnClickListener, OnItemClickListener, Runnable, ConstantObjects
{
    private FloatingActionButton returnButton, setAdapter;
    private Animation anaimation;
    private boolean flag, flagThread;
    private AdapterList myAdapter;
    private Sqlite_Manager sqlite_manager;
    private Thread thread;
    private ArrayList<Student> listStudents;
    private ArrayList<Teacher> listTeachers;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);

        this.instaceComponents();

        this.setProcessAdapter();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.flagThread = false;
    }
    @Override
    public void onClick(View view)
    {
        if(this.returnButton.getId() == view.getId())
        {
            this.startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
        }else if(this.setAdapter.getId() == view.getId())
        {
            this.setAdapter.startAnimation(this.anaimation);
            this.setProcessAdapter();
            if(this.flag)
                this.flag = false;
            else this.flag = true;
        }
    }
    @Override
    public void onItecmClick(final AdapterList.AdapterObject adapterObject , View view, final int position, final int list)
    {
        adapterObject.imageView.setImageResource(R.drawable.ic_delete_black_48dp);
        Snackbar.make(view, "Deseas Eliminar Elemento", Snackbar.LENGTH_LONG)
                .setActionTextColor(view.getResources().getColor(R.color.colorAccent))
                .setAction("Si?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        myAdapter.removerItem(position, list);
                    }
                }).show();
    }
    @Override
    public void run()
    {
        while(this.flagThread)
        {
            try
            {
                synchronized (this)
                {
                    int countStudents = this.sqlite_manager.countDataStudent();
                    int countTeacher = this.sqlite_manager.countDataTeacher();
                    this.listTeachers = this.sqlite_manager.readObjectTeacher();
                    this.listStudents = this.sqlite_manager.readObjectStudents();
                    if(this.flag && countTeacher > listTeachers.size() || countTeacher < this.listTeachers.size())
                    {
                        this.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                setProcessAdapter();
                            }
                        });
                    }else if(!this.flag && countStudents > this.listTeachers.size()|| countStudents < this.listStudents.size())
                    {
                        this.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                setProcessAdapter();
                            }
                        });
                    }
                }
            }catch (NullPointerException e)
            {
            }catch (Exception err)
            {
            }
        }
    }
    private void instaceComponents()
    {
        this.returnButton = (FloatingActionButton) this.findViewById(R.id.returnXML_List);
        this.setAdapter = (FloatingActionButton) this.findViewById(R.id.setListXML);
        this.returnButton.setOnClickListener(this);
        this.setAdapter.setOnClickListener(this);

        this.sqlite_manager = new Sqlite_Manager(this.getApplicationContext());

        this.listTeachers = this.sqlite_manager.readObjectTeacher();
        this.listStudents = this.sqlite_manager.readObjectStudents();

        this.anaimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.animator_button);
        this.setProcessAdapter();
        this.flagThread = true;
        this.thread = new Thread(this);
        this.thread.start();
    }
    private void setProcessAdapter()
    {
        if(this.flag)
        {
            this.myAdapter = new AdapterList(this.listTeachers);
            this.myAdapter.setOnClickItemListener(this);
            this.processRecyclerView(this.myAdapter);
        }else
        {
            this.myAdapter = new AdapterList(this.listStudents, true);
            this.myAdapter.setOnClickItemListener(this);
            this.processRecyclerView(this.myAdapter);
        }
    }

    private void processRecyclerView(RecyclerView.Adapter myAdapter)
    {
        recyclerView = (RecyclerView) this.findViewById(R.id.list_card_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollListener(new ListenerAdapter()
        {
            @Override
            public void show()
            {
                returnButton.animate().translationY(0)
                            .setInterpolator(new DecelerateInterpolator(2))
                            .start();

                setAdapter.animate()
                          .translationY(0)
                          .setInterpolator(new DecelerateInterpolator(2))
                          .start();
            }

            @Override
            public void hide()
            {
                returnButton.animate()
                            .translationY(300)
                            .setInterpolator(new DecelerateInterpolator(2))
                            .start();

                setAdapter.animate()
                          .translationY(300)
                          .setInterpolator(new DecelerateInterpolator(2))
                          .start();
            }
        });
        RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myManager);
        recyclerView.setAdapter(myAdapter);
    }


}
