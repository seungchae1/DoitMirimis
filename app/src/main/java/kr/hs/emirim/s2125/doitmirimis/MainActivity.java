package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static RequestQueue requestQueue;
    LinearLayout frame;
    TextView text1;
    EditText edit1;
    ArrayList<CheckBox> checkList = new ArrayList<CheckBox>();
    int cnt;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애는 거임!! 지우지 마셈!!
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언

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
        text1.setText(sharedPreferences.getString("default",""));

        cnt=sharedPreferences.getInt("cnt",0);
        for(int i=0; i<cnt; i++){
            View checkView = View.inflate(getApplicationContext(), R.layout.new_checkbox, null);
            TextView checkVText = checkView.findViewById(R.id.text_n);
            checkVText.setText(sharedPreferences.getString(Integer.toString(i),""));
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
            CheckBox check = checkView.findViewById(R.id.check_n);
            frame.addView(checkView);
        }
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
                    cnt++;
                    editor.putInt("cnt",cnt);
                    editor.commit();
                    View checkView = View.inflate(getApplicationContext(), R.layout.new_checkbox, null);
                    TextView checkVText = checkView.findViewById(R.id.text_n);
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
                                    editor.putString( Integer.toString(cnt-1),editDlg.getText().toString());
                                    editor.commit();
                                }
                            });
                            dlg.show();
                        }
                    });
                    CheckBox check1 = checkView.findViewById(R.id.check_n);
                    frame.addView(checkView);
                    break;
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
                            editor.putString( "default",editDlg.getText().toString());
                            editor.commit();
                        }
                    });
                    dlg.show();
            }
        }
    };
}