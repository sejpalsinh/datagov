package com.example.demo_tebbed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SqliteDatabse extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hospital.db";
    public static final String TABLE_HOSPITAL = "hospital";
    public static final String COL_1 = "h_id";
    public static final String COL_2 = "h_name";
    public static final String COL_3 = "h_pgflag";
    public static final String COL_4 = "h_address";
    public static final String COL_5 = "h_state";
    public static final String COL_6 = "h_dist";
    public static final String COL_7 = "h_number";
    public static final String COL_8 = "h_email";
    public static final String COL_9 = "h_website";
    public static final String COL_10 = "h_location";
    public static final String COL_11 = "h_time";

    public static final String TABLE_FACILITIES = "facilities";
    public static final String FCOL_1 = "f_id";
    public static final String FCOL_2 = "f_name";
    public static final String FCOL_3 = "h_id";

    public static final String TABLE_DOCTOR = "doctor";
    public static final String DCOL_1 = "d_id";
    public static final String DCOL_2 = "d_name";
    public static final String DCOL_3 = "d_email";
    public static final String DCOL_4 = "d_details";
    public static final String DCOL_5 = "d_speciality";
    public static final String DCOL_6 = "h_id";

    public static final String TABLE_BLOODBANK = "bloodbank";
    public static final String BCOL_1 = "b_id";
    public static final String BCOL_2 = "b_name";
    public static final String BCOL_3 = "b_email";
    public static final String BCOL_4 = "b_address";
    public static final String BCOL_5 = "b_website";
    public static final String BCOL_6 = "b_location";
    public static final String BCOL_7 = "b_number";
    public static final String BCOL_8 = "b_state";
    public static final String BCOL_9 = "b_dist";
    public static final String BCOL_10 = "b_ap";
    public static final String BCOL_11 = "b_an";
    public static final String BCOL_12 = "b_bp";
    public static final String BCOL_13 = "b_bn";
    public static final String BCOL_14 = "b_op";
    public static final String BCOL_15 = "b_on";
    public static final String BCOL_16 = "b_abp";
    public static final String BCOL_17 = "b_abn";


    public SqliteDatabse(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSPITAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOODBANK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACILITIES);
        String createDB = "CREATE TABLE " + TABLE_HOSPITAL + " ("+COL_1 + " INTEGER PRIMARY KEY, " +COL_2+ " TEXT, "+ COL_3+ " TEXT, "+ COL_4+ " TEXT, "+ COL_5+ " TEXT, "+ COL_6+ " TEXT, "+ COL_7+ " TEXT, "+ COL_8+ " TEXT, "+ COL_9+ " TEXT, "+ COL_10+ " TEXT, "+ COL_11+ " TEXT)";
        Log.i("marudatabase",createDB);
        db.execSQL(createDB);
       String createF = "CREATE TABLE " + TABLE_FACILITIES + " ("+FCOL_1 + " INTEGER PRIMARY KEY , " +FCOL_2+ " TEXT, "+ FCOL_3+ " INTEGER)";
        db.execSQL(createF);
       String createD = "CREATE TABLE " + TABLE_DOCTOR + " ("+DCOL_1 + " INTEGER PRIMARY KEY, " +DCOL_2+ " TEXT, "+ DCOL_3+ " TEXT, "+ DCOL_4+ " TEXT, "+ DCOL_5+ " TEXT, "+ DCOL_6+ " INTEGER)";
       db.execSQL(createD);
      String createB = "CREATE TABLE " + TABLE_BLOODBANK + " ("+BCOL_1 + " INTEGER PRIMARY KEY, " +BCOL_2+ " TEXT, "+ BCOL_3+ " TEXT, "+BCOL_4+ " TEXT, "+ BCOL_5+ " TEXT, "+ BCOL_6+ " TEXT, "+ BCOL_7+ " TEXT, "+ BCOL_8+ " TEXT, "+ BCOL_9+ " TEXT, "+ BCOL_10+ " INTEGER, "+ BCOL_11+ " INTEGER, "+ BCOL_12+ " TEXT, "+ BCOL_13+ " INTEGER, "+ BCOL_14+ " INTEGER, "+ BCOL_15+ " INTEGER, "+ BCOL_16+ " INTEGER, "+ BCOL_17+ " INTEGER)";
        db.execSQL(createB);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

    public boolean addHospital(int hid,String hname,String hpgflag,String haddress,String hstate,String hdist,String hnumber,String hemail,String hwebsite ,String hlocation,String htime){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,hid);
        contentValues.put(COL_2,hname);
        contentValues.put(COL_3,hpgflag);
        contentValues.put(COL_4,haddress);
        contentValues.put(COL_5,hstate);
        contentValues.put(COL_6,hdist);
        contentValues.put(COL_7,hnumber);
        contentValues.put(COL_8,hemail);
        contentValues.put(COL_9,hwebsite);
        contentValues.put(COL_10,hlocation);
        contentValues.put(COL_11,htime);

        long result = db.insert(TABLE_HOSPITAL,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }




    public boolean addFacilities(int fid,String fname,int hid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FCOL_1,fid);
        contentValues.put(FCOL_2,fname);
        contentValues.put(FCOL_3,hid);
        long result = db.insert(TABLE_FACILITIES,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addDoctor(int did,String dname,String demail,String ddetails,String dspeciality,int hid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_1,did);
        contentValues.put(DCOL_2,dname);
        contentValues.put(DCOL_3,demail);
        contentValues.put(DCOL_4,ddetails);
        contentValues.put(DCOL_5,dspeciality);
        contentValues.put(DCOL_6,hid);
        long result = db.insert(TABLE_DOCTOR,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean addBloodbank(int bid,String bname,String bemail,String baddress,String bwebsite,String blocation,String bnumber,String bstate,String bdist,int bap,int ban,int bbp,int bbn,int bop,int bon,int babp,int babn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BCOL_1,bid);
        contentValues.put(BCOL_2,bname);
        contentValues.put(BCOL_3,bemail);
        contentValues.put(BCOL_4,baddress);
        contentValues.put(BCOL_5,bwebsite);
        contentValues.put(BCOL_6,blocation);
        contentValues.put(BCOL_7,bnumber);
        contentValues.put(BCOL_8,bstate);
        contentValues.put(BCOL_9,bdist);
        contentValues.put(BCOL_10,bap);
        contentValues.put(BCOL_11,ban);
        contentValues.put(BCOL_12,bbp);
        contentValues.put(BCOL_13,bbn);
        contentValues.put(BCOL_14,bop);
        contentValues.put(BCOL_15,bon);
        contentValues.put(BCOL_16,babp);
        contentValues.put(BCOL_17,babn);

        long result = db.insert(TABLE_BLOODBANK,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public String showHospitals(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_HOSPITAL, null);
        String r = cursorToString(res);
        res.close();
        return r;

    }

    public String showDoctorName(int h_id){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT "+ DCOL_1 + ", " + DCOL_2 + " FROM "+ TABLE_DOCTOR + " WHERE " + DCOL_6 + " = " + h_id, null);
        String r = cursorToString(res);
        res.close();
        return r;

    }

    public String showBloodbank(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_BLOODBANK, null);
        String r = cursorToString(res);
        res.close();
        return r;
    }

    public String ShowDoctorData(int h_id){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT "+ DCOL_2 + ", " + DCOL_3 + ", " + DCOL_5 + ", " + DCOL_4 + " FROM " + TABLE_DOCTOR + " WHERE " + DCOL_6 + " = " + h_id, null);
        String r = cursorToString(res);
        res.close();
        return r;
    }

    public String showSelectedHospitals(String state, String district){
        SQLiteDatabase db =this.getWritableDatabase();
        //Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_HOSPITAL, null);

        if(district == null){
            Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_HOSPITAL + " WHERE " + COL_5 + " LIKE '%" + state + "%'", null);
            String resSecHos = cursorToString(res);
            res.close();
            return resSecHos;
        } else {
            Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_HOSPITAL + " WHERE " + COL_5 + " LIKE '%" + state + "%' " + " AND " + COL_6 + " LIKE '%" + district + "%'", null);
            String resSecHos = cursorToString(res);
            res.close();
            return resSecHos;

        }





    }




    public void deleteAll()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSPITAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOODBANK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACILITIES);
        onCreate(db);
        db.close();

    }
    public String showHospi(int i){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_HOSPITAL +" WHERE h_id=" + i , null);
        String r = cursorToString(res);
        return r;

    }
    public String showFacilities(int i){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_FACILITIES +" WHERE h_id=" + i , null);
        String r = cursorToString(res);
        return r;

    }


    public String cursorToString(Cursor crs) {
        JSONArray arr = new JSONArray();
        crs.moveToFirst();
        while (!crs.isAfterLast()) {
            int nColumns = crs.getColumnCount();
            JSONObject row = new JSONObject();

            for (int i = 0 ; i < nColumns ; i++) {
                String colName = crs.getColumnName(i);
                if (colName != null) {
                    String val = "";
                    try {
                        switch (crs.getType(i)) {
                            case Cursor.FIELD_TYPE_BLOB   : row.put(colName, crs.getBlob(i).toString()); break;
                            case Cursor.FIELD_TYPE_FLOAT  : row.put(colName, crs.getDouble(i))         ; break;
                            case Cursor.FIELD_TYPE_INTEGER: row.put(colName, crs.getLong(i))           ; break;
                            case Cursor.FIELD_TYPE_NULL   : row.put(colName, null)               ; break;
                            case Cursor.FIELD_TYPE_STRING : row.put(colName, crs.getString(i))         ; break;
                        }
                    } catch (JSONException e) {
                    }
                }
            }
            Log.i("lowerSC", row.toString());
            arr.put(row);
            if (!crs.moveToNext())
                break;
        }
        crs.close(); // close the cursor
        return arr.toString();
    }

}
