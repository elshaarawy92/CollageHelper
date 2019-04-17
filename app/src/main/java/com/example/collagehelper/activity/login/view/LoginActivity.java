package com.example.collagehelper.activity.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.login.presenter.LoginPresenter;
import com.example.collagehelper.activity.main.view.MainActivity;
import com.example.collagehelper.activity.regist.view.RegistActivity;
import com.example.collagehelper.base.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create by liang on 2019/03/06
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    private CircleImageView civLoginHead;
    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForget;
    private TextView tvRegist;
    private RadioButton rbCustomer;
    private RadioButton rbSeller;
    private ImageView ivShowPwd;

    private LoginPresenter loginPresenter;
    private boolean isChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hideActionBar();
        setTittle("登录");
        initView();
        hideRightImage();
        clickEvent();
    }

    private void initView(){
        civLoginHead = findViewById(R.id.civ_login_head);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForget = findViewById(R.id.tv_forget);
        tvRegist = findViewById(R.id.tv_regist);
        civLoginHead.setImageResource(R.drawable.logo);
        rbCustomer = findViewById(R.id.rb_login_customer);
        rbSeller = findViewById(R.id.rb_login_seller);
        ivShowPwd = findViewById(R.id.iv_login_show_pwd);
        loginPresenter = new LoginPresenter(this);
    }

    private void clickEvent(){
        proxyOnClickListener(2, tvRegist, new MyClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivityForResult(intent,1);
            }
        });
        proxyOnClickListener(2, btnLogin, new MyClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String pwd = etPassword.getText().toString();
                String type;
                if (rbCustomer.isChecked()){
                    type = "0";
                }else {
                    type = "1";
                }
                loginPresenter.login(phone,pwd,type);
            }
        });
        ivShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = !isChecked;
                if (isChecked){
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.length());
            }
        });
    }

    @Override
    public void onBackPressed() {
        clickTwiceToExit();
    }

    @Override
    public void pwdWrong() {
        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void typeWrong() {
        Toast.makeText(LoginActivity.this,"登陆方式错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        BaseActivity.phone = etPhone.getText().toString().trim();
        intent.putExtra("phone",etPhone.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userNotExist() {
        Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
    }
}