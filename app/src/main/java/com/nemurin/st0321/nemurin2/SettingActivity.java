package com.nemurin.st0321.nemurin2;

import android.content.DialogInterface;
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

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    TextView foodSetNm;
    ImageButton foodNumberPicker;
    Button save_button;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //food設定項目の特定
        foodSetNm= (TextView) findViewById(R.id.food_set_num);

        // 保存データを読み込み、food設定項目に設定
        pref = getSharedPreferences("pref_data", MODE_PRIVATE);
        String foodDb = pref.getString("foodInput","30");
        foodSetNm.setText(foodDb);
        save_button = (Button)findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            //保存ボタンが押されたら、データを保存する
            @Override
            public void onClick(View v) {
                Editor editor = pref.edit();
                // Key: input, value: text
                editor.putString("foodInput", (String)foodSetNm.getText());
                editor.apply();
            }
        });

        //
        foodNumberPicker = (ImageButton)findViewById(R.id.food_num_pick);
        foodNumberPicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        numberPickerDialog();

    }

    private void numberPickerDialog(){
        NumberPicker fdNumberPickLg = new NumberPicker(this);
        fdNumberPickLg.setMaxValue(120);
        fdNumberPickLg.setMinValue(0);
        NumberPicker.OnValueChangeListener fdChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                foodSetNm.setText(""+newVal);
            }
        };
        fdNumberPickLg.setOnValueChangedListener(fdChangedListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(fdNumberPickLg);
        builder.setTitle("Change").setIcon(R.mipmap.dialog_info);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }


}
