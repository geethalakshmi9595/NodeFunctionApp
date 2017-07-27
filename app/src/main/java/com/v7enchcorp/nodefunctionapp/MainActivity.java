package com.v7enchcorp.nodefunctionapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText editText;
    TextView textView,textView1;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    CalendarView calendarView;
    private int year, month, day;
    String s1,s2,s3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
b1=(Button)findViewById(R.id.b77);
        textView1=(TextView)findViewById(R.id.text123);
        calendarView=(CalendarView)findViewById(R.id.calendarview12);
        textView = (TextView)findViewById(R.id.t11);
        editText = (EditText)findViewById(R.id.edi1);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                i1=i1+1;
                textView1.setText(+ i2 + " / " + i1 + " / " + i);


            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                int length = editText.length();
                String convert = String.valueOf(length);
                textView.setText(convert);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                s1 = editText.getText().toString();
                s2 = textView.getText().toString();
s3=textView1.getText().toString();


                if (editText.getText().toString().equals("") && textView.getText().toString().equals("") && textView1.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter some details", Toast.LENGTH_SHORT).show();
                }

                else {
                    insertme(s1, s2,s3);
                    Toast.makeText(MainActivity.this, "THANK YOU!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void insertme(final String s1, final String s2,final String s3) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Global_url.INSERT_DATA2, new Response.Listener<String>() {
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error)
            { }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", s1);
                params.put("count", s2);
params.put("date12",s3);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
