package com.example.dod.applicationnaso;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Patients extends AppCompatActivity {
     private RecyclerView patientrecycler;
    private RecyclerView.Adapter pList;
    private RecyclerView.LayoutManager layoutManager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        patientrecycler = (RecyclerView) findViewById(R.id.patient_recycler_view);
        patientrecycler.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        patientrecycler.setLayoutManager(layoutManager1);
        List<String> input = new ArrayList<>();

        for (int i=0;i<20;i++){
            input.add("(21/10/2017)" );

        }

        pList = new MyAdapter(input);
        patientrecycler.setAdapter(pList);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patients.this, AddPatient.class));
            }
        });
    }

}
