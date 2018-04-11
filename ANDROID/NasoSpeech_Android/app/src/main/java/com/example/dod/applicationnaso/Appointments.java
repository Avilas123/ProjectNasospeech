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

public class Appointments extends AppCompatActivity {


    private RecyclerView appointrecycler;
    private RecyclerView.Adapter aList;
    private RecyclerView.LayoutManager layoutManagerappoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Appointments");


        appointrecycler = (RecyclerView) findViewById(R.id.appoint_recycler_view);
        appointrecycler.setHasFixedSize(true);
        layoutManagerappoint = new LinearLayoutManager(this);
        appointrecycler.setLayoutManager(layoutManagerappoint);
        List<String> input = new ArrayList<>();

        for (int i=0;i<20;i++){
            input.add("(21/10/2017)" );

        }

        aList = new MyAdapter(input);
        appointrecycler.setAdapter(aList);

        FloatingActionButton fablist = (FloatingActionButton) findViewById(R.id.fabappoint);
        fablist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Appointments.this, AddAppointment.class));
            }
        });
    }

}
