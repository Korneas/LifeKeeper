package com.camilomontoya.lifekeeper;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogActivity extends AppCompatActivity {

    private EditText mail,pass;
    private ImageButton log;
    private TextView addAccount;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        mail = (EditText) findViewById(R.id.mail_log);
        pass = (EditText) findViewById(R.id.pass_log);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Catamaran-Light.ttf");
        mail.setTypeface(type);
        pass.setTypeface(type);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LogActivity.this, HomeActivity.class));
            finish();
        }

        log = (ImageButton) findViewById(R.id.login_button);

        addAccount = (TextView) findViewById(R.id.addAccount);

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogActivity.this,RegisterActivity.class));
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mMail = mail.getText().toString();
                final String mPass = pass.getText().toString();

                if(TextUtils.isEmpty(mMail)){
                    aviso("Escribe tu correo");
                    return;
                } else if(TextUtils.isEmpty(mPass)){
                    aviso("Escribe tu contraseña");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(mMail,mPass).addOnCompleteListener(LogActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if(!task.isSuccessful()){
                            if(mPass.length()<6){
                                aviso("La contraseña requerida es de 6 o más dígitos");
                            } else {
                                aviso("Error de autenticación");
                            }
                        } else {
                            startActivity(new Intent(LogActivity.this,HomeActivity.class));
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
