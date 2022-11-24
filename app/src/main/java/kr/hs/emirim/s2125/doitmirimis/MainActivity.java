package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    LinearLayout frame;
    TextView text1;
    EditText edit1;
    ArrayList<CheckBox> checkList = new ArrayList<CheckBox>();
    int cnt;
    int new_cnt;
    int i;
    String edit_text1, edit_text2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 타이틀바 없애는 거임!! 지우지 마셈!!
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언

        today = sharedPreferences.getString("today","");
        //Toast.makeText(getApplicationContext(), today, Toast.LENGTH_SHORT).show();
        TextView today_text = findViewById(R.id.today_text);
        today_text.setText(today);
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

        cnt=sharedPreferences.getInt(today.concat("cnt"),0);
        new_cnt=0;
        for(i=1; i<=cnt; i++){
            View checkView = View.inflate(getApplicationContext(), R.layout.new_checkbox, null);
            TextView checkVText = checkView.findViewById(R.id.text_n);
            checkVText.setText(sharedPreferences.getString(today.concat("edit1")+i,""));
            checkVText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new_cnt++;
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    View dlgView = View.inflate(MainActivity.this, R.layout.checkbox_dlg,null);
                    EditText editDlg = dlgView.findViewById(R.id.ch_edit);
                    EditText editDlg2 = dlgView.findViewById(R.id.ch_edit2);
                    editDlg.setText(checkVText.getText());
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int a) {
                            checkVText.setText(editDlg.getText());
                            editor.putString(today.concat("edit1")+new_cnt,editDlg.getText().toString());
                            //Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
                            editor.commit();
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
                    editor.putInt(today.concat("cnt"),cnt);
                    editor.commit();
                    View checkView = View.inflate(getApplicationContext(), R.layout.new_checkbox, null);
                    TextView checkVText = checkView.findViewById(R.id.text_n);
                    checkVText.setText(sharedPreferences.getString(today.concat("edit1")+cnt,""));
                    checkVText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                            View dlgView = View.inflate(MainActivity.this, R.layout.checkbox_dlg,null);
                            EditText editDlg = dlgView.findViewById(R.id.ch_edit);
                            EditText editDlg2 = dlgView.findViewById(R.id.ch_edit2);
                            //editDlg2.setText(sharedPreferences.getString(edit_text2,""));
                            editDlg.setText(checkVText.getText());
                            dlg.setView(dlgView);
                            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    checkVText.setText(editDlg.getText());
                                    editor.putString(today.concat("edit1")+cnt,editDlg.getText().toString());
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
                    EditText editDlg2 = dlgView.findViewById(R.id.ch_edit2);
                    editDlg2.setText(sharedPreferences.getString("default2",""));
                    editDlg.setText(text1.getText());
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            text1.setText(editDlg.getText());
                            editor.putString( "default",editDlg.getText().toString());
                            editor.putString( "default2",editDlg2.getText().toString());
                            editor.commit();
                        }
                    });
                    dlg.show();
            }
        }
    };
}