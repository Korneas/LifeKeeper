package com.camilomontoya.lifekeeper;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText mail,pass, confirm;
    private ImageButton register;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail = (EditText) findViewById(R.id.mail_reg);
        pass = (EditText) findViewById(R.id.pass_reg);
        confirm = (EditText) findViewById(R.id.confirm_pass_reg);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Catamaran-Light.ttf");
        mail.setTypeface(type);
        pass.setTypeface(type);
        confirm.setTypeface(type);

        register = (ImageButton) findViewById(R.id.register);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mMail = mail.getText().toString();
                String mPass = pass.getText().toString();
                String mConfirm = confirm.getText().toString();

                if(TextUtils.isEmpty(mMail)){
                    aviso("Escribe tu correo");
                } else if(TextUtils.isEmpty(mPass)){
                    aviso("Escribe tu contraseña");
                } else if(mPass.length()<6){
                    aviso("La contraseña es muy corta");
                    return;
                } else if(!mPass.contentEquals(mConfirm)){
                    aviso("Las contraseñas no coinciden");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(mMail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            aviso("No se pudo realizar el registro");
                        } else {
                            aviso("Se registro correctamente");
                            startActivity(new Intent(RegisterActivity.this,LogActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }

    public void aviso(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
