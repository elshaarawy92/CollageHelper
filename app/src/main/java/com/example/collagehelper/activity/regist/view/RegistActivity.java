package com.example.collagehelper.activity.regist.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.regist.presenter.RegistPresenter;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.dao.Ask;
import com.example.collagehelper.utils.AccountUtils;
import com.example.collagehelper.utils.BitmapUtils;
import com.example.collagehelper.widget.CameraCircleImageView;
import com.mob.MobSDK;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Create by liang on 2019/03/06
 */
public class RegistActivity extends BaseActivity implements IRegistView{

    private EditText etUsername;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etCode;
    private Button btnGetCode;
    private Button btnRegist;
    private ImageView ivShowPwd;
    private ImageView ivShowConfirmPwd;
    private RadioButton rbCustomer;
    private RadioButton rbSeller;
    private MyCountDownTimer countDownTimer;
    private CameraCircleImageView registHead;
    private EventHandler eventHandler;
    private boolean isChecked1 = false;
    private boolean isChecked2 = false;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 0;
    public static final int CHOOSE_PHOTO = 1;
    private RegistPresenter presenter;
    private Bitmap bitmap;
    private File file;
    private Handler handler;

    private String phone;
    private String pwd;
    private String pwdConfirm;
    private String username;
    private String code;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        MobSDK.init(RegistActivity.this);
        countDownTimer = new MyCountDownTimer(60 * 1000, 1000);
        presenter = new RegistPresenter(this);
        hideActionBar();
        initView();
        hideRightImage();
        setTittle("注册");
        clickEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        if (eventHandler != null){
            SMSSDK.unregisterEventHandler(eventHandler);
        }
    }

    private void clickEvent(){
        proxyOnClickListener(2, btnGetCode, new MyClickListener() {
            @Override
            public void onClick(View view) {
                phone = etPhone.getText().toString().trim();
                pwd = etPassword.getText().toString().trim();
                pwdConfirm = etConfirmPassword.getText().toString().trim();
                username = etUsername.getText().toString();
                presenter.checkAccountExists(phone);
                if (AccountUtils.isPhoneLegal(phone) && AccountUtils.isPwdLegal(pwd) && isPwdLengthLegal(pwd) && pwd.equals(pwdConfirm) && username != null){
                    eventHandler = new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                            Message msg = new Message();
                            msg.arg1 = event;
                            msg.arg2 = result;
                            msg.obj = data;
                            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                                @Override
                                public boolean handleMessage(Message msg) {
                                    int event = msg.arg1;
                                    int result = msg.arg2;
                                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                        if (result == SMSSDK.RESULT_COMPLETE) {
                                            Toast.makeText(RegistActivity.this,"发送验证码成功",Toast.LENGTH_SHORT).show();
                                            countDownTimer.start();
                                        } else {
                                            Toast.makeText(RegistActivity.this,"发送验证码失败",Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                        if (result == SMSSDK.RESULT_COMPLETE) {
                                            Message message = Message.obtain();
                                            message.what = 1;
                                            handler.sendMessage(message);
                                        } else {
                                            Toast.makeText(RegistActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    return false;
                                }
                            }).sendMessage(msg);
                        }
                    };
                    SMSSDK.registerEventHandler(eventHandler);
                }else if (username == null){
                    Toast.makeText(RegistActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if (!AccountUtils.isPhoneLegal(phone)){
                    Toast.makeText(RegistActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }else if (!AccountUtils.isPwdLegal(pwd) || !isPwdLengthLegal(pwd)){
                    Toast.makeText(RegistActivity.this,"请输入6-24位由字母和数字组成的密码",Toast.LENGTH_SHORT).show();
                }else if (!pwd.equals(pwdConfirm)){
                    Toast.makeText(RegistActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });
        proxyOnClickListener(2, btnRegist, new MyClickListener() {
            @Override
            public void onClick(View view) {
                code = etCode.getText().toString().trim();
                if (bitmap == null){
                    Toast.makeText(RegistActivity.this,"头像不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == null){
                    Toast.makeText(RegistActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                code = etCode.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                pwd = etPassword.getText().toString().trim();
                username = etUsername.getText().toString();
                SMSSDK.submitVerificationCode("86",phone,code);
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 1){
                            try {
                                file = BitmapUtils.saveFile(bitmap,phone);
                                while (file.length() / 1024 / 1024 >= 1){
                                    bitmap = BitmapUtils.compressMatrix(bitmap);
                                    file = BitmapUtils.saveFile(bitmap,phone);
                                }
                                String type;
                                if (rbCustomer.isChecked()){
                                    type = "0";
                                }else {
                                    type = "1";
                                }
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                                MultipartBody.Part part = MultipartBody.Part.createFormData("head",file.getName(),requestBody);
                                presenter.regist(username,phone,pwd,type,part);
                            } catch (Exception e) {
                                Log.d("onClick", e.getMessage());
                            }
                            Intent intent = new Intent();
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }
                };
            }
        });
        ivShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked1 = !isChecked1;
                if (isChecked1){
                    ivShowPwd.setBackgroundResource(R.drawable.pwd_show);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    ivShowPwd.setBackgroundResource(R.drawable.pwd_unshow);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.length());
            }
        });
        ivShowConfirmPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked2 = !isChecked2;
                if (isChecked2){
                    ivShowConfirmPwd.setBackgroundResource(R.drawable.pwd_show);
                    etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    ivShowConfirmPwd.setBackgroundResource(R.drawable.pwd_unshow);
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etConfirmPassword.setSelection(etConfirmPassword.length());
            }
        });
        proxyOnClickListener(2, registHead, new MyClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(RegistActivity.this)
                        .setTitle("请选择设置头像的方式")
                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (ContextCompat.checkSelfPermission(RegistActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                    ActivityCompat.requestPermissions(RegistActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                                }else {
                                    openAlbum();
                                }
                            }
                        })
                        .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                                try {
                                    if (outputImage.exists()){
                                        outputImage.delete();
                                    }
                                    outputImage.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (Build.VERSION.SDK_INT >= 24){
                                    imageUri = FileProvider.getUriForFile(RegistActivity.this,"com.example.collagehelper.fileprovider",outputImage);
                                }else {
                                    imageUri = Uri.fromFile(outputImage);
                                }

                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                startActivityForResult(intent,TAKE_PHOTO);
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(RegistActivity.this,"您拒绝了授权",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        registHead.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data);
                    }else {
                        handleImageBeforeKitkat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitkat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath != null){
            bitmap = BitmapFactory.decodeFile(imagePath);
            registHead.setImageBitmap(bitmap);
        }else {
            Toast.makeText(RegistActivity.this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(){
        etUsername = findViewById(R.id.et_regist_username);
        etPhone = findViewById(R.id.et_regist_phone);
        etPassword = findViewById(R.id.et_regist_password);
        etConfirmPassword = findViewById(R.id.et_regist_confirm_password);
        etCode = findViewById(R.id.et_regist_code);
        btnGetCode = findViewById(R.id.btn_get_code);
        btnRegist = findViewById(R.id.btn_regist);
        registHead = findViewById(R.id.cci_regist_head);
        ivShowPwd = findViewById(R.id.iv_show_pwd);
        ivShowConfirmPwd = findViewById(R.id.iv_show_confirm_pwd);
        rbCustomer = findViewById(R.id.rb_customer);
        rbSeller = findViewById(R.id.rb_seller);
    }

    private boolean isPwdLengthLegal(String pwd){
        if (pwd.length() < 6 || pwd.length() > 24){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void pwdExist() {
        Toast.makeText(RegistActivity.this,"手机号已注册",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdNotExist() {
        SMSSDK.getVerificationCode("86", phone);
    }

    @Override
    public void registSuccess() {
        Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registFailure() {
        Toast.makeText(RegistActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
    }

    class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnGetCode.setText((int)millisUntilFinished / 1000 + "秒后获取");
            btnGetCode.setEnabled(false);
            btnGetCode.setBackgroundResource(R.drawable.button_ban);
        }

        @Override
        public void onFinish() {
            btnGetCode.setBackgroundResource(R.drawable.login_button_type);
            btnGetCode.setText("获取验证码");
            btnGetCode.setEnabled(true);
        }
    }
}
