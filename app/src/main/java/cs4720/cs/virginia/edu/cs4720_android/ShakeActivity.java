package cs4720.cs.virginia.edu.cs4720_android;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by laurenbarker on 10/10/15.
 */
public class ShakeActivity extends Activity implements SensorEventListener {

    private float xAccel, yAccel, zAccel;
    private float xPreviousAccel, yPreviousAccel, zPreviousAccel;
    private boolean firstUpdate = true;
    private final float shakeThreshold = 1.5f;
    private boolean shakeInitiated = false;
    SensorEventListener mShakeListener;
    SensorManager mSensorMgr;

    protected void onCreate()
    {
        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorMgr.registerListener(mShakeListener,
                mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void executeShakeAction() {
        //this method is called when devices shakes
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //setting the accuracy
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

}
