package com.example.cj.customtoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cj.toastlibrary.UTCustomToast;
import com.cj.toastlibrary.UTToast;


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
//                ToastUtil.common("普通弱提示");
                UTToast.success(getApplicationContext(), UTCustomToast.LEFT);
//                Toast.makeText(getApplicationContext(), "1212313", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_fail:
                UTToast.fail(getApplicationContext(),UTCustomToast.LEFT);
                break;
            case R.id.btn_remind:
                UTToast.remind(getApplicationContext(),UTCustomToast.LEFT);
                break;
            case R.id.btn_success:
                UTToast.warn(getApplicationContext(),UTCustomToast.LEFT);
                break;
            case R.id.btn_warn:
                Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
