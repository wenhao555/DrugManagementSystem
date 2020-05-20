package com.example.drugmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ImageView xf_account, xf_password;
    private EditText accountEdit, passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        initSpeech();
        xf_password = (ImageView) findViewById(R.id.xf_password);
        xf_account = (ImageView) findViewById(R.id.xf_account);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        xf_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechDialog();
            }
        });
        xf_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechDialog2();
            }
        });
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = DataSupport.findAll(User.class);

                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                for (User user : users) {
                    if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    } else if (user.getAccount().equals(DataSupport.findLast(User.class).getAccount())) {
                        Toast.makeText(LoginActivity.this, "账户或密码错误，请重新登录", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }


    private Toast mToast;

    private void initSpeech() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5ec22eb1");
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    private RecognizerDialog mIatDialog;

    private void speechDialog() {
        mIatDialog = new RecognizerDialog(LoginActivity.this, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);
        mIatDialog.show();
        showTip("开始语音转换");
    }

    private void speechDialog2() {
        mIatDialog = new RecognizerDialog(LoginActivity.this, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener2);
        mIatDialog.show();
        showTip("开始语音转换");
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {

            printResult(results);

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));

        }

    };
    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener2 = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {

            printResult2(results);

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));

        }

    };
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        accountEdit.setText(resultBuffer.toString());
        accountEdit.setSelection(accountEdit.length());
    }

    private void printResult2(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        passwordEdit.setText(resultBuffer.toString());
        passwordEdit.setSelection(passwordEdit.length());
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            }
        }
    };

    private void showTip(final String str) {

        mToast.setText(str);
        mToast.show();
    }

}