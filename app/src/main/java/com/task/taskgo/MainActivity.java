package com.task.taskgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String json_string;
    ListViewAdapter listViewAdapter;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundTask().execute();

        listView = findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(this,R.layout.rowelements);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,DisplayEmpDetails.class);
                String str = json_string;
                intent.putExtra("json",str);
                startActivity(intent);
            }
        });
    }

    private class BackgroundTask extends AsyncTask<String, String, String> {
        HttpURLConnection connection;
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                url = new URL("http://dummy.restapiexample.com/api/v1/employees");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();

            }
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = connection.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return (stringBuilder.toString());
                } else {
                    return "unsuccessful";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                connection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            json_string =s;

            try {

                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");

                int count = 0;
                String name,id,age,salary;
                while(count<jsonArray.length()){
                    JSONObject object =jsonArray.getJSONObject(count);
                    name = object.getString("employee_name");
                    id = object.getString("id");
                    age = object.getString("employee_age");
                    salary = object.getString("employee_salary");
                    Employee employee = new Employee(name,id,age,salary);
                    listViewAdapter.add(employee);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}