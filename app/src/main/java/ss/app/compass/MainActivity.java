package ss.app.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ((SensorManager) getSystemService(SENSOR_SERVICE)).registerListener(
            new SensorEventListener(){
                @Override public void onSensorChanged(SensorEvent event) {
                    synchronized (this)  {     
                        ((ImageView) findViewById(R.id.imageView)).startAnimation(
                            new RotateAnimation(
                                -event.values[0],
                                event.values[0], 
                                Animation.RELATIVE_TO_SELF, 
                                0.5f, 
                                Animation.RELATIVE_TO_SELF, 
                                0.5f
                            )
                        );
                        ((TextView) findViewById(R.id.textView)).setText((int)event.values[0] + "°");
                    }
                }
                @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
            },
            ((SensorManager)getSystemService(SENSOR_SERVICE)).getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        );
    }
}
