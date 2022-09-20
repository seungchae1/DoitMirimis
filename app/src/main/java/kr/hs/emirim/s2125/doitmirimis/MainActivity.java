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
        btnCharacter.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){/*
                case R.id.btn_calendar:
                    intent = new Intent(MainActivity.this, Calendar.class);
                    startActivity(intent);
                    break;*/
                case R.id.btn_char:
                    intent = new Intent(MainActivity.this, CharacterActivity.class);
                    intent.putExtra("back","main");
                    startActivity(intent);
                    break;
            }
        }
    };
}