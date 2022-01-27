package android.moon.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { //현재시간을 조회해서 00시가 넘어가면 앱을 실행할떄 테이블 초기화
    TextInputEditText in;
    ArrayList<String> str_list = new ArrayList<String>();
    ListAdapter adapter;
    RecyclerView viewlist;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db;
        helper = new DBHelper(MainActivity.this, "todo.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);
        String [] temp = helper.getResult();
        System.out.println("데이터 베이스: " +temp);
        for(int i = 0;temp.length > i; i++){
            str_list.add(temp[i]);
        }
        in = findViewById(R.id.input);
        adapter = new ListAdapter(str_list);
        viewlist = findViewById(R.id.list);
        viewlist.setLayoutManager(new LinearLayoutManager(this));
        viewlist.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(View v, int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(str_list.get(position) + " 를 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                String str = str_list.get(position);
                                helper.delete(str);
                                str_list.remove(position);
                                viewlist.setAdapter(adapter);
                                Toast.makeText(getApplicationContext(), "삭제하였습니다", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getApplicationContext(), "취소 누름", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
    public void btnClick(View v){
        String str = in.getText().toString();
        str_list.add(str);
        helper.insert(str);
        viewlist.setLayoutManager(new LinearLayoutManager(this));
        viewlist.setAdapter(adapter);
        in.setText("");
    }
}