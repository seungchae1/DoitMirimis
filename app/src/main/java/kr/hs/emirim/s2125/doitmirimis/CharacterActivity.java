package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CharacterActivity extends AppCompatActivity {
    GridView gridv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        setTitle("캐릭터 목록");
        gridv = findViewById(R.id.gridv);
        charAdapter adapter= new charAdapter(this);
        gridv.setAdapter(adapter);

        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        home.setOnClickListener(btnListener);
        back.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.btn_home:
                    intent = new Intent(CharacterActivity.this, StartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_back:
                    intent = new Intent(CharacterActivity.this, StartActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private class charAdapter extends BaseAdapter {
        Context context;
        public charAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() { return 24;}

        @Override
        public Object getItem(int i) {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imgv = new ImageView(context);
            ViewGroup.LayoutParams params = new GridView.LayoutParams(330,330);
            imgv.setLayoutParams(params);
            imgv.setPadding(10,20,20,10);
            imgv.setImageResource(R.drawable.char_x);
            return imgv;
        }
    }
}