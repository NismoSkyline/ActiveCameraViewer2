package com.projects.thirtyseven.activecameraviewer2;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    Spinner spinner;
    ImageView statusView;
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> normalCameraNames;
    ArrayList<Camera> camerasArrayList;
    Camera camera;
    Camera changedCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = getIntent();
        normalCameraNames = intent.getStringArrayListExtra("normalCameraNames");

        init();
        setAdapterOtions();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int cameraNumber, long id) {
                textView.setText(String.valueOf(cameraNumber + 1));
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Выбрана камера: " + Integer.valueOf(cameraNumber + 1), Toast.LENGTH_LONG);
                toast.show();
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        changedCamera = dataSnapshot.getValue(Camera.class);
                        changedCamera.getCameraName();
                        if (changedCamera.getCameraName() == Integer.valueOf(cameraNumber + 1))
                            changeStatus();
                        else ;
                        Toast.makeText(ViewerActivity.this, "Изменён статус камеры: " +
                                String.valueOf(changedCamera.getCameraName()), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void changeStatus() {
        if (changedCamera.getStatus() == 1) statusView.setImageResource(R.drawable.grey_status);
        else if (changedCamera.getStatus() == 2)
            statusView.setImageResource(R.drawable.yellow_status);
        else if (changedCamera.getStatus() == 3) statusView.setImageResource(R.drawable.red_status);
    }

    private void setAdapterOtions() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_simple_spinner_item, normalCameraNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Cameras");
        spinner.setSelection(0);
    }

    private void init() {
        textView = (TextView) findViewById(R.id.textView);
        spinner = (Spinner) findViewById(R.id.spinner);
        statusView = (ImageView) findViewById(R.id.statusView);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
        camera = new Camera();
        camerasArrayList = new ArrayList<>();
    }
}
