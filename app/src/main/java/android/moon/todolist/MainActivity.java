package android.moon.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextInputEditText in;
    ArrayList<String> str_list = new ArrayList<String>();
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = findViewById(R.id.input);
        adapter = new ListAdapter(str_list);
        adapter.setOnItemClickListener(new ListAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(View v, int position) {
                new AlertDialog.Builder(MainActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setMessage(str_list.get(position) + " 를 삭제하시겠습니까?")     // 제목 부분 (직접 작성)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getApplicationContext(), "확인 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getApplicationContext(), "취소 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            }
                        })
                        .show();
            }
        });
    }
    public void enterClick(View v){
        String str = in.getText().toString();
        str_list.add(str);
        RecyclerView viewlist = findViewById(R.id.list);
        viewlist.setLayoutManager(new LinearLayoutManager(this));
        viewlist.setAdapter(adapter);
    }
}