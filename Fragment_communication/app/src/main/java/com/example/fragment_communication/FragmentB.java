package com.example.fragment_communication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentB extends Fragment {

    private FragmentBListener listener;
    private EditText editText;
    private Button buttonOk;

    public interface FragmentBListener {
        void onInputBSent(CharSequence input);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_b, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputBSent(input);
            }
        });

        return v;
    }
    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener) {
            listener = (FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}