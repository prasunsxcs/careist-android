package com.example.invinciblesourav.careist;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class categories extends AppCompatActivity {
    Typeface tfavv;
    String list[]={"Entire region","Garia","Jadavpur","Kasba"};
    ArrayList<String> location = new ArrayList<>();
    Firebase mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://careist-46253.firebaseio.com/locations/");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Adding text for Mental Wellness button
        Button button1 = (Button)findViewById(R.id.mentalwellness);
        button1.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button1.setText("  Mental Wellness");

        //Adding text for Dietitian button
        Button button2 = (Button)findViewById(R.id.dietitian);
        button2.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button2.setText("  Dietitian");

        //Adding text for Physiotherapist button
        Button button3 = (Button)findViewById(R.id.physiotherapist);
        button3.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button3.setText("  Physiotherapist");

        //Adding text for Vets button
        Button button4 = (Button)findViewById(R.id.vets);
        button4.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button4.setText("  Vets");

        //Adding text for Speechtherapist button
        Button button5 = (Button)findViewById(R.id.speechtherapist);
        button5.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button5.setText("  Speechtherapist");

        //Adding text for Spa and salon button
        Button button6 = (Button)findViewById(R.id.spaandsalon);
        button6.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button6.setText("   Spa and salon");

        //Adding text for General Physician button
        Button button7 = (Button)findViewById(R.id.generalphysician);
        button7.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button7.setText("   General Physician");

        //Adding text for Accupressure button
        Button button8 = (Button)findViewById(R.id.accupressure);
        button8.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        button8.setText("   Accupressure");

        //Adding text for City
        TextView tv=(TextView)findViewById(R.id.city);
        tv.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        tv.setText("   Kolkata");

        spinner2meth();


        /*This part is for testing with JSON
        TextView output=(TextView) findViewById(R.id.sample);
        String strJson="{ \"Employee\" :[{\"id\":\"101\",\"name\":\"Sonoo Jaiswal\",\"salary\":\"50000\"},
        {\"id\":\"102\",\"name\":\"Vimal Jaiswal\",\"salary\":\"60000\"}] }";

        String data = "";
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject  jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}*/
    }

    public <ViewGroup> void spinner2meth(){
        Spinner mySpinner = (Spinner) findViewById(R.id.options);
     final  ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,location)
        {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                tfavv = Typeface.createFromAsset(getAssets(),"OpenSans-Light.ttf");
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(tfavv);
                v.setTextColor(Color.BLACK);
                v.setTextSize(12);
                return v;
            }

            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(tfavv);
                v.setTextColor(Color.BLACK);
                v.setTextSize(12);
                return v;
            }
        };
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter1);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value  = dataSnapshot.getKey();
                location.add(value);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
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
    }*/
}
