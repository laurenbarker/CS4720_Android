package cs4720.cs.virginia.edu.cs4720_android;

/**
 * Created by reinaH on 9/13/15.
 * SOURCE : http://www.rdcworld-android.blogspot.in/2012/01/get-current-location-coordinates-city.html
 * SOURCE :http://www.javacodegeeks.com/2010/09/android-location-based-services.html
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GetCurrentLocation extends Activity {

    private LocationManager locationManager=null;
    private Button btnGetLocation=null;

    private String start_val = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to lock screen on Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnGetLocation = (Button) findViewById(R.id.btnLocation);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (displayGpsStatus()){
            try{
                locationManager.requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 5000, 10, new MyLocationListener());
            } catch (final SecurityException e) {
                Log.i("GetCurrentLocation", "Sorry, application does not have permissions to send to this destination.");
            }
        }
        btnGetLocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCurrentLocation();
            }
        });
    }

    protected void showCurrentLocation() {
        Location location=null;


        if (displayGpsStatus()){
            try{
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } catch (final SecurityException e) {
                Log.i("GetCurrentLocation", "application does not have GPS permission.");

            }}

        if (location != null) {
            String message = String.format("Current Location \n Longitude: %1$s \nLatitude: %2$s",
                    location.getLongitude(),location.getLatitude());
            Toast.makeText(GetCurrentLocation.this, message, Toast.LENGTH_LONG).show();
        }
    }


    /*----Check if GPS is enabled or disabled ----- */
    private Boolean displayGpsStatus(){

        Context context = this;
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);;
        boolean gpsStatus = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gpsStatus) {
            return true;
        } else {
            return false;
        }
    }

    /*----------Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            String message = String.format("Current Location \n Longitude: %1$s \nLatitude: %2$s",
                    loc.getLongitude(),loc.getLatitude());
            Toast.makeText(GetCurrentLocation.this, message, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(GetCurrentLocation.this, "Provider disabled by the user. GPS turned off.",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(GetCurrentLocation.this, "Provider enabled by the user. GPS turned on.",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            Toast.makeText(GetCurrentLocation.this, "Provider status changed",
                    Toast.LENGTH_LONG).show();

        }
    }


    /* When the up button is clicked it increases the number
     * when the down button is clicked it decreases the number
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the starting value:");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected;
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                start_val = input.getText().toString();
                TextView num = (TextView)findViewById(R.id.number);
                num.setText(start_val);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


        ImageButton upButton = (ImageButton) findViewById(R.id.up);

        upButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView num = (TextView)findViewById(R.id.number);
                Integer orgNum = Integer.parseInt(num.getText().toString());
                Integer newNum = orgNum + 1;
                num.setText(newNum.toString());
            }

        });
        ImageButton downButton = (ImageButton) findViewById(R.id.down);

        downButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView num = (TextView) findViewById(R.id.number);
                Integer orgNum = Integer.parseInt(num.getText().toString());
                Integer newNum = orgNum - 1;
                num.setText(newNum.toString());
            }
        });

        return true;
    }


}