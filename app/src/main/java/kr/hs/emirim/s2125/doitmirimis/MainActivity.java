package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnCharacter = findViewById(R.id.btn_char);
        ImageButton btnCalendar = findViewById(R.id.btn_calendar);
        btnCharacter.setOnClickListener(btnListener);
        btnCalendar.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn_calendar:
                    intent = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_char:
                    intent = new Intent(MainActivity.this, CharacterActivity.class);
                    intent.putExtra("back","main");
                    startActivity(intent);
                    break;
            }
        }
    };
}