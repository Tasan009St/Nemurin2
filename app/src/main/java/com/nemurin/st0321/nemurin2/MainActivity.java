package com.nemurin.st0321.nemurin2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    /**Prefファイルのアクセスインスタンス*/
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView foodtimeFirst = findViewById(R.id.food_time);
        TextView showertimeFirst = findViewById(R.id.shower_time);
        TextView hobbytimeFirst = findViewById(R.id.hobby_time);
        TextView sleeptimeFirst = findViewById(R.id.sleep_time);

        //設定分の初期表示
        EditText foodcalcFirst = (EditText) findViewById(R.id.food_calc);
        EditText showercalcFirst = (EditText) findViewById(R.id.shower_calc);
        EditText hobbycalcFirst = (EditText) findViewById(R.id.hobby_calc);


        // 保存データを読み込み、food、shower、hobby設定項目に設定
        pref = getSharedPreferences("pref_data", MODE_PRIVATE);
        String foodDb = pref.getString("foodInput",PrefTimeEnum.DEFTIME.getString());
        foodcalcFirst.setText(foodDb);

        String showerDb = pref.getString("showerInput",PrefTimeEnum.DEFTIME.getString());
        showercalcFirst.setText(showerDb);


        String hobbyDb = pref.getString("hobbyInput",PrefTimeEnum.DEFTIME.getString());
        hobbycalcFirst.setText(hobbyDb);


        //Formatの設定
        DateFormat hhmmFormat = new SimpleDateFormat("HH:mm");

        //現在時刻の計算
        Calendar openTime = Calendar.getInstance();
        openTime.getTime();

        //現在時刻からデフォルト値/または設定値のfoodDbを足し、FoodTimeを求める
        openTime.add(Calendar.MINUTE, Integer.parseInt(foodDb));
        String food = hhmmFormat.format(openTime.getTime());
        foodtimeFirst.setText(food);

        //FoodTimeからデフォルト値/または設定値のshowerDbを足し、ShowerTimeを求める
        openTime.add(Calendar.MINUTE, Integer.parseInt(showerDb));
        String shower = hhmmFormat.format(openTime.getTime());
        showertimeFirst.setText(shower);

        //ShowerTimeからデフォルト値/または設定値のhobbyDb)を足し、HobbyTimeを求める
        openTime.add(Calendar.MINUTE, Integer.parseInt(hobbyDb));
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

                //ユーザー入力がない場合は、noInputを設定値に適用。
                if (StringUtils.isBlank(foodcalc.getText())) {
                    foodcalc.setText(PrefTimeEnum.NOINPUT.getString());
                }

                if (StringUtils.isBlank(foodcalc.getText())) {
                    showercalc.setText(PrefTimeEnum.NOINPUT.getString());
                }

                if (StringUtils.isBlank(foodcalc.getText())) {
                    hobbycalc.setText(PrefTimeEnum.NOINPUT.getString());
                }

                //入力設定値を取得。(ユーザー設定値がない場合はnoinputが取得される)
                int foodMin = Integer.parseInt(foodcalc.getText().toString());
                int showerMin = Integer.parseInt(showercalc.getText().toString());
                int hobbyMin = Integer.parseInt(hobbycalc.getText().toString());


                //Formatの設定
                DateFormat hhmmFormat = new SimpleDateFormat("HH:mm");

                TextView foodtime = findViewById(R.id.food_time);
                TextView showertime = findViewById(R.id.shower_time);
                TextView hobbytime = findViewById(R.id.hobby_time);
                TextView sleeptime = findViewById(R.id.sleep_time);



                //現在時刻の取得
                Calendar nowtime = Calendar.getInstance();
                nowtime.getTime();


                //表示する”～まで”のfoodの計算
                nowtime.add(Calendar.MINUTE, foodMin);
                String dinner = hhmmFormat.format(nowtime.getTime());
                foodtime.setText(dinner);

                //表示する”～まで”のshowerの計算
                nowtime.add(Calendar.MINUTE, showerMin);
                String shower = hhmmFormat.format(nowtime.getTime());
                showertime.setText(shower);

                //表示する”～まで”のhobbyの計算
                nowtime.add(Calendar.MINUTE, hobbyMin);
                String hobby = hhmmFormat.format(nowtime.getTime());
                hobbytime.setText(hobby);

                //sleep timeに、hobbyの値を表示
                sleeptime.setText(hobby);
            }
        });


    }

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
        finish();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

