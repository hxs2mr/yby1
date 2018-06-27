package com.itislevel.lyl.mvp.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public class DBHorstoryUtil {
    /**
     * 建表语句
     */
    private static final String CREATE_HISTORY_SEARCH = "create table searchHistory (" +
            "id integer primary key autoincrement, " +
            "name text)";

    private final String TAG = "HistorySearchUtil";

    private final String TABLE_NAME = "searchHistory";

    public Context mContext;

    private static DBHorstoryUtil mHistorySearchUtil;

    private MyDatabaseHelper mMyDatabaseHelper;

    private DBHorstoryUtil(Context context) {
        mMyDatabaseHelper = new MyDatabaseHelper(context, "complint.db", null, 1);
        this.mContext = context;
    }

    public static DBHorstoryUtil getInstance(Context context) {//得到一个实例
        if (mHistorySearchUtil == null) {
            mHistorySearchUtil = new DBHorstoryUtil(context);
        } else if ((!mHistorySearchUtil.mContext.getClass()
                .equals(context.getClass()))) {////判断两个context是否相同
            mHistorySearchUtil = new DBHorstoryUtil(context);
        }
        return mHistorySearchUtil;
    }

    /**
     * 添加一条新纪录
     * @param name
     */
    public void putNewSearch(String name) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (!isExist(name)) {//判断新纪录是否存在，不存在则添加
            ContentValues values = new ContentValues();
            values.put("name", name);
            db.insert(TABLE_NAME, null, values);
        }
    }

    /**
     * 判断记录是否存在
     * @param name
     * @return
     */
    public boolean isExist(String name) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where name = '?'", new String[]{name});
        if (cursor.moveToFirst()) {//如果存在
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询所有历史纪录
     * @return
     */
    public List<String> queryHistorySearchList() {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                list.add(name);
            } while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * 删除单条记录
     * @param name
     */
    public void deleteHistorySearch(String name) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (isExist(name)) {
            db.delete(TABLE_NAME, "name = " + "'" + name + "'", null);
        }
    }

    /**
     * 删除所有记录
     */
    public void deleteAllHistorySearch() {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public class MyDatabaseHelper extends SQLiteOpenHelper {

        public MyDatabaseHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factoty, int version) {
            super(context, name, factoty, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_HISTORY_SEARCH);//建表
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
