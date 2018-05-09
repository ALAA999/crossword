package com.example.acer.crossword;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    public static ArrayList<questions> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File database = getApplication().getDatabasePath(databaseHelper.DBNAME);
        if (database.exists() == false) {
            databaseHelper.getReadableDatabase();
            if (CopyDatabase(this)) {
                Toast.makeText(this, "تم نسخ بيانات قاعدة البيانات بنجاح", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "فشل نسخ بيانات قاعدة البيانات", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        arrayList = databaseHelper.get_All_IDS();
        for (int i = 0; i < arrayList.size(); i++) {
            Log.e("asd", arrayList.get(i).getQuestion());
        }
        Main_screen_fragment fragment1 = new Main_screen_fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_act, fragment1);
        transaction.commit();
    }


    public boolean CopyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(databaseHelper.DBNAME);
            String outFileName = databaseHelper.DBLOCATION + databaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
