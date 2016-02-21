package tomas.com.sysdist.controller.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdist.models.objects.ConstantObjects;
import tomas.com.sysdist.models.objects.Student;
import tomas.com.sysdist.models.objects.Teacher;



/**
 * Tomas Yussef Galicia Guzman
 */
public class AdapterList extends  RecyclerView.Adapter <AdapterList.AdapterObject> implements ConstantObjects
{
    private ArrayList<Student> listStudent;
    private ArrayList<Teacher> listTeacher;
    private boolean flag;
    private OnItemClickListener itemClick;
    public AdapterList(ArrayList<Student> list, boolean flag)
    {
        this.flag = flag;
        this.listStudent = list;
        this.listTeacher = null;

    }
    public AdapterList(ArrayList<Teacher> list )
    {
        this.flag = false;
        this.listTeacher = list;
        this.listStudent = null;

    }

    @Override
    public AdapterList.AdapterObject onCreateViewHolder(ViewGroup content, int viewType)
    {
        View view =LayoutInflater.from(content.getContext())
                                    .inflate(R.layout.layout_list, content, false);
        AdapterObject viewHolderStudent = null;
        if(this.listTeacher != null)
            viewHolderStudent = new AdapterObject(view, this, TEACHER);
        else if(this.listStudent != null)
            viewHolderStudent = new AdapterObject(view, this, STUDENTS);
        return viewHolderStudent;
    }
    @Override
    public void onBindViewHolder(final AdapterObject adapter,  int position)
    {
        if(this.flag) this.loadStudent(adapter, position);
        else this.loadTeacher(adapter, position);
    }
    @Override
    public int getItemCount()
    {
        int count = 0;
        try
        {
            if(this.flag) count =this.listStudent.size();
            else count = this.listTeacher.size();
        }catch (NullPointerException e)
        {
            this.listTeacher = new ArrayList<>();
            this.listStudent = new ArrayList<>();
            this.listStudent.add(new Student(0000000, "No hay datos...", "", "", "", "", "", ""));
            this.listTeacher.add(new Teacher(0000000,"No hay datos...","","","",""));
            if(this.flag) count =this.listStudent.size();
            else count = this.listTeacher.size();
        }finally {
            return count;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnClickItemListener(OnItemClickListener listener)
    {
        this.itemClick = listener;
    }
    public OnItemClickListener getOnClickItemListener()
    {
        return this.itemClick;
    }
    public void removerItem(int position, int constant)
    {
        if(constant == TEACHER)
        {
            if(position > this.listTeacher.size())return;
            this.listTeacher.remove(position);
            this.notifyItemRemoved(position);
        }else if(STUDENTS == constant)
        {
            if(position > this.listStudent.size())return;
            this.listStudent.remove(position);
            this.notifyItemRemoved(position);
        }
    }
    private void loadStudent(AdapterObject adapter, int position)
    {
        String var = this.listStudent.get(position).getNameStudent() +" "+this.listStudent.get(position).getLastName();
        adapter.level1.setText(String.valueOf(this.listStudent.get(position).getNumber()));
        adapter.level2.setText(var);
        var = this.listStudent.get(position).getRace() + " en el " + this.listStudent.get(position).getSchool() + " "+this.listStudent.get(position).getLevel();
        adapter.level3.setText(var);
        var = this.listStudent.get(position).getEmail() + " " + this.listStudent.get(position).getPhone();
        adapter.level4.setText(var);
        adapter.imageView.setImageResource(R.drawable.ic_face_black_48dp);
    }
    private void loadTeacher(AdapterObject adapter, int position)
    {
        String var = this.listTeacher.get(position).getNameTeacher() +" "+this.listTeacher.get(position).getLastName();
        adapter.level1.setText(String.valueOf(this.listTeacher.get(position).getNumber()));
        adapter.level2.setText(var);
        var = this.listTeacher.get(position).getRace();
        adapter.level3.setText(var);
        var = this.listTeacher.get(position).getEmail() + " " + this.listTeacher.get(position).getPhone();
        adapter.level4.setText(var);
        adapter.imageView.setImageResource(R.drawable.ic_android_black_48dp);
    }
    /*
    * Class Adapter.......
    * */
    public static class AdapterObject extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private AdapterList classAdapter;
        private int var;
        public TextView level1, level2, level3, level4;
        public ImageView imageView;
        public AdapterObject(View view, AdapterList x, int var)
        {
            super(view);
            this.classAdapter =  x;
            this.var = var;
            this.level1 = (TextView) view.findViewById(R.id.level1XML);
            this.level2 = (TextView) view.findViewById(R.id.level2XML);
            this.level3 = (TextView) view.findViewById(R.id.level3XML);
            this.level4 = (TextView) view.findViewById(R.id.level4XML);
            this.imageView = (ImageView) view.findViewById(R.id.imageXML);
            this.imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view)
        {
            OnItemClickListener listener = classAdapter.getOnClickItemListener();
            listener.onItecmClick(this, view, this.getPosition(), var);
        }
    }
}