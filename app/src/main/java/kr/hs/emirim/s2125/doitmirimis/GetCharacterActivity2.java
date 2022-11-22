package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GetCharacterActivity2 extends AppCompatActivity {
    public static int giveChar;
    int imgId[]={R.drawable.img1_2,R.drawable.img2_2,R.drawable.img3_2,R.drawable.img4_2,R.drawable.img5_2,R.drawable.img6_2,R.drawable.img7_2,R.drawable.img8_2,
            R.drawable.img9_2,R.drawable.img10_2,R.drawable.img11_2,R.drawable.img12_2,R.drawable.img13_2,R.drawable.img14_2,R.drawable.img15_2,R.drawable.img16_2,
            R.drawable.img17_2, R.drawable.img18_2,R.drawable.img19_2,R.drawable.img20_2,R.drawable.img21_2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_character2);
        ImageButton check_btn = findViewById(R.id.check_btn);
        ImageButton character_btn = findViewById(R.id.character_btn);
        check_btn.setOnClickListener(btnListener);
        character_btn.setOnClickListener(btnListener);

        giveChar = (int)(Math.random()*21)+1;

        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        String input_img = "img";
        input_img.concat(Integer.toString(giveChar));
        editor.putBoolean(input_img,true);
        editor.commit();

        ImageView imgv = findViewById(R.id.get_imgv);
        imgv.setImageResource(imgId[giveChar]);
        TextView textv = findViewById(R.id.get_textv);
        textv.setText(CharacterActivity.imgName[giveChar]);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.check_btn:
                    intent = new Intent(GetCharacterActivity2.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.character_btn:
                    intent = new Intent(GetCharacterActivity2.this, CharacterActivity.class);
                    intent.putExtra("back","main");
                    startActivity(intent);
                    break;
            }
        }
    };
}