package com.example.acer.crossword;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.util.ArrayList;

/**
 * Created by Shougy on 3/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Crossword.db";
    public static final String DBLOCATION = Environment.getDataDirectory() + "/data/com.example.acer.crossword/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public ArrayList<questions> get_All_IDS() {
        ArrayList<questions> arrayList = new ArrayList();
        int imgs[] = {R.drawable.house, R.drawable.calculater, R.drawable.finger, R.drawable.sea, R.drawable.disk, R.drawable.camel,
                R.drawable.mobile, R.drawable.yamen, R.drawable.headphone, R.drawable.egypt, R.drawable.penciel, R.drawable.mountaince,
                R.drawable.number, R.drawable.tones, R.drawable.train, R.drawable.coffee, R.drawable.china, R.drawable.banana,
                R.drawable.sharet, R.drawable.qater, R.drawable.ciliegia, R.drawable.screw, R.drawable.marwaha, R.drawable.bullet,
                R.drawable.candel, R.drawable.bank,  R.drawable.lawha, R.drawable.notebook, R.drawable.hadaba, R.drawable.lebanon,
                R.drawable.mokaef, R.drawable.banada,R.drawable.dora, R.drawable.sarden, R.drawable.stop, R.drawable.esfalet,
                R.drawable.lawha,  R.drawable.paris, R.drawable.chelsea, R.drawable.playarea, R.drawable.melan, R.drawable.chrom,
                R.drawable.mejhar, R.drawable.computer,R.drawable.cpture, R.drawable.canon, R.drawable.soldier, R.drawable.iphone,
                R.drawable.chees,  R.drawable.rehan,   R.drawable.turkey, R.drawable.switchs, R.drawable.zedan, R.drawable.heber,
                R.drawable.roof,   R.drawable.taba, R.drawable.link, R.drawable.jordan, R.drawable.oreo, R.drawable.river};
        openDatabase();
        Cursor res = mDatabase.rawQuery("select * from QUESTION", null);
        res.moveToFirst();
        int i = 0;
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex("ID"));
            String question = res.getString(res.getColumnIndex("question"));
            String right_ans = res.getString(res.getColumnIndex("Right_Answer"));
            int is_ans = res.getInt(res.getColumnIndex("IS_Answerd"));
            arrayList.add(new questions(id, question, right_ans, is_ans, imgs[i]));
            ++i;
            res.moveToNext();
        }
        res.close();
        closeDatabase();
        return arrayList;
    }

    public void change_level_scoore(int rate, int ID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Is_Answerd", rate);
        openDatabase();
        mDatabase.update("QUESTION", contentValues, "ID=?", new String[]{ID + ""});
    }
}

