package com.kuaikuaiyu.assistant.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kuaikuaiyu.assistant.R;

import java.util.ArrayList;

/**
 * Created by kky on 15-7-21.
 */
public class CityUtil {

    private static String dbPath = UIUtil.getContext().getFilesDir().getPath() + "cityInfo.db";

    static {
        FileUtil.writeFile(UIUtil.getResources().openRawResource(R.raw.cityinfo), dbPath, false);
    }

    private static ArrayList<String> queryFindAll(String table, String key, String value, int index) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from " + table + " where " + key + " = ?;", new String[]{value});
        while (cursor.moveToNext()) {
            list.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return list;
    }

    private static ArrayList<String> queryAll(String table, int index) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from " + table + ";", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return list;
    }

    private static String queryFindOne(String table, String key, String value, int index) {
        String result = null;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from " + table + " where " + key + " = ?;", new String[]{value});
        if (cursor.moveToNext()) {
            result = cursor.getString(index);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * get all bank name
     *
     * @return
     */
    public static ArrayList<String> getBankNameList() {
        return queryAll("bankinfo", 2);
    }

    /**
     * get bank id by bank name
     *
     * @param bankName
     * @return
     */
    public static String getBankId(String bankName) {
        return queryFindOne("bankinfo", "name", bankName, 1);
    }

    /**
     * get all provinceName
     *
     * @return
     */
    public static ArrayList<String> getProvinceNameList() {
        return queryAll("bankprovinceinfo", 2);
    }

    /**
     * get provinceId by provinceName
     *
     * @param provinceName
     * @return
     */
    public static String getProvinceID(String provinceName) {
        return queryFindOne("bankprovinceinfo", "name", provinceName, 1);
    }

    /**
     * get all city name by provinceName
     *
     * @param provinceName
     * @return
     */
    public static ArrayList<String> getCityNameList(String provinceName) {
        return queryFindAll("bankcityinfo", "province_name", provinceName, 4);
    }

    /**
     * get city Id by city name
     *
     * @param cityName
     * @return
     */
    public static String getCityID(String cityName) {
        return queryFindOne("bankcityinfo", "city_name", cityName, 1);
    }
}
