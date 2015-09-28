package cs4720.cs.virginia.edu.cs4720_android;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;


import java.util.ArrayList;

import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    // Construct the data source
    ArrayList<Goal> arrayOfGoals = new ArrayList<Goal>();
    DashAdapter adapter;
    ListView listView;
    // Create the adapter to convert the array to views

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.listView);
        adapter = new DashAdapter(this, arrayOfGoals);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(mMessageClickedHandler);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();




        // get values from form
        if (extras != null) {
            String title = extras.getString(AddGoal.EXTRA_TITLE);
            String goal = extras.getString(AddGoal.EXTRA_GOAL);
            String unit = extras.getString(AddGoal.EXTRA_UNIT);
            String increment = extras.getString(AddGoal.EXTRA_INCREMENT);
            String interval = extras.getString(AddGoal.EXTRA_INTERVAL);

            String describe_goal = title + " " + goal + " " + unit + " per " +  interval;
            newRow(describe_goal, goal, increment);
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Goal.class);
        startActivity(intent);
    }

    public void onClickUp(View view) {
        Button upButton = (Button) row.findViewById(R.id.button_up);
        Integer randomNum = 1 + (int)(Math.random()*100000000);
        upButton.setId(randomNum);
        if (upButton != null) {
            upButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView num = (TextView) findViewById(R.id.text_goal);
                    Double orgNum = Double.parseDouble(num.getText().toString());
                    Double newNum = orgNum + Double.parseDouble(interval);
                    num.setText(newNum.toString());
                }

            });
        }
    }

    public void onClickDown(View view) {
        // down on click
        Button downButton = (Button) rowView.findViewById(R.id.button_down);
        downButton.setId(randomNum+2);
        downButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView num = (TextView) findViewById(R.id.text_goal);
                Double orgNum = Double.parseDouble(num.getText().toString());
                Double newNum = orgNum - Double.parseDouble(interval);
                num.setText(newNum.toString());
            }

        });
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

    public void addItem(View view) {





//        EditText editText = (EditText)findViewById(R.id.textView);
        Goal a = new Goal();
        arrayOfGoals.add(a);
        adapter.notifyDataSetChanged();
//        Log.d("BuildingListView", itemList.toString());

    }

    public void onDeleteClicked(View v) {
        // remove the row by calling the getParent on button
        listView.removeView((View) v.getParent());
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: Handle screen rotation:
        // encapsulate information in a parcelable object, and save it
        // into the state bundle.

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO: Handle screen rotation:
        // restore the saved items and inflate each one with inflateEditRow;

    }
}