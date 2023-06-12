package com.hoangdai.lab5_ph36944;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;

import Lab8.ReadWirteUser;
import Lab8.User;

public class LoginActivity extends AppCompatActivity {
    Context context=this;
    CheckBox chk;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword =findViewById(R.id.edt_password);
        chk = findViewById(R.id.chkRemember);
        checkRememberUP();

        //String sUsername = getIntent().getStringExtra(RegisterActivity.KEY_USERNAME);
       // String sPassword = getIntent().getStringExtra(RegisterActivity.KEY_PASSWORD);

        //edtUsername.setText(sUsername);
       // edtPassword.setText(sPassword);

        Button btnRegister = findViewById(R.id.btnregister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đọc dữ liệu từ file
                ArrayList<User> lisUser = new ArrayList<>();
                ReadWirteUser readWriteUser = new ReadWirteUser(context);
                lisUser=readWriteUser.readUser(context,"user.txt");

                boolean isU=lisUser.get(0).getUser().equals(edtUsername.getText().toString());
                boolean isP=lisUser.get(0).getPass().equals(edtPassword.getText().toString());
                if (isU&&isP&&lisUser.get(0).getUser()!=""&&lisUser.get(0).getPass()!=""){
                    rememberUP(lisUser.get(0).getUser(),lisUser.get(0).getPass(), true);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ListStudent.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),"Đăng nhập khồn thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //hàm lưu dữ liệu vào SharePref(gọi khi đăng nhập thành công)
    public void rememberUP(String u, String p, boolean chkRemember){
        SharedPreferences s= getSharedPreferences("saveUP", MODE_PRIVATE);
        SharedPreferences.Editor editor=s.edit();
        editor.putString("user", u);
        editor.putString("pass", p);
        editor.putBoolean("chkRemember", chkRemember);
        editor.apply();
    }

    //hàm kiểm tra checkbox (gọi khi khởi động Activity)
    public void checkRememberUP(){
        SharedPreferences s = getSharedPreferences("saveUP", MODE_PRIVATE);
        String user = s.getString("user","");
        String pass = s.getString("pass", "");
        boolean isChkRememer = s.getBoolean("chkRemember", false);
        chk.setChecked(isChkRememer);
        if (chk.isChecked()){
            edtUsername.setText(user);
            edtPassword.setText(pass);
        }
    }

}