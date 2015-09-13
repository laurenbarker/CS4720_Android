//
package cs4720.cs.virginia.edu.cs4720_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);

            // show location button click event
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {

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

    public void startService(View view) {
        Intent intent = new Intent(this, GetCurrentLocation.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, GetCurrentLocation.class);
        stopService(intent);
    }
}