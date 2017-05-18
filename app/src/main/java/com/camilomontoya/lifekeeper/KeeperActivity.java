package com.camilomontoya.lifekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.camilomontoya.lifekeeper.other.ControlTipografia;

public class KeeperActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView title,content;
    private ImageButton start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeper);

        toolbar = (Toolbar) findViewById(R.id.toolbar_keeper);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = (TextView) findViewById(R.id.title_keeper);
        content = (TextView) findViewById(R.id.content_keeper);

        title.setTypeface(ControlTipografia.getInstance().getTypeTitle());
        content.setTypeface(ControlTipografia.getInstance().getTypeMsg());

        start = (ImageButton) findViewById(R.id.begin_keeper);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
