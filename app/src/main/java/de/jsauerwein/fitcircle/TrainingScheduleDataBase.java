package de.jsauerwein.fitcircle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Janina on 25.06.2015.
 */
class TrainingScheduleDataBase extends SQLiteOpenHelper {
    public static String DatabaseName = "exercises.db";
    public static int DatabaseVersion = 1;

    public TrainingScheduleDataBase(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ExerciseTable.onCreate(db);
        ToolTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ExerciseTable.onUpgrade(db, oldVersion, newVersion);
        ToolTable.onUpgrade(db, oldVersion, newVersion);
    }
}
