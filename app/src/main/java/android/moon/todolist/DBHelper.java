package android.moon.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists todotable ("
                + "_id integer primary key autoincrement,"
                + "work text);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists todotable";

        db.execSQL(sql);
        onCreate(db);
    }
    public void insert(String work) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO todotable VALUES(null, '" + work + "');");
        db.close();
    }
    public String[] getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String[] str = null;
        int count = 0;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM todotable", null);
            str = new String[cursor.getCount()];
            while (cursor.moveToNext()) {
                str[count++] = cursor.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return str;
    }
    public void delete(String work) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM todotable WHERE work = '" + work + "';");
        db.close();
    }
}

