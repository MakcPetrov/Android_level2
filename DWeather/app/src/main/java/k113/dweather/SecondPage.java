package k113.dweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondPage  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);
        String txt = BackEnd.getCity();

        TextView textView = findViewById(R.id.sensor1);
        textView.setText(txt); // Сохранить их в TextView

        Button button = findViewById(R.id.returnbtn);     // Кнопка
        button.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondPage.this, StartPage.class);
                startActivity(intent);//вернуться в предыдущее окно
            }
        });

    }//onCreate

}
