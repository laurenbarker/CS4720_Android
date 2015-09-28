//package cs4720.cs.virginia.edu.cs4720_android;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class DashActivity extends Activity implements OnClickListener {
//
//    // Parent view for all rows and the add button.
//    private LinearLayout mContainerView;
//
//    //    private Button goal_activity;
//    // There always should be only one empty row, other empty rows will
//    // be removed.
//    private View mExclusiveEmptyView;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        setContentView(R.layout.row_container);
//
//        mContainerView = (LinearLayout) findViewById(R.id.parentView);
//
//        // Add some examples
//        newRow("Work 10 hours each week", "10.0", "1");
//
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        // get values from form
//        if (extras != null) {
//            String title = extras.getString(AddGoal.EXTRA_TITLE);
//            String goal = extras.getString(AddGoal.EXTRA_GOAL);
//            String unit = extras.getString(AddGoal.EXTRA_UNIT);
//            String increment = extras.getString(AddGoal.EXTRA_INCREMENT);
//            String interval = extras.getString(AddGoal.EXTRA_INTERVAL);
//
//            String describe_goal = title + " " + goal + " " + unit + " per " +  interval;
//            newRow(describe_goal, goal, increment);
//        }
//    }
//
//    /** Called when the user clicks the Add Goal button */
//    public void onClick(View view) {
//        Intent intent = new Intent(this, AddGoal.class);
//        startActivity(intent);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // TODO: Handle screen rotation:
//        // encapsulate information in a parcelable object, and save it
//        // into the state bundle.
//
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // TODO: Handle screen rotation:
//        // restore the saved items and inflate each one with inflateEditRow;
//
//    }
//
//    // onClick handler for the "X" button of each row
//    public void onDeleteClicked(View v) {
//        // remove the row by calling the getParent on button
//        mContainerView.removeView((View) v.getParent());
//    }
//
//    // adds a new row
//    private void newRow(String name, String goal, String param_interval) {
//        final String interval = param_interval;
//
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View rowView = inflater.inflate(R.layout.row, null);
//        // delete button
//        final ImageButton deleteButton = (ImageButton) rowView
//                .findViewById(R.id.buttonDelete);
//        // description
//        final TextView description = (TextView) rowView
//                .findViewById(R.id.view_description);
//        // goal
//        final TextView text_goal = (TextView) rowView
//                .findViewById(R.id.text_goal);
//
//        // set description
//        if (name != null && !name.isEmpty()) {
//            description.setText(name);
//        } else {
//            mExclusiveEmptyView = rowView;
//            deleteButton.setVisibility(View.INVISIBLE);
//        }
//
//        // set goal
//        if (name != null && !name.isEmpty()) {
//            text_goal.setText(goal);
//        }
//
//        // up on click
//        Button upButton = (Button) rowView.findViewById(R.id.button_up);
//        Integer randomNum = 1 + (int)(Math.random()*100000000);
//        upButton.setId(randomNum);
//        if (upButton != null) {
//            upButton.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    TextView num = (TextView) findViewById(R.id.text_goal);
//                    Double orgNum = Double.parseDouble(num.getText().toString());
//                    Double newNum = orgNum + Double.parseDouble(interval);
//                    num.setText(newNum.toString());
//                }
//
//            });
//        }
//
//        // down on click
//        Button downButton = (Button) rowView.findViewById(R.id.button_down);
//        downButton.setId(randomNum+2);
//        downButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                TextView num = (TextView)findViewById(R.id.text_goal);
//                Double orgNum = Double.parseDouble(num.getText().toString());
//                Double newNum = orgNum - Double.parseDouble(interval);
//                num.setText(newNum.toString());
//            }
//
//        });
//
//
//
//        // Inflate at the end of all rows but before the "Add new" button
//        mContainerView.addView(rowView, mContainerView.getChildCount() - 1);
//    }
//}