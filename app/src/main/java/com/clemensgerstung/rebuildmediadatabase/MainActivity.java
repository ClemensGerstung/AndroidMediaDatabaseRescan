package com.clemensgerstung.rebuildmediadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
//        File file = new File("/storage/emulated/0/Pictures/A7III/");
//
//        SingleMediaScanner scanner = new SingleMediaScanner(this, file);

        SelectFileBottomSheetFragment selector = new SelectFileBottomSheetFragment();
        selector.show(getSupportFragmentManager(), "FileSelector");
    }
}
