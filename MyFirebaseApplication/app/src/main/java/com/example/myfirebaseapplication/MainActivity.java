package com.example.myfirebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText name,position;
    Button submit,update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.edit_name);
        position=findViewById(R.id.edit_position);
        submit=findViewById(R.id.btn_submit);
        update=findViewById(R.id.btn_update);
        delete=findViewById(R.id.btn_delete);
        DAOEmployee dao=new DAOEmployee();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Employee emp=new Employee(name.getText().toString(),position.getText().toString());

                   dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Message","Record is inserted");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message",e.getMessage());
                    }
                });

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("position",position.getText().toString());
                dao.update("-Mn-Z-3Ufnn5MesjYtrZ",hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Message","Record is updated");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message",e.getMessage());
                    }
                });

            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dao.remove("-Mn-a_yA1gf1-KdRn7eG").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Message","Record is removed");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message",e.getMessage());
                    }
                });


            }
        });

    }
}