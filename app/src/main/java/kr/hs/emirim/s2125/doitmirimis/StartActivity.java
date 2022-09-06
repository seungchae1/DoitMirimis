package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ImageButton btnMain = findViewById(R.id.btn_main);
        ImageButton btnCharacter = findViewById(R.id.btn_character);
        btnMain.setOnClickListener(btnListener);
        btnCharacter.setOnClickListener(btnListener);
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn_main:
                    intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_character:
                    intent = new Intent(StartActivity.this, CharacterActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}