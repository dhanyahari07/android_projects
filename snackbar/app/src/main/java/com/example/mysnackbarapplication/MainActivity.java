package com.example.mysnackbarapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbar();
            }
        });
    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "This is a Snackbar", Snackbar.LENGTH_INDEFINITE)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Undo successful", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                })
                .setActionTextColor(Color.RED);
       View snackView = snackbar.getView();
       TextView textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
       textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}