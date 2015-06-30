package de.jsauerwein.fitcircle;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.List;

public class TrainingScheduleProvider extends ContentProvider {

    TrainingScheduleDataBase db;
    Cursor cursor;

    public TrainingScheduleProvider() {
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = 0;
        switch(TrainingScheduleContract.URI_MATCHER.match(uri)) {
            case TrainingScheduleContract.EXERCISE_LIST:
                id = db.getWritableDatabase().insert("exercises", null, values);
                break;
            case TrainingScheduleContract.EXERCISE_TOOL_LIST:
                id = db.getWritableDatabase().insert("tools", null, values);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse(uri + "/" + id);

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

       switch(TrainingScheduleContract.URI_MATCHER.match(uri)) {
           case TrainingScheduleContract.EXERCISE_LIST:
               cursor = db.getReadableDatabase().rawQuery("SELECT * FROM exercises", null);

                return cursor;

           case TrainingScheduleContract.EXERCISE_TOOL_LIST:

                List<String> pathSegments = uri.getPathSegments();
                int exerciseId = Integer.valueOf(pathSegments.get(pathSegments.size() - 2));
                cursor = db.getReadableDatabase().rawQuery("SELECT tool FROM tools WHERE exercise_id = " + exerciseId, null);
               cursor.setNotificationUri(getContext().getContentResolver(), uri);

                return cursor;
            default:
                throw new UnsupportedOperationException("Resource not support.");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch(TrainingScheduleContract.URI_MATCHER.match(uri)) {
            case TrainingScheduleContract.EXERCISE_LIST:
                return TrainingScheduleContract.Exercises.CONTENT_TYPE;
            case TrainingScheduleContract.EXERCISE_TOOL_LIST:
                return TrainingScheduleContract.Exercises.Tools.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Resource not support.");
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = new TrainingScheduleDataBase((this.getContext()));
        return false;
    }
}
