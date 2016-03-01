package com.travel.personaltravel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.travel.personaltravel.R;
import com.travel.personaltravel.widget.ClearEditText;

public class UserRegisterFirstActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText userPhone;
    private String tel;

    @Override
    protected void onDestroy() {
        closeKeybord(userPhone);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_first_activity);
        userPhone = (ClearEditText) findViewById(R.id.f_user_phone_register_dt);
        //获取焦点
        userPhone.setFocusable(true);
        userPhone.setFocusableInTouchMode(true);
        userPhone.requestFocus();
        openKeybord(userPhone);
        TextView nextPress = (TextView) findViewById(R.id.f_user_press_next_tv);
        TextView backTv = (TextView) findViewById(R.id.f_register_title_bar_cancel_tv);
        nextPress.setOnClickListener(this);
        backTv.setOnClickListener(this);
        //TODO 后期考虑使用正则
        phoneNumAddSpace(userPhone);
    }

    //手机号码的格式
    public void phoneNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    int len = s.toString().length();
                    if (len == 3 || len == 8) {
                        userPhone.setText(s + " ");
                        userPhone.setSelection(userPhone.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                tel = userPhone.getText().toString();
                if (s.length() == 13) {
                }
            }
        });
    }

    /**
     * 打开软键盘
     *
     * @param editText
     */
    public void openKeybord(EditText editText) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param editText
     */
    public void closeKeybord(EditText editText) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_user_press_next_tv:
                closeKeybord(userPhone);
                Intent intent = new Intent(UserRegisterFirstActivity.this, UserRegisterLastActivity.class);
                intent.putExtra("uTel", tel);
                startActivity(intent);
                break;
            case R.id.f_register_title_bar_cancel_tv:
                closeKeybord(userPhone);
                finish();
                break;
        }
    }
}
