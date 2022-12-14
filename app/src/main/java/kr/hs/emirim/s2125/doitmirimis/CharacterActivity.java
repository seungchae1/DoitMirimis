package kr.hs.emirim.s2125.doitmirimis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterActivity extends AppCompatActivity {
    GridView gridv;
    public static boolean get_Char[]= new boolean[21];
    public static int imgId[]={R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7,
            R.drawable.img8, R.drawable.img9, R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15,
            R.drawable.img16,R.drawable.img17, R.drawable.img18,R.drawable.img19, R.drawable.img20, R.drawable.img21};
    public static String imgName[]={"책읽는 미리미", "과제하는 미리미","고양이 탈을 쓴 미리미", "우울한 미리미",
            "밥먹는 미리미", "청소하는 미리미", "그림그리는 미리미", "수줍어하는 미리미", "졸고있는 미리미", "체육하는 미리미", "더워하는 미리미", "추워하는 미리미", "노래하는 미리미",
            "가을타는 미리미", "셀카찍는 미리미", "상받는 미리미", "꽃보는 미리미", "배드민턴치는 미리미", "아픈 미리미", "매점다녀온 미리미", "수영복입은 미리미"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애는 거임!! 지우지 마셈!!
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_character);
        gridv = findViewById(R.id.gridv);
        charAdapter adapter= new charAdapter(this);
        gridv.setAdapter(adapter);

        ImageButton home = findViewById(R.id.btn_home);
        ImageButton back = findViewById(R.id.btn_back);
        home.setOnClickListener(btnListener);
        back.setOnClickListener(btnListener);

        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        for(int i=0; i<21; i++)
        {
            String input_img = "img";
            input_img=input_img.concat(Integer.toString(i));
            get_Char[i]=sharedPreferences.getBoolean(input_img, false);
        }
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
                    intent = getIntent();
                    String back = intent.getExtras().getString("back");
                    if(back.equals("start"))
                    {
                        intent = new Intent(CharacterActivity.this, StartActivity.class);
                    }
                    else if(back.equals("main"))
                    {
                        intent = new Intent(CharacterActivity.this, MainActivity.class);
                    }
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
        public int getCount() { return 21;}

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
            if(get_Char[i])
            {
                imgv.setImageResource(imgId[i]);
                imgv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(CharacterActivity.this);
                        View dlgView = View.inflate(CharacterActivity.this, R.layout.click_char,null);
                        ImageView imgvDlg = dlgView.findViewById(R.id.click_img);
                        imgvDlg.setImageResource(imgId[i]);
                        TextView textDlg = dlgView.findViewById(R.id.char_text);
                        textDlg.setText(imgName[i]);
                        dlg.setView(dlgView);
                        dlg.setNegativeButton("close",null);
                        dlg.show();
                    }
                });
            }
            else imgv.setImageResource(R.drawable.char_x);
            return imgv;
        }
    }
}