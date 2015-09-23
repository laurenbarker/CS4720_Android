package cs4720.cs.virginia.edu.cs4720_android;
import cs4720.cs.virginia.edu.cs4720_android.AddGoal;

//
//
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//
//public class MainActivity extends AppCompatActivity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
////    public void startService(View view) {
////        Intent intent = new Intent(this, GetCurrentLocation.class);
////        startService(intent);
////    }
////
////    public void stopService(View view) {
////        Intent intent = new Intent(this, GetCurrentLocation.class);
////        stopService(intent);
////    }
//}


import android.content.Intent;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import cs4720.cs.virginia.edu.cs4720_android.R;

public class MainActivity extends Activity {

    // Parent view for all rows and the add button.
    private LinearLayout mContainerView;
    // The "Add new" button
    private Button mAddButton;

//    private Button goal_activity;
    // There always should be only one empty row, other empty rows will
    // be removed.
    private View mExclusiveEmptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_add_goal);
        setContentView(R.layout.row_container);
//        addListenerOnButton();

        mContainerView = (LinearLayout) findViewById(R.id.parentView);
        mAddButton = (Button) findViewById(R.id.btnAddNewItem);

        // Add some examples
        inflateEditRow("Work 10 hours each week");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // get values from form
        if (extras != null) {
            String title = extras.getString(AddGoal.EXTRA_TITLE);
            String goal = extras.getString(AddGoal.EXTRA_GOAL);
            String unit = extras.getString(AddGoal.EXTRA_UNIT);
            TextView textView = new TextView(this);
            textView.setText(title + goal + unit);
            setContentView(textView);
        }
    }

//    public void addListenerOnButton(){
//
//        final Context context = this;
//        addG = (Button) findViewById(R.id.main_add_goal1);
//        addG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent  intent = new Intent (context, AddGoal.class);
//                    startActivity(intent);
//            }
//        });
//
//    }


        /** Called when the user clicks the Add Goal button */
    public void newGoal(View view) {
        Intent intent = new Intent(this, AddGoal.class);
        startActivity(intent);
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

    // onClick handler for the "Add new" button;
    public void onAddNewClicked(View v) {
        // Inflate a new row and hide the button self.
        inflateEditRow(null);
        v.setVisibility(View.GONE);
    }

    // onClick handler for the "X" button of each row
    public void onDeleteClicked(View v) {
        // remove the row by calling the getParent on button
        mContainerView.removeView((View) v.getParent());
    }

    // Helper for inflating a row
    private void inflateEditRow(String name) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.row, null);
        final ImageButton deleteButton = (ImageButton) rowView
                .findViewById(R.id.buttonDelete);
        final EditText editText = (EditText) rowView
                .findViewById(R.id.editText);

        if (name != null && !name.isEmpty()) {
            editText.setText(name);
        } else {
            mExclusiveEmptyView = rowView;
            deleteButton.setVisibility(View.INVISIBLE);
        }

        // A TextWatcher to control the visibility of the "Add new" button and
        // handle the exclusive empty view.
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().isEmpty()) {
                    mAddButton.setVisibility(View.GONE);
                    deleteButton.setVisibility(View.INVISIBLE);

                    if (mExclusiveEmptyView != null
                            && mExclusiveEmptyView != rowView) {
                        mContainerView.removeView(mExclusiveEmptyView);
                    }
                    mExclusiveEmptyView = rowView;
                } else {

                    if (mExclusiveEmptyView == rowView) {
                        mExclusiveEmptyView = null;
                    }

                    mAddButton.setVisibility(View.VISIBLE);
                    deleteButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        // Inflate at the end of all rows but before the "Add new" button
        mContainerView.addView(rowView, mContainerView.getChildCount() - 1);
    }
}