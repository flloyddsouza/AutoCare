package com.example.autoCare.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.autoCare.Model.Garage;
import com.example.autoCare.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Second extends Fragment {

    DatabaseReference myRef;
    private TextView textView;
    public Second() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        textView=view.findViewById(R.id.tv);
        textView.setText("This is Second");

        // Write a message to the database
        Garage garage = new Garage("Auto","26 Batesford Road","25.1000","84.7895","123456789" );
        myRef = FirebaseDatabase.getInstance().getReference().child("Garage");
        myRef.push().setValue(garage);

        return view;
    }
}
