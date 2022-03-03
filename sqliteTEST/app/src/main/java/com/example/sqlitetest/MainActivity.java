package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    private TextView textView_data;
    private SQLiteDatabase db;
    private List<Student> StudentList = new ArrayList<>();

    private final static String TAG = "test";
    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这一句话指定了可以去哪个布局文件中找id
        setContentView(R.layout.activity_main);

        //依靠DatabaseHelper的构造函数创建数据库
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db", null, 1);
        db = dbHelper.getWritableDatabase();
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MyAdapter(StudentList);
        recyclerView.setAdapter(adapter);
        initView();
        findData();

    }

    private void initView() {
        // 根据setContentView(R.layout.activity_main)方法指定的布局中的id初始化对象
        // 8个按钮
        btn1 = findViewById(R.id.add);
        btn2 = findViewById(R.id.delete);
        btn3 = findViewById(R.id.update);
        btn4 = findViewById(R.id.find);

        editText1 = findViewById(R.id.number_text);
        editText2 = findViewById(R.id.studentName_text);
        editText3 = findViewById(R.id.sex_text);
        editText4 = findViewById(R.id.grade_text);


        // 5个输入框（4个输入框需要在下面的onClick()方法中用去获取输入的文本，所以在全局进行声明）
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }
    public  void findData(){
        StudentList.clear();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        //利用游标遍历所有数据对象（for循环中，建议使用StringBuilder替代String）
        //为了显示全部，把所有对象连接起来，放到TextView中
        while (cursor.moveToNext()) {
            Student x = new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            StudentList.add(x);
        }
        cursor.close(); // 关闭游标，释放资源

        adapter.setData(StudentList);
    }
    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //插入数据按钮
            case R.id.add:
                //创建存放数据的ContentValues对象
                ContentValues values = new ContentValues();
                values.put("number", String.valueOf(editText1.getText()));
                values.put("studentName", String.valueOf(editText2.getText()));
                values.put("sex",String.valueOf(editText3.getText()));
                values.put("grade",String.valueOf(editText4.getText()));
                //数据库执行插入命令
                db.insert("user", null, values);
                findData();
                break;
            //删除数据按钮
            case R.id.delete:
                db.delete("user", "number=?", new String[]{String.valueOf(editText1.getText())});
                findData();
                break;
            //删除数据按钮后面的清除按钮

            //更新数据按钮
            case R.id.update:
                ContentValues values2 = new ContentValues();
                values2.put("grade", String.valueOf(editText4.getText()));
                String where = "number =" +String.valueOf(editText1.getText());
                db.update("user", values2, where, null);
                findData();
                break;

            //查询全部按钮
            case R.id.find:
                //创建游标对象
                Cursor cursor = db.query("user", null, null, null, null, null, null);
                //利用游标遍历所有数据对象（for循环中，建议使用StringBuilder替代String）
                //为了显示全部，把所有对象连接起来，放到TextView中
                while (cursor.moveToNext()) {
                    Log.i(TAG, "number "+ cursor.getInt(0)+" name "+cursor.getString(1)+" sex "+cursor.getString(2)+" grade "+cursor.getInt(3));
                }
                cursor.close(); // 关闭游标，释放资源
                break;

            default:
                break;
        }
    }

}