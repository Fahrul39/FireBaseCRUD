package com.example.firebasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addmusik extends AppCompatActivity {
    EditText judul, desc, purl;
    Button submit, back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_musik);

        judul = (EditText)findViewById(R.id.add_judul);
        desc = (EditText)findViewById(R.id.add_desc);
        purl = (EditText)findViewById(R.id.add_purl);

        back= (Button) findViewById(R.id.add_back);
        back.setOnClickListener((view)->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });
        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener((view)-> processinsert());

    }
    private void processinsert(){
        Map<String, Object> map = new HashMap<>();
        map.put("judul",judul.getText().toString());
        map.put("desc",desc.getText().toString());
        map.put("purl",purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("musik").push()
                .setValue(map)
                .addOnSuccessListener(aVoid -> {
                    judul.setText("");
                    desc.setText("");
                    purl.setText("");
                    Toast.makeText(getApplicationContext(),"Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(),"Terdapat Kesalahan!!",Toast.LENGTH_LONG).show());
    }
}

