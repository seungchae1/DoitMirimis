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
import android.widget.TextView;

public class CharacterActivity extends AppCompatActivity {
    GridView gridv;
    public static boolean get_Char[]= new boolean[21];
    public static int imgId[]={R.drawable.img, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
    public static String imgName[]={"책읽는 미리미", "과제하는 미리미","고양이 탈을 쓴 미리미", "우울한 미리미",
            "밥먹는 미리미", "청소하는 미리미"};
    int back = R.drawable.char_o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*for(int i=0; i<get_Char.length; i++)
        {
            get_Char[i]=false;
        }*/
        setContentView(R.layout.activity_character);
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
                        imgvDlg.setImageResource(imgId[0]);
                        TextView textDlg = dlgView.findViewById(R.id.char_text);
                        textDlg.setText(imgName[0]);
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