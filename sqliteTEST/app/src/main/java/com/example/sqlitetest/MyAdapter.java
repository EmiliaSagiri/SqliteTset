package com.example.sqlitetest;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Student> mStudentLIst;

    public void setData(List<Student> studentList) {
        mStudentLIst = studentList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView number;
        private final TextView sex;
        private final TextView grade;
        View ljj;

        public ViewHolder(View view) {
            super(view);
            ljj=view;
            number = (TextView) view.findViewById(R.id.number_l);
            name = (TextView) view.findViewById(R.id.name_l);
            sex = (TextView) view.findViewById(R.id.sex_l);
            grade = (TextView) view.findViewById(R.id.grade_l);
        }
    }
    public int getItemViewType(int position) {
        return position;
    }
    public MyAdapter(List<Student> StudentLIst) {
        mStudentLIst = StudentLIst;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent
                , false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ljj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =holder.getAdapterPosition();
                Student student = mStudentLIst.get(position);
                Toast.makeText(view.getContext(),"嘿嘿a"+student.getNumber(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mStudentLIst.get(position);
        holder.number.setText(String.valueOf(student.getNumber()));
        holder.name.setText(student.getName());
        holder.sex.setText(student.getSex());
        holder.grade.setText(String.valueOf(student.getGrade()));


    }

    @Override
    public int getItemCount() {
        // TODO: Add for test
        return mStudentLIst.size();
    }
}


