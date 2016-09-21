package com.example.ranalucky.retrofitexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ranalucky.retrofitexample.model.ResponseModel;
import com.example.ranalucky.retrofitexample.model.Student;
import com.example.ranalucky.retrofitexample.services.ApiArrayInterface;
import com.example.ranalucky.retrofitexample.services.ApiClient;
import com.example.ranalucky.retrofitexample.services.ApiObjectInterface;
import com.example.ranalucky.retrofitexample.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button,button3,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setLayout();



        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // apiCallRequest();
                apiArrayCallRequest();

            }
        });
}

    private void setLayout() {

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*calling json object api */
                apiObjectCallRequest();

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*calling json array api */
                apiArrayCallRequest();

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*uploading file multipart  api  */

                Intent uploadImageMultipart = new Intent(getApplicationContext(),MutlitpartFileUploadActivity.class);
                startActivity(uploadImageMultipart);
            }
        });


    }

    private void apiObjectCallRequest() {
        ApiObjectInterface objectApiService = ApiClient.getClient().create(ApiObjectInterface.class);
        final Call<Student> call =  objectApiService.getStudentDetails();

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                final TextView textView = (TextView) findViewById(R.id.tv);
                textView.setText("Student Name: - "+new Gson().toJson(response.body()));
            }
            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                final TextView textView = (TextView) findViewById(R.id.tv);
                textView.setText("Something went wrong: " + t.getMessage());
            }
        });



    }

    public void apiArrayCallRequest() {

        ApiArrayInterface arrayService = ApiClient.getClient().create(ApiArrayInterface.class);

        Call<List<Student>> call = arrayService.getStudentDetails();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {
                    List<Student> StudentData = response.body();
                    for (int i = 0; i < StudentData.size(); i++) {

                        final TextView textView = (TextView) findViewById(R.id.tv);
                        textView.setText("Student Array Result: " + new Gson().toJson(response.body())+"");

                        /*if (i == 0) {
                            text_id_1.setText("StudentId  :  " + StudentData.get(i).getStudentId());
                            text_name_1.setText("StudentName  :  " + StudentData.get(i).getStudentName());
                            text_marks_1.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        } else if (i == 1) {
                            text_id_2.setText("StudentId  :  " + StudentData.get(i).getStudentId());
                            text_name_2.setText("StudentName  :  " + StudentData.get(i).getStudentName());
                            text_marks_2.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        }*/
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                final TextView textView = (TextView) findViewById(R.id.tv);
                textView.setText("Something went wrong: " + t.getMessage());
            }
    });
    }
}
