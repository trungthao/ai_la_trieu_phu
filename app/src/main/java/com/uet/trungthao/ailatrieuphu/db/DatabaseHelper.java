package com.uet.trungthao.ailatrieuphu.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.uet.trungthao.ailatrieuphu.model.Question;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by JiH on 11/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ailatrieuphu.sqlite";
    private static final String DATABASE_PATH = "/data/data/com.uet.trungthao.ailatrieuphu/databases/";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private final Context mContext;

    private static final String TABLE_NAME = "Question";
    private static final String QUESTION_COLUMN = "question";
    private static final String LEVEL_COLUMN = "level";
    private static final String CASE_A_COLUMN = "casea";
    private static final String CASE_B_COLUMN = "caseb";
    private static final String CASE_C_COLUMN = "casec";
    private static final String CASE_D_COLUMN = "cased";
    private static final String TRUE_CASE_COLUMN = "truecase";

    public DatabaseHelper(Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = mContext;
    }

    public void createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        if (!dbExists) {
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    private void copyDatabase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        try {
            checkDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e) {
        }

        if (checkDB != null) {
            checkDB.close();
            return true;
        }

        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void openDatabase() {
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public Question getQuestion(String level) {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openDatabase();
        Cursor cursor = db.query(TABLE_NAME
        , new String[] {QUESTION_COLUMN, CASE_A_COLUMN, CASE_B_COLUMN, CASE_C_COLUMN, CASE_D_COLUMN, TRUE_CASE_COLUMN}
        , LEVEL_COLUMN + "=?"
        , new String[] {level}
        , null, null, null, null);

        int n = new Random().nextInt(cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < n; i++) {
                cursor.moveToNext();
            }
        }

        int questionIndex = cursor.getColumnIndexOrThrow(QUESTION_COLUMN);
        int caseAIndex = cursor.getColumnIndexOrThrow(CASE_A_COLUMN);
        int caseBIndex = cursor.getColumnIndexOrThrow(CASE_B_COLUMN);
        int caseCIndex = cursor.getColumnIndexOrThrow(CASE_C_COLUMN);
        int caseDIndex = cursor.getColumnIndexOrThrow(CASE_D_COLUMN);
        int trueCaseIndex = cursor.getColumnIndexOrThrow(TRUE_CASE_COLUMN);

        Question question = new Question(cursor.getString(questionIndex)
        , cursor.getString(caseAIndex)
        , cursor.getString(caseBIndex)
        , cursor.getString(caseCIndex)
        , cursor.getString(caseDIndex)
        , cursor.getInt(trueCaseIndex));

        return question;
    }
}
