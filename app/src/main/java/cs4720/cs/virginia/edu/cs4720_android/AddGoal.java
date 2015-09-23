package cs4720.cs.virginia.edu.cs4720_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by laurenbarker on 9/21/15.
 */
public class AddGoal extends Activity implements AdapterView.OnItemSelectedListener{
    // strings
    public final static String EXTRA_TITLE = ".TITLE";
    public final static String EXTRA_GOAL = ".GOAL";
    public final static String EXTRA_UNIT = ".UNIT";
    public final static String EXTRA_INCREMENT = ".INCREMENT";
    public final static String EXTRA_BACKGROUND = ".BACKGROUND";
    public final static String EXTRA_INTERVAL = ".INTERVAL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        // interval spinner
        Spinner spinner_interval = (Spinner) findViewById(R.id.spinner_interval);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_interval = ArrayAdapter.createFromResource(this,
                R.array.array_interval, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_interval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_interval.setAdapter(adapter_interval);
        spinner_interval.setOnItemSelectedListener(this);

        // background spinner
        Spinner spinner_background = (Spinner) findViewById(R.id.spinner_background);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_background = ArrayAdapter.createFromResource(this,
                R.array.array_background, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_background.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_background.setAdapter(adapter_background);
        spinner_background.setOnItemSelectedListener(this);

        // increment spinner
        Spinner spinner_increment = (Spinner) findViewById(R.id.spinner_increment);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_increment = ArrayAdapter.createFromResource(this,
                R.array.array_increment, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_increment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_increment.setAdapter(adapter_background);
        spinner_increment.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String selected = (String) parent.getItemAtPosition(pos);

        // toast
        Context context = getApplicationContext();
        CharSequence text = selected;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /** Called when the user clicks the Save button */
    public void saveGoal(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // title
        EditText editTitle = (EditText) findViewById(R.id.input_title);
        String title = editTitle.getText().toString();
        // goal
        EditText editGoal = (EditText) findViewById(R.id.input_goal);
        String goal = editGoal.getText().toString();
        // unit
        EditText editUnit = (EditText) findViewById(R.id.input_unit);
        String unit = editUnit.getText().toString();
        // increment
        Spinner editBackground = (Spinner) findViewById(R.id.spinner_background);
        String background = editBackground.getSelectedItem().toString();
        // background
        Spinner editIncrement = (Spinner) findViewById(R.id.spinner_increment);
        String increment = editIncrement.getSelectedItem().toString();
        // interval
        Spinner editInterval = (Spinner) findViewById(R.id.spinner_interval);
        String interval = editInterval.getSelectedItem().toString();

        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, title);
        extras.putString(EXTRA_GOAL, goal);
        extras.putString(EXTRA_UNIT, unit);
        extras.putString(EXTRA_INCREMENT, increment);
        extras.putString(EXTRA_BACKGROUND, background);
        extras.putString(EXTRA_INTERVAL, interval);
        intent.putExtras(extras);

        startActivity(intent);
    }
}
