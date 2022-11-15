package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout frame;
    TextView text1;
    EditText edit1;
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
        CheckBox check1 = findViewById(R.id.check1);
        check1.setOnClickListener(btnListener);
        text1= findViewById(R.id.text1);
        text1.setOnClickListener(btnListener);

        edit1=findViewById(R.id.ch_edit);
    }

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
                    View checkView = View.inflate(getApplicationContext(), R.layout.new_checkbox, null);
                    TextView checkVText = checkView.findViewById(R.id.text1);
                    checkVText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                            View dlgView = View.inflate(MainActivity.this, R.layout.checkbox_dlg,null);
                            EditText editDlg = dlgView.findViewById(R.id.ch_edit);
                            editDlg.setText(checkVText.getText());
                            dlg.setView(dlgView);
                            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    checkVText.setText(editDlg.getText());
                                }
                            });
                            dlg.show();
                        }
                    });
                    CheckBox check1 = checkView.findViewById(R.id.check1);
                    frame.addView(checkView);
                case R.id.text1:
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    View dlgView = View.inflate(MainActivity.this, R.layout.checkbox_dlg,null);
                    EditText editDlg = dlgView.findViewById(R.id.ch_edit);
                    editDlg.setText(text1.getText());
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            text1.setText(editDlg.getText());
                        }
                    });
                    dlg.show();
            }
        }
    };
}
