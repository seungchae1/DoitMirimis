package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ImageButton btnCalendar = findViewById(R.id.btn_calendar);
        ImageButton btnCharacter = findViewById(R.id.btn_character);
        btnCalendar.setOnClickListener(btnListener);
        btnCharacter.setOnClickListener(btnListener);
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn_calendar:
                    intent = new Intent(StartActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_character:
                    intent = new Intent(StartActivity.this, CharacterActivity.class);
                    intent.putExtra("back","start");
                    startActivity(intent);
                    break;
            }
        }
    };
}