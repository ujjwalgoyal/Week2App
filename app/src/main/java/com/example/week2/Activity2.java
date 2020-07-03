package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView textView;
    EditText editText;
    public static String REPLY= "reply";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        textView = (TextView) findViewById(R.id.textView2);
        editText= (EditText) findViewById(R.id.editText1);
        Bundle bundle= getIntent().getExtras();
        textView.setText(bundle.getString(MainActivity.MESSAGE));
    }

    public void sendReply(View view){
        String reply= editText.getText().toString();
        Intent intent= new Intent(this, MainActivity.class);
        intent.putExtra(REPLY, reply);
        Activity2.this.startActivity(intent);
    }


}