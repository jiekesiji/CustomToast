package com.example.cj.customtoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common:
                ToastUtil.common("普通弱提示");
                break;
            case R.id.btn_fail:
                ToastUtil.fail("操作失败");
                break;
            case R.id.btn_remind:
                ToastUtil.remind("提醒一下");
                break;
            case R.id.btn_success:
                new Thread(){
                    @Override
                    public void run() {
                        ToastUtil.success("操作成功");
                    }
                }.start();
                break;
            case R.id.btn_warn:
                new Thread() {
                    @Override
                    public void run() {
                        ToastUtil.warn("严重警告");
                    }
                }.start();

                break;
        }
    }

}
