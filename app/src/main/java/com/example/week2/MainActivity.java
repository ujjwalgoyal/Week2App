package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String MESSAGE= "message";
    private static int PIC_ID = 123;
    EditText message;
    TextView title;
    TextView reply;
    ImageView profilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message= (EditText) findViewById(R.id.editText1);
        title= (TextView) findViewById(R.id.textView1);
        reply= (TextView) findViewById(R.id.textView2);
        profilePicture= (ImageView) findViewById(R.id.imageView);

        Bundle bundle= getIntent().getExtras();
        if(bundle!= null){
            title.setText("Reply Received");
            reply.setText(bundle.getString(Activity2.REPLY));
        }

    }
    //Explicit intent demo
    public void sendMessage(View view){
        Intent intent= new Intent(this, Activity2.class);
        String msg= message.getText().toString();
        intent.putExtra(MESSAGE, msg);
        startActivity(intent);

    }

    //Implicit intent demo
    public void performAction(View view){
        switch(view.getId()){
            case R.id.call:
                Intent call= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9876508271"));
                startActivity(call);
                break;

            case R.id.camera:
                Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, PIC_ID);
                break;

            case R.id.email:
                Intent i3= new Intent(Intent.ACTION_SEND);
                i3.putExtra(Intent.EXTRA_EMAIL, "lavish.bansal05@gmail.com");
                i3.putExtra(Intent.EXTRA_SUBJECT, "Week 2 of training completed.");
                i3.putExtra(Intent.EXTRA_TEXT, "Get ready for the next week.");
                i3.setType("message/rfc822");
                //Forcing an app chooser
                startActivity(Intent.createChooser(i3, "Choose an Email client :"));
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PIC_ID){
            Bitmap photo = (Bitmap)data.getExtras()
                    .get("data");
            profilePicture.setImageBitmap(photo);
        }
    }
}