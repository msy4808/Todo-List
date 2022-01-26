package android.moon.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { //쉐이드프리퍼런스가 아닌 DB사용예정
    TextInputEditText in;
    ArrayList<String> str_list = new ArrayList<String>();
    ListAdapter adapter;
    RecyclerView viewlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = findViewById(R.id.input);
        adapter = new ListAdapter(str_list);
        adapter.setOnItemClickListener(new ListAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(View v, int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(str_list.get(position) + " 를 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
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
        viewlist = findViewById(R.id.list);
        viewlist.setLayoutManager(new LinearLayoutManager(this));
        viewlist.setAdapter(adapter);
        in.setText("");
    }
}