package cs4720.cs.virginia.edu.cs4720_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by laurenbarker on 9/21/15.
 */
public class AddGoal extends Activity implements AdapterView.OnItemSelectedListener{
    // strings
    public final static String EXTRA_TITLE = ".TITLE";
    public final static String EXTRA_GOAL = ".GOAL";
    public final static String EXTRA_UNIT = ".UNIT";
    public final static String EXTRA_INCREMENT = ".INCREMENT";
    public final static String EXTRA_INTERVAL = ".INTERVAL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

//        if( getIntent().getBooleanExtra("Exit me", false)) {
//            Intent exitIntent = new Intent(AddGoal.this, MainActivity.class);
//            exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            exitIntent.putExtra("Exit me", true);
//            startActivity(exitIntent);
//            if (!isFinishing()) {
//                finish();
//            }
//            return; // add this to prevent from doing unnecessary stuffs
//        }

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

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String selected = (String) parent.getItemAtPosition(pos);

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
        Double goal;
        try {
            goal = Double.parseDouble(editGoal.getText().toString());
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("some error", "" + e);
            goal = 0.0;
        }

        // unit
        EditText editUnit = (EditText) findViewById(R.id.input_unit);
        String unit = editUnit.getText().toString();
        // increment
        EditText editIncrement = (EditText) findViewById(R.id.input_increment);
        Double increment;
        try {
            increment = Double.parseDouble(editIncrement.getText().toString());
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("some error", "" + e);
            increment = 0.0;
        }

        // interval
        Spinner editInterval = (Spinner) findViewById(R.id.spinner_interval);
        String interval = editInterval.getSelectedItem().toString();


        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, title);
        extras.putDouble(EXTRA_GOAL, goal);
        extras.putString(EXTRA_UNIT, unit);
        extras.putDouble(EXTRA_INCREMENT, increment);
        extras.putString(EXTRA_INTERVAL, interval);
        intent.putExtras(extras);

        startActivity(intent);
    }

    /** Called when the user clicks the Delete button */
    public void deleteGoal(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
