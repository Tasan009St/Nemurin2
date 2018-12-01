package com.nemurin.st0321.nemurin2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private TextView textView3;
    private TextView date;
    private String showDate;
    private Calendar calendar;
    private static final int foodDef = 40;
    private static final int showerDef = 40;
    private static final int hobbyDef = 40;
    private static final int noInput = 0;
    private static final int defNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        Button button = findViewById(R.id.button);
        TextView foodtimeFirst = findViewById(R.id.food_time);
        TextView showertimeFirst = findViewById(R.id.shower_time);
        TextView hobbytimeFirst = findViewById(R.id.hobby_time);
        TextView sleeptimeFirst = findViewById(R.id.sleep_time);

        //設定分の初期表示
        EditText foodcalcFirst = (EditText) findViewById(R.id.food_calc);
        EditText showercalcFirst = (EditText) findViewById(R.id.shower_calc);
        EditText hobbycalcFirst = (EditText) findViewById(R.id.hobby_calc);
        foodcalcFirst.setText(Integer.toString(foodDef));
        showercalcFirst.setText(Integer.toString(showerDef));
        hobbycalcFirst.setText(Integer.toString(hobbyDef));

        //Formatの設定
        DateFormat hhmmFormat = new SimpleDateFormat("HH:mm");

        //現在時刻の計算
        Calendar openTime = Calendar.getInstance();
        openTime.getTime();

        //現在時刻からデフォルト値のdinnerDefを足し、FoodTimeを求める
        openTime.add(Calendar.MINUTE, foodDef);
        String dinner = hhmmFormat.format(openTime.getTime());
        foodtimeFirst.setText(dinner);

        //FoodTimeからデフォルト値のshowerDefを足し、ShowerTimeを求める
        openTime.add(Calendar.MINUTE, showerDef);
        String shower = hhmmFormat.format(openTime.getTime());
        showertimeFirst.setText(shower);

        //ShowerTimeからデフォルト値のhobbyDefを足し、HobbyTimeを求める
        openTime.add(Calendar.MINUTE, hobbyDef);
        String hobby = hhmmFormat.format(openTime.getTime());
        hobbytimeFirst.setText(hobby);

        //sleep timeの表示
        sleeptimeFirst.setText(hobby);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText foodcalc = (EditText) findViewById(R.id.food_calc);
                EditText showercalc = (EditText) findViewById(R.id.shower_calc);
                EditText hobbycalc = (EditText) findViewById(R.id.hobby_calc);

                //ユーザー入力がない場合は、noInputが適用される。
                //ユーザー入力値がある場合は、Food Shower Hobbyの所要時間に設定

                int foodMin =defNumber;
                int showerMin=defNumber;
                int hobbyMin=defNumber;

                if (StringUtils.isBlank(foodcalc.getText())) {
                    foodcalc.setText(String.valueOf(noInput));
                } else {
                    foodMin = Integer.parseInt(foodcalc.getText().toString());
                }

                if (StringUtils.isBlank(foodcalc.getText())) {
                    showercalc.setText(String.valueOf(noInput));
                } else {
                    showerMin = Integer.parseInt(showercalc.getText().toString());
                }

                if (StringUtils.isBlank(foodcalc.getText())) {
                    hobbycalc.setText(String.valueOf(noInput));
                } else {
                    hobbyMin = Integer.parseInt(hobbycalc.getText().toString());
                }

                //Formatの設定
                DateFormat hhmmFormat = new SimpleDateFormat("HH:mm");

                TextView foodtime = findViewById(R.id.food_time);
                TextView showertime = findViewById(R.id.shower_time);
                TextView hobbytime = findViewById(R.id.hobby_time);
                TextView sleeptime = findViewById(R.id.sleep_time);



                //現在時刻の取得
                Calendar nowtime = Calendar.getInstance();
                nowtime.getTime();


                //foodの計算
                nowtime.add(Calendar.MINUTE, foodMin);
                String dinner = hhmmFormat.format(nowtime.getTime());
                foodtime.setText(dinner);

                //showerの計算
                nowtime.add(Calendar.MINUTE, showerMin);
                String shower = hhmmFormat.format(nowtime.getTime());
                showertime.setText(shower);

                //hobbyの計算
                nowtime.add(Calendar.MINUTE, hobbyMin);
                String hobby = hhmmFormat.format(nowtime.getTime());
                hobbytime.setText(hobby);

                //sleep timeの表示
                sleeptime.setText(hobby);
            }
        });


    }
    //今後やること


    //設定ボタンを描画
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //設定ボタン押下後の遷移先を指定
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
        return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

