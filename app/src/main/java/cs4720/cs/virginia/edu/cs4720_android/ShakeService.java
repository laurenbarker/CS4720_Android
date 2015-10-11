package cs4720.cs.virginia.edu.cs4720_android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by laurenbarker on 10/10/15.
 */
public class ShakeService extends Service {

    private float xAccel = 0, yAccel = 0, zAccel = 0;
    private float xPreviousAccel = 0, yPreviousAccel = 0, zPreviousAccel= 0;
    private boolean firstUpdate = true;
    private final float shakeThreshold = 1.5f;
    private boolean shakeInitiated = false;
    //SensorEventListener mShakeListener;
    SensorManager mSensorMgr;
    Sensor mAccelerometer;
    private static final String TAG = "ShakeSensor";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Shake detection Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Shake detection Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        mSensorMgr.unregisterListener(shakeListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        xAccel = 0;
        yAccel = 0;
        zAccel = 0;
        xPreviousAccel = 0;
        yPreviousAccel = 0;
        zPreviousAccel= 0;
        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Log.d(TAG, "Shake detection start");
        mSensorMgr.registerListener(shakeListener,
                mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccelerometer = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return START_STICKY;
    }

    public SensorEventListener shakeListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }
        @Override
        public void onSensorChanged(SensorEvent se) {
            updateAccelParameters(se.values[0], se.values[1], se.values[2]);
            if ((!shakeInitiated) && isAccelerationChanged()) {
                shakeInitiated = true;
            }else if ((shakeInitiated) && isAccelerationChanged()){
                executeShakeAction();
            }else if((shakeInitiated) && (!isAccelerationChanged())){
                shakeInitiated = false;
            }
        }

        private void executeShakeAction() {
            Log.d(TAG, "Device was shaken.");
            //this method is called when devices shakes
            mSensorMgr.unregisterListener(this);
            Intent intent = new Intent(ShakeService.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Exit me", true);
            startActivity(intent);
        }

        private boolean isAccelerationChanged() {
            float deltaX = Math.abs(xPreviousAccel - xAccel);
            float deltaY = Math.abs(yPreviousAccel - yAccel);
            float deltaZ = Math.abs(zPreviousAccel - zAccel);
            return (deltaX > shakeThreshold && deltaY > shakeThreshold)
                    || (deltaX > shakeThreshold && deltaZ > shakeThreshold)
                    || (deltaY > shakeThreshold && deltaZ > shakeThreshold);
        }

        private void updateAccelParameters(float xNewAccel, float yNewAccel, float zNewAccel) {
            if (firstUpdate) {
                xPreviousAccel = xNewAccel;
                yPreviousAccel = yNewAccel;
                zPreviousAccel = zNewAccel;
                firstUpdate = false;
            }else{
                xPreviousAccel = xAccel;
                yPreviousAccel = yAccel;
                zPreviousAccel = zAccel;
            }
            xAccel = xNewAccel;
            yAccel = yNewAccel;
            zAccel = zNewAccel;
        }

    };



}
