//
package cs4720.cs.virginia.edu.cs4720_android;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//
////public class MainActivity extends AppCompatActivity {
////
//////    GPSTracker gps = new GPSTracker(this);
//////    if(gps.canGetLocation()){ // gps enabled} // return boolean true/false
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
////}
//
//
////new CODE: http://javapapers.com/android/android-location-using-gps-network-provider/
//
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//public class MainActivity extends Activity {
//
//    Button btnGPSShowLocation;
//    Button btnNWShowLocation;
//
//    GPSTracker appLocationService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        appLocationService = new GPSTracker(
//                MainActivity.this);
//
//        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
//        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                Location gpsLocation = appLocationService
//                        .getLocation();
//
//                if (gpsLocation != null) {
//                    double latitude = gpsLocation.getLatitude();
//                    double longitude = gpsLocation.getLongitude();
//                    Toast.makeText(
//                            getApplicationContext(),
//                            "Mobile Location (GPS): \nLatitude: " + latitude
//                                    + "\nLongitude: " + longitude,
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    showSettingsAlert("GPS");
//                }
//
//            }
//        });
//
//        btnNWShowLocation = (Button) findViewById(R.id.btnNWShowLocation);
//        btnNWShowLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                Location nwLocation = appLocationService
//                        .getLocation();
//
//                if (nwLocation != null) {
//                    double latitude = nwLocation.getLatitude();
//                    double longitude = nwLocation.getLongitude();
//                    Toast.makeText(
//                            getApplicationContext(),
//                            "Mobile Location (NW): \nLatitude: " + latitude
//                                    + "\nLongitude: " + longitude,
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    showSettingsAlert("NETWORK");
//                }
//
//            }
//        });
//
//    }
//
//    public void showSettingsAlert(String provider) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//                MainActivity.this);
//
//        alertDialog.setTitle(provider + " SETTINGS");
//
//        alertDialog
//                .setMessage(provider + " is not enabled! Want to go to settings menu?");
//
//        alertDialog.setPositiveButton("Settings",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(
//                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        MainActivity.this.startActivity(intent);
//                    }
//                });
//
//        alertDialog.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//        alertDialog.show();
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
//
//}

public class MainActivity extends Activity {

    Button btnGPSShowLocation;

            // GPSTracker class
            GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);

            // show location button click event
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View arg0) {
            // create class object
            gps = new GPSTracker(MainActivity.this);

            // check if GPS enabled
            if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            }

            }
            });
            }

}