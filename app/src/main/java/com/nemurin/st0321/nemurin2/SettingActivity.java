package com.nemurin.st0321.nemurin2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.IdentityHashMap;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    /** food計算の画面設定値*/
    TextView foodSetNm;

    /** shower計算の画面設定値*/
    TextView showerSetNm;

    /** hobby計算の画面設定値*/
    TextView hobbySetNm;

    /**PrefファイルのfoodDb項目への保存値*/
    String foodDb;

    /**PrefファイルのshowerDb項目への保存値*/
    String showerDb;

    /**PrefファイルのhobbyDb項目への保存値*/
    String hobbyDb;

    /**Prefファイルのアクセスインスタンス*/
    SharedPreferences pref;


    //どの項目のNumberPickerが選択されたかのフラグ
    private static final String foodFlag = "foodFlag";
    private static final String showerFlag = "showerFlag";
    private static final String hobbyFlag = "hobbyFlag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //値の設定先の特定
        foodSetNm= (TextView) findViewById(R.id.food_set_num);
        showerSetNm= (TextView) findViewById(R.id.shower_set_num);
        hobbySetNm= (TextView) findViewById(R.id.hobby_set_num);

        // 保存データを読み込み、food、shower、hobby設定項目に設定
        pref = getSharedPreferences("pref_data", MODE_PRIVATE);
        foodDb = pref.getString("foodInput",PrefTimeEnum.DEFTIME.getString());
        foodSetNm.setText(foodDb);

        showerDb = pref.getString("showerInput",PrefTimeEnum.DEFTIME.getString());
        showerSetNm.setText(showerDb);


        hobbyDb = pref.getString("hobbyInput",PrefTimeEnum.DEFTIME.getString());
        hobbySetNm.setText(hobbyDb);


        /*SAVEボタンがクリックされた場合の処理。
        *NumberPickerで設定した値を保存する。
        */
        Button save_button = (Button)findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            //保存ボタンが押されたら、データを保存する
            @Override
            public void onClick(View v) {
                Editor editor = pref.edit();
                editor.putString("foodInput", (String)foodSetNm.getText());
                editor.putString("showerInput", (String)showerSetNm.getText());
                editor.putString("hobbyInput", (String)hobbySetNm.getText());
                editor.apply();

                //MainActivityに戻る
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //NumberPickerをボタンごとに出力する。
        ImageButton foodNumberPicker = (ImageButton)findViewById(R.id.food_num_pick);
        ImageButton showerNumberPicker = (ImageButton)findViewById(R.id.shower_num_pick);
        ImageButton hobbyNumberPicker = (ImageButton)findViewById(R.id.hobby_num_pick);

        foodNumberPicker.setOnClickListener(this);
        showerNumberPicker.setOnClickListener(this);
        hobbyNumberPicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String flag = "def";
        switch (v.getId()){
            case R.id.food_num_pick:
                flag = foodFlag;
                numberPickerDialog(flag);
                break;

            case R.id.shower_num_pick:
                flag = showerFlag;
                numberPickerDialog(flag);
                break;
            case R.id.hobby_num_pick:
                flag = hobbyFlag;
                numberPickerDialog(flag);
                break;

        }


    }

    private void numberPickerDialog(final String flag){
        NumberPicker NumberPickLg = new NumberPicker(this);

        //NumberPickerの最大値、最小値、初期値を設定
        NumberPickLg.setMaxValue(120);
        NumberPickLg.setMinValue(0);

        NumberPicker.OnValueChangeListener changedListener = null;

        switch(flag) {
            case foodFlag:
                NumberPickLg.setValue(Integer.parseInt(foodDb));
                changedListener = new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        foodSetNm.setText("" + newVal);
                    }
                };
                break;

            case showerFlag:
                NumberPickLg.setValue(Integer.parseInt(showerDb));
                changedListener = new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        showerSetNm.setText("" + newVal);
                    }
                };
                break;

            case hobbyFlag:
                NumberPickLg.setValue(Integer.parseInt(hobbyDb));
                changedListener = new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        hobbySetNm.setText("" + newVal);
                    }
                };
                break;

        }
        NumberPickLg.setOnValueChangedListener(changedListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(NumberPickLg);
        builder.setTitle("Change").setIcon(R.mipmap.dialog_info);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

}
