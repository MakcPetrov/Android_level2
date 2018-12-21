package k113.dweather;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondPage  extends AppCompatActivity {

    TextView cityName,termSensor;
    private Sensor sensor;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

        cityName = findViewById(R.id.cityID);
        cityName.setText(BackEnd.getCity()); // Сохранить их в TextView

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor = sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) : null;

        termSensor = findViewById(R.id.termsensor);//встроенный датчик температуры
        termSensor.setText("000");


        Button button = findViewById(R.id.returnbtn);     // Кнопка
        button.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondPage.this, StartPage.class);
//                startActivity(intent);//вернуться в предыдущее окно
                BackEnd.isLog("end secondpage");
                finish();//закрыть окно и вернуться в породившее
            }
        });

    }//onCreate

    // Вывод датчика освещенности
    private String showLightSensors(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Light Sensor value = ").append(event.values[0])
                .append("\n").append("=======================================").append("\n");
        return (stringBuilder.toString());
    }


    // Слушатель датчика освещенности
    private final SensorEventListener listenerLight = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            showLightSensors(event);
        }
    };


}
