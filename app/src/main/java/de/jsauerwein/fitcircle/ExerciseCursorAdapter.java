package de.jsauerwein.fitcircle;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExerciseCursorAdapter extends CursorAdapter {

    private final int[] exerciseIcons= new int[] {
            R.drawable.pose1,
            R.drawable.pose2,
            R.drawable.pose3,
            R.drawable.pose4,
            R.drawable.pose5,
            R.drawable.pose6,
            R.drawable.pose7,
            R.drawable.pose8,
            R.drawable.pose9,
            R.drawable.pose10,
            R.drawable.pose11,
            R.drawable.pose12,
            R.drawable.pose13,
            R.drawable.pose14,
            R.drawable.pose15,
            R.drawable.pose16,
            R.drawable.pose17,
            R.drawable.pose18,
            R.drawable.pose19,
            R.drawable.pose20,
            R.drawable.pose21,
            R.drawable.pose22,
            R.drawable.pose23,
            R.drawable.pose24,
            R.drawable.pose25,
            R.drawable.pose26,
            R.drawable.pose27,
            R.drawable.pose28,
            R.drawable.pose29,
            R.drawable.pose30,
            R.drawable.pose31,
            R.drawable.pose32,
            R.drawable.pose33,
            R.drawable.pose34,
            R.drawable.pose35,
            R.drawable.pose36,
            R.drawable.pose37,
            R.drawable.pose38,
            R.drawable.pose39,
            R.drawable.pose40,
            R.drawable.pose41,
            R.drawable.pose42,
            R.drawable.pose43,
            R.drawable.pose44,
            R.drawable.pose45
    };

    ViewHolder viewHolder;

    public ExerciseCursorAdapter(Context context, Cursor c) {
        super(context,c,0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = View.inflate(context, R.layout.main_trainingschedule_overview_exercise, null);

            viewHolder = new ViewHolder();
            viewHolder.poseView = (ImageView) convertView.findViewById(R.id.main_trainingschedule_workout_type);
            viewHolder.nameView = (TextView) convertView.findViewById(R.id.main_trainingschedule_workout_name);
            viewHolder.difficultyView = (TextView) convertView.findViewById(R.id.main_trainingschedule_difficulty);
            viewHolder.toolViewBall = (ImageView) convertView.findViewById(R.id.main_trainingschedule_tool_ball);
            viewHolder.toolViewChair = (ImageView) convertView.findViewById(R.id.main_trainingschedule_tool_chair);
            viewHolder.toolViewExpander = (ImageView) convertView.findViewById(R.id.main_trainingschedule_tool_expander);
            viewHolder.toolViewMat = (ImageView) convertView.findViewById(R.id.main_trainingschedule_tool_mat);

            convertView.setTag(viewHolder);


        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
    viewHolder = (ViewHolder) view.getTag();
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        int pose = cursor.getInt(cursor.getColumnIndex("type"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int difficulty = cursor.getInt(cursor.getColumnIndex("difficulty"));

        //first, no tools should be visible
        viewHolder.toolViewBall.setVisibility(View.GONE);
        viewHolder.toolViewMat.setVisibility(View.GONE);//
        viewHolder.toolViewChair.setVisibility(View.GONE);
        viewHolder.toolViewExpander.setVisibility(View.GONE);


        // set all Views:
        viewHolder.poseView.setImageResource(this.exerciseIcons[pose- 1]);
        viewHolder.nameView.setText(name);
        viewHolder.difficultyView.setText(""+difficulty);
        Log.i(null,"hallo");
        ContentResolver cr = context.getContentResolver();
        Cursor toolCursor = cr.query(Uri.parse("content://de.jsauerwein.fitcircle.schedule/exercises/" + cursor.getString(cursor.getColumnIndex("_id")) + "/tool"), null, null, null, null);

        for (int i = 0; i < toolCursor.getCount(); i++) {

            toolCursor.moveToNext();
            switch(toolCursor.getInt(toolCursor.getColumnIndex("tool")))
            {
                case 1:
                    viewHolder.toolViewMat.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    viewHolder.toolViewExpander.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    viewHolder.toolViewBall.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    viewHolder.toolViewChair.setVisibility(View.VISIBLE);
                    break;
                default: break;
            }
        }

    }

    private static class ViewHolder {
        private ImageView poseView;
        public TextView nameView;
        public TextView difficultyView;
        public ImageView toolViewBall;
        public ImageView toolViewChair;
        public ImageView toolViewExpander;
        public ImageView toolViewMat;

    }
}
