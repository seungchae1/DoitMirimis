package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GetCharacterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_character2);
        ImageButton check_btn = findViewById(R.id.check_btn);
        ImageButton character_btn = findViewById(R.id.character_btn);
        check_btn.setOnClickListener(btnListener);
        character_btn.setOnClickListener(btnListener);

        int giveChar = (int)(Math.random()*6)+1;
        CharacterActivity.get_Char[giveChar]=true;

        ImageView imgv = findViewById(R.id.get_imgv);
        imgv.setImageResource(CharacterActivity.imgId[giveChar]);
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
                    startActivity(intent);
                    break;
            }
        }
    };
}