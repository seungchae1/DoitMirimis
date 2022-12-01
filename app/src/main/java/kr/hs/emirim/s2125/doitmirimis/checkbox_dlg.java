package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class checkbox_dlg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애는 거임!! 지우지 마셈!!
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.checkbox_dlg);
    }
}