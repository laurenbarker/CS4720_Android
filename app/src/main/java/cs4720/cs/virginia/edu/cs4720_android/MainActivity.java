package cs4720.cs.virginia.edu.cs4720_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    // Construct the data source
    ArrayList<Goal> arrayOfGoals = new ArrayList<Goal>();
    Goal_Adapter gAdapter;
    ListView listView;
    DatabaseHandler db;
    // Create the adapter to convert the array to views


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listView = (ListView) findViewById(R.id.listView);
            listView.setItemsCanFocus(false);
            //add_btn = (Button) findViewById(R.id.add_btn);

            Set_Refresh_Data();

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("some error", "" + e);
        }



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        // get values from form
        if (extras != null) {
            String title = extras.getString(AddGoal.EXTRA_TITLE);
            Double goal = Double.parseDouble(extras.getString(AddGoal.EXTRA_GOAL));
            String unit = extras.getString(AddGoal.EXTRA_UNIT);
            Double increment = Double.parseDouble(extras.getString(AddGoal.EXTRA_INCREMENT));
            String interval = extras.getString(AddGoal.EXTRA_INTERVAL);

            String describe_goal = title + " " + goal + " " + unit + " per " +  interval;
            addItem(title, goal, unit, increment, interval, describe_goal);
        }


    }

    public void Set_Refresh_Data() {
        arrayOfGoals.clear();
        db = new DatabaseHandler(this);
        ArrayList<Goal> goal_array_from_db = db.Get_Goals();

        for (int i = 0; i < goal_array_from_db.size(); i++) {

            int tidno = goal_array_from_db.get(i).getID();
            String title = goal_array_from_db.get(i).getEXTRA_TITLE();
            Double goal = goal_array_from_db.get(i).getEXTRA_GOAL();
            String unit = goal_array_from_db.get(i).getEXTRA_UNIT();
            Double increment = goal_array_from_db.get(i).getEXTRA_INCREMENT();
            String interval = goal_array_from_db.get(i).getEXTRA_INTERVAL();
            Goal gl = new Goal();
            gl.setID(tidno);
            gl.setTitle(title);
            gl.setGoal(goal);
            gl.setUnit(unit);
            gl.setIncrement(increment);
            gl.setInterval(interval);

            arrayOfGoals.add(gl);
        }
        db.close();

        // Attach the adapter to a ListView
        // listView = (ListView) findViewById(R.id.listView);
        gAdapter = new Goal_Adapter(MainActivity.this, R.layout.row ,arrayOfGoals);
        listView.setAdapter(gAdapter);
        gAdapter.notifyDataSetChanged();
    }

    /** Called when the user clicks the Add Goal button */
    public void onClick(View view) {
        Intent intent = new Intent(this, AddGoal.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Set_Refresh_Data();

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

    public void addItem(String title, Double goal, String unit, Double increment, String interval, String describe_goal) {

//        EditText editText = (EditText)findViewById(R.id.textView);
        Goal a = new Goal(title, goal, unit, increment, interval);
        a.setDESCRIPTION(describe_goal);
        arrayOfGoals.add(a);
        gAdapter.notifyDataSetChanged();

        db.Add_Goal(a);
        String Toast_msg = "Data inserted successfully";
        Show_Toast(Toast_msg);
//        Log.d("BuildingListView", itemList.toString());

    }

    public void Show_Toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void onDeleteClicked(View v) {
        // remove the row by calling the getParent on button
        listView.removeView((View) v.getParent());
    }

    public class Goal_Adapter extends ArrayAdapter<Goal> {
        Activity activity;
        int layoutResourceId;
        Goal module;
        ArrayList<Goal> data = new ArrayList<Goal>();

        public Goal_Adapter(Activity act, int layoutResourceId,
                               ArrayList<Goal> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            GoalHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new GoalHolder();
                holder.title = (TextView) row.findViewById(R.id.view_description);
                holder.goal = (TextView) row.findViewById(R.id.text_goal);
                holder.up = (Button) row.findViewById(R.id.button_up);
                holder.down = (Button) row.findViewById(R.id.button_down);
                holder.delete = (ImageButton) row.findViewById(R.id.buttonDelete);
                row.setTag(holder);
            } else {
                holder = (GoalHolder) row.getTag();
            }
            module = data.get(position);
            holder.delete.setTag(module.getID());
            holder.up.setTag(module.getID());
            holder.down.setTag(module.getID());
            holder.title.setTag(module.getDESCRIPTION());
            holder.goal.setText(module.getEXTRA_GOAL().toString());

            holder.up.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("Up Button Clicked", "**********");

                    final int goal_id = Integer.parseInt(v.getTag().toString());

                    DatabaseHandler dBHandler = new DatabaseHandler(
                            activity.getApplicationContext());

                    Goal goal = dBHandler.Get_Goal(goal_id);

//                    String Toast_msg = "Goal id = " + goal.toString();
//                    Show_Toast(Toast_msg);

                    dBHandler.Increment_Goal(goal);
                    MainActivity.this.onResume();

                }
            });
            holder.down.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("Up Button Clicked", "**********");

                    final int goal_id = Integer.parseInt(v.getTag().toString());

                    DatabaseHandler dBHandler = new DatabaseHandler(
                            activity.getApplicationContext());

                    Goal goal = dBHandler.Get_Goal(goal_id);
                    dBHandler.Decrement_Goal(goal);
                    MainActivity.this.onResume();

                }
            });
            holder.delete.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(final View v) {
                    // TODO Auto-generated method stub

                    // show a message while loader is loading

                    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete ");
                    final int goal_id = Integer.parseInt(v.getTag().toString());
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok",
                            new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // MyDataObject.remove(positionToRemove);
                                    DatabaseHandler dBHandler = new DatabaseHandler(
                                            activity.getApplicationContext());
                                    dBHandler.Delete_Goal(goal_id);
                                    MainActivity.this.onResume();

                                }
                            });
                    adb.show();
                }

            });
            return row;

        }

        class GoalHolder {
            TextView title;
            TextView goal;
            Button up;
            Button down;
            ImageButton delete;
        }

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