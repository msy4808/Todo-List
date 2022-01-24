package android.moon.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextInputEditText in;
    ArrayList<String> str_list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = findViewById(R.id.input);
    }
    public void enterClick(View v){
        String str = in.getText().toString();
        Toast.makeText(getApplicationContext(), str,Toast.LENGTH_LONG).show();
        str_list.add(str);
        RecyclerView viewlist = findViewById(R.id.list);
        viewlist.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter(str_list);
        viewlist.setAdapter(adapter);

    }
}