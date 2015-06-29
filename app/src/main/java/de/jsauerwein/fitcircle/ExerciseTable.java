package de.jsauerwein.fitcircle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Janina on 29.06.2015.
 */
public class ExerciseTable {
    public static String create_table_sql =
            "CREATE TABLE [exercises] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,[type] INTEGER NOT NULL, [name] TEXT NOT NULL UNIQUE, [difficulty] INTEGER NOT NULL)";
    public static String DatabaseName = "exercises.db";
    public static int DatabaseVersion = 1;


    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_sql);
        // seed with data
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('14', 'Exercise 3', '2')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('14', 'Exercise 21', '2')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('21', 'Exercise 22', '5')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('21', 'Exercise 28', '3')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('39', 'Exercise 39', '7')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('41', 'Exercise 41', '7')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('44', 'Exercise 44', '4')");
        db.execSQL("INSERT INTO exercises (type, name, difficulty) VALUES ('45', 'Exercise 45', '8')");
    }


    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS exercises");
        onCreate(db);
    }
}
