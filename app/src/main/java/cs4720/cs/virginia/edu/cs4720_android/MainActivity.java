package cs4720.cs.virginia.edu.cs4720_android;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Construct the data source
    ArrayList<Goal> arrayOfGoals = new ArrayList<Goal>();
    DashAdapter adapter;
    // Create the adapter to convert the array to views

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new DashAdapter(this, arrayOfGoals);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(mMessageClickedHandler);
        listView.setAdapter(adapter);

    }


//    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
//        public void onItemClick(AdapterView parent, View v, int position, long id) {
//            Toast.makeText(getApplicationContext(), itemList.get(position), Toast.LENGTH_SHORT).show();
//        }
//    };


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
        Goal a = new Goal( "run everyday", "7", "miles", "1", "3");
        arrayOfGoals.add(a);
        adapter.notifyDataSetChanged();
//        Log.d("BuildingListView", itemList.toString());

    }
}