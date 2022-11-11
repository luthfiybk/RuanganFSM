package com.example.ruangan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ruangan";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table_ruangan";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_KODE = "kode_ruangan";
    private static final String COLUMN_KAPASITAS = "kapasitas_ruangan";


    DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_KODE + "TEXT, " + COLUMN_KAPASITAS + "TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_NAME);
        onCreate(db);
    }

    void addRuang(String kodeRuang, String kapasitas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_KODE, kodeRuang);
        cv.put(COLUMN_KAPASITAS, kapasitas);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Gagal menambahkan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil menambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updatedata(String row_id, String kodeRuang, String kapasitas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_KODE, kodeRuang);
        cv.put(COLUMN_KAPASITAS, kapasitas);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Gagal menghapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
