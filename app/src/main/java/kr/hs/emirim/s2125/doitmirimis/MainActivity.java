package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnCharacter = findViewById(R.id.btn_char);
        ImageButton btnCalendar = findViewById(R.id.btn_calendar);
        btnCharacter.setOnClickListener(btnListener);
        btnCalendar.setOnClickListener(btnListener);
        imageButton = (ImageButton) findViewById(R.id.button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder menu = new AlertDialog.Builder(MainActivity.this);
                menu.setIcon(R.mipmap.ic_launcher);
                menu.setTitle("DialogTest"); // 제목
                menu.setMessage("안녕하세요"); // 문구
                // 확인 버튼
                menu.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // dialog 제거
                        dialog.dismiss();
                    }
                });
                menu.show();
            }
        });
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