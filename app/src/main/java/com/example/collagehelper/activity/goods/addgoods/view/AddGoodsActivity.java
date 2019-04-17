package com.example.collagehelper.activity.goods.addgoods.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;
import com.example.collagehelper.activity.goods.addgoods.presenter.AddGoodsPresenter;
import com.example.collagehelper.activity.regist.view.RegistActivity;
import com.example.collagehelper.base.BaseActivity;
import com.example.collagehelper.utils.BitmapUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by liang on 2019/04/16
 */
public class AddGoodsActivity extends BaseActivity implements IAddGoodsView{

    private ImageView ivGoodsImage;
    private EditText etGoodsName;
    private EditText etGoodsDes;
    private EditText etGoodsPrice;
    private Button btnAdd;

    private Uri imageUri;
    public static final int TAKE_PHOTO = 0;
    public static final int CHOOSE_PHOTO = 1;
    private Bitmap bitmap;
    private String name;
    private String des;
    private String price;
    private File file;

    private AddGoodsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        hideActionBar();
        setTittle("添加商品");
        presenter = new AddGoodsPresenter(this);
        initView();
        clickEvent();
    }

    public void initView(){
        ivGoodsImage = findViewById(R.id.iv_goods_image);
        etGoodsName = findViewById(R.id.et_goods_name);
        etGoodsDes = findViewById(R.id.et_goods_des);
        etGoodsPrice = findViewById(R.id.et_goods_price);
        btnAdd = findViewById(R.id.btn_add);
    }

    public void clickEvent(){
        proxyOnClickListener(2, ivGoodsImage, new MyClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(AddGoodsActivity.this)
                        .setTitle("请选择设置头像的方式")
                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (ContextCompat.checkSelfPermission(AddGoodsActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                    ActivityCompat.requestPermissions(AddGoodsActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
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
                                    imageUri = FileProvider.getUriForFile(AddGoodsActivity.this,"com.example.collagehelper.fileprovider",outputImage);
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

        proxyOnClickListener(2, btnAdd, new MyClickListener() {
            @Override
            public void onClick(View view) {
                name = etGoodsName.getText().toString().trim();
                des = etGoodsDes.getText().toString().trim();
                price = etGoodsPrice.getText().toString().trim();
                if (name == null){
                    Toast.makeText(AddGoodsActivity.this,"商品名称不能为空",Toast.LENGTH_SHORT).show();
                }else if (des == null){
                    Toast.makeText(AddGoodsActivity.this,"商品描述不能为空",Toast.LENGTH_SHORT).show();
                }else if (price == null){
                    Toast.makeText(AddGoodsActivity.this,"商品价格不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        file = BitmapUtils.saveFile(bitmap,phone);
                        while (file.length() / 1024 / 1024 >= 1){
                            bitmap = BitmapUtils.compressMatrix(bitmap);
                            file = BitmapUtils.saveFile(bitmap,phone);
                        }
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("goods_image",file.getName(),requestBody);
                        presenter.addGoods(BaseActivity.phone,name,price,des,part);
                    } catch (Exception e) {
                        Log.d("onClick", e.getMessage());
                    }
                }
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
                    Toast.makeText(AddGoodsActivity.this,"您拒绝了授权",Toast.LENGTH_SHORT).show();
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
                        ivGoodsImage.setBackground(new BitmapDrawable(bitmap));
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
            ivGoodsImage.setBackground(new BitmapDrawable(bitmap));
        }else {
            Toast.makeText(AddGoodsActivity.this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addSuccess() {
        Toast.makeText(AddGoodsActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void addFailure() {
        Toast.makeText(AddGoodsActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
        finish();
    }
}
