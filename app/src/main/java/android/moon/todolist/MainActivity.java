package android.moon.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = findViewById(R.id.input);
    }
    public void enterClick(View v){
        String str = in.getText().toString();
        Toast.makeText(getApplicationContext(), str,Toast.LENGTH_LONG).show();
        TextView list = findViewById(R.id.list);
        list.setText(str);
    }
}