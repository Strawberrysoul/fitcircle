package de.jsauerwein.fitcircle;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Janina on 29.06.2015.
 */
public class ToolTable {
    public static String create_table_sql =
            "CREATE TABLE [tools] ([_id]INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, [exercise_id] INTEGER NOT NULL ,[tool] INTEGER NOT NULL)";
    public static String DatabaseName = "tools.db";
    public static int DatabaseVersion = 1;

  
    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_sql);
        // seed with data
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('1', '1')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('2', '1')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('3', '1')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('4', '1')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('5', '4')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('6', '3')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('7', '2')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('7', '4')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('8', '2')");
        db.execSQL("INSERT INTO tools (exercise_id, tool) VALUES ('8', '3')");

    }


    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tools");
        onCreate(db);
    }
}
