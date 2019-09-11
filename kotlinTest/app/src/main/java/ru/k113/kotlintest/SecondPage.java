package ru.k113.kotlintest;

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

    TextView cityName, termSensor;
    private Sensor sensor;
    private SensorManager sensorManager;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

        cityName = findViewById(R.id.cityID);
        cityName.setText(BackEnd.getCity());//Взять имя из бэкэнда

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor = sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) : null;

        termSensor = findViewById(R.id.termsensor);//встроенный датчик температуры
        str = "term " + BackEnd.getLtms();
        termSensor.setText(str);


        Button button = findViewById(R.id.returnbtn);     // Кнопка возврата
        button.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondPage.this, StartPage.class);
                BackEnd.isLog("end secondpage");
                finish();//закрыть окно и вернуться в породившее
            }
        });

    }//onCreate

    // Если приложение свернуто, то не будем тратить энергию на получение информации по датчикам
    @Override
    protected void onPause() {
        super.onPause();
        BackEnd.isLog("датчик на паузу");
        sensorManager.unregisterListener(listenerTerm, sensor);
    }

    // Слушатель датчика температуры
    private final SensorEventListener listenerTerm = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }//onAccuracyChanged

        @Override
        public void onSensorChanged(SensorEvent event) {
            str = "term " + BackEnd.getLtms();
            termSensor.setText(str);
            BackEnd.setLtms(showTermSensor(event));
        }//onSensorChanged
    };//SensorEventListener

    // Вывод датчика
    private float showTermSensor(SensorEvent event) {
        BackEnd.isLog("term=" + event.values[0]);
        str = "term " + BackEnd.getLtms();
        termSensor.setText(str);
        return event.values[0];
    }//showTermSensor
}


