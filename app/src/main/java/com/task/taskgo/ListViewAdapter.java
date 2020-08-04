package com.task.taskgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListViewAdapter extends ArrayAdapter {
 List list = new ArrayList();

    public ListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(@Nullable Employee object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        EmployeeHolder employeeHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.rowelements,parent,false);

            employeeHolder =new EmployeeHolder();
            employeeHolder.name=(TextView)row.findViewById(R.id.ename);
            employeeHolder.id=(TextView)row.findViewById(R.id.eid);
            employeeHolder.age=(TextView)row.findViewById(R.id.eage);
            employeeHolder.salary=(TextView)row.findViewById(R.id.esalary);
            row.setTag(employeeHolder);
        }
        else{
            employeeHolder = (EmployeeHolder)row.getTag();
        }
        Employee employee = (Employee)this.getItem(position);
        employeeHolder.name.setText(" Employee Name: "+employee.geteName());
        employeeHolder.id.setText(" Employee ID: " +employee.geteId());
        employeeHolder.age.setText(" Employee age: "+employee.geteAge());
        employeeHolder.salary.setText(" Employee Salary: "+employee.geteSalary());

        return row;
    }

    static class EmployeeHolder{
        TextView name,id,age,salary;
    }
}
