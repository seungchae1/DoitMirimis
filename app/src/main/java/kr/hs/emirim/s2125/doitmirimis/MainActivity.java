package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.frame);
        ImageButton btnCharacter = findViewById(R.id.btn_char);
        ImageButton btnCalendar = findViewById(R.id.btn_calendar);
        btnCharacter.setOnClickListener(btnListener);
        btnCalendar.setOnClickListener(btnListener);
        ImageButton btnPlus = findViewById(R.id.plus);
        btnPlus.setOnClickListener(btnListener);
    }
    //
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_calendar:
                    intent = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_char:
                    intent = new Intent(MainActivity.this, CharacterActivity.class);
                    intent.putExtra("back", "main");
                    startActivity(intent);
                    break;
                case R.id.plus:
                    EditText newEdit = new EditText(MainActivity.this);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
                    newEdit.setLayoutParams(params);
                    newEdit.setBackgroundResource(R.drawable.rectangle1);
                    newEdit.setGravity(Gravity.TOP);
                    newEdit.setTextSize(15);
                    newEdit.setHint("오늘의 할 일은 무엇인가요?");
                    newEdit.setTextSize(15);
                    newEdit.setPadding(80,100,80,80);
                    frame.addView(newEdit);
            }
        }
    };
}
