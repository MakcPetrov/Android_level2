package ru.k113.rxhw.task2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ru.k113.rxhw.R;

public class ObserverActivity extends AppCompatActivity {

    private Sender mSender = new Sender();
    private Iam mDestination = new Iam();
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        mTextView = findViewById(R.id.text_view);
        mTextView.setText("0");
    }

    public void onClickButtonAdd(View view) {
        //Log.d(Const.TAG, "ButtonAdd ");
        mSender.addObserver(mDestination);
        mTextView.setText(String.valueOf(mSender.getCoutObserver()));
    }

    public void onClickButtonRemove(View view) {
        //Log.d(Const.TAG, "ButtonRemove " );
        mSender.removeObserver(mDestination);
    }

    public void onClickButtonSend(View view) {
        //Log.d(Const.TAG, "ButtonSend ");
        mSender.newItem(" user", " n");
    }
}
