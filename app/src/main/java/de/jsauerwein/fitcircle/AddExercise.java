package de.jsauerwein.fitcircle;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ImageView;
import android.widget.TextView;

public class AddExercise extends Fragment {

    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_trainingschedule_add_exercise, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new AddExerciseListener());
        return view;
    }

    private void navigateBack() {
        Intent overview = new Intent(AppContract.BROADCAST_ACTION_WORKOUT);
        overview.putExtra(InteractionModel.TAG_CURRENT_FRAGMENT, InteractionModel.WORKOUT_OVERVIEW);
        LocalBroadcastManager.getInstance(this.getActivity()).sendBroadcast(overview);
    }

    private class AddExerciseListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            AddViewHolder addviewholder = new AddViewHolder();

            addviewholder.editName = (EditText) view.findViewById(R.id.edit_name);
            addviewholder.editPose = (EditText) view.findViewById(R.id.edit_pose);
            addviewholder.editDifficulty = (EditText) view.findViewById(R.id.edit_difficulty);
            addviewholder.ball = (CheckBox) view.findViewById(R.id.checkBoxBall);
            addviewholder.chair = (CheckBox) view.findViewById(R.id.checkBoxChair);
            addviewholder.expander = (CheckBox) view.findViewById(R.id.checkBoxExpander);
            addviewholder.mat = (CheckBox) view.findViewById(R.id.checkBoxMat);

            String name = addviewholder.editName.getText().toString();
            String pose = addviewholder.editPose.getText().toString();
            String difficulty = addviewholder.editDifficulty.getText().toString();
            boolean checkBall = addviewholder.ball.isChecked();
            boolean checkChair = addviewholder.chair.isChecked();
            boolean checkExpander = addviewholder.expander.isChecked();
            boolean checkMat = addviewholder.mat.isChecked();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("type", pose);
            contentValues.put("difficulty", difficulty);

            Uri baseUri =  TrainingScheduleContract.Exercises.CONTENT_URI;
            Uri target = getActivity().getContentResolver().insert(baseUri, contentValues);

            String id = target.getLastPathSegment();

            ContentValues contentTools;
            if (checkBall == true){
                contentTools = new ContentValues();
                contentTools.put("tool", "3");
                contentTools.put("exercise_id", id);
                Uri toolsBaseUri = Uri.parse("content://de.jsauerwein.fitcircle.schedule/exercises/" + id + "/tools");
                Uri uri = getActivity().getContentResolver().insert(toolsBaseUri, contentTools);
            }
            if (checkChair == true) {
                contentTools = new ContentValues();
                contentTools.put("tool", "4");
                contentTools.put("exercise_id", id);
                Uri toolsBaseUri = Uri.parse("content://de.jsauerwein.fitcircle.schedule/exercises/" + id + "/tools");
                Uri uri = getActivity().getContentResolver().insert(toolsBaseUri, contentTools);
            }
            if (checkExpander == true) {
                contentTools = new ContentValues();
                contentTools.put("tool", "2");
                contentTools.put("exercise_id", id);
                Uri toolsBaseUri = Uri.parse("content://de.jsauerwein.fitcircle.schedule/exercises/" + id + "/tools");
                Uri uri = getActivity().getContentResolver().insert(toolsBaseUri, contentTools);
            }
            if (checkMat == true) {
                contentTools = new ContentValues();
                contentTools.put("tool", "1");
                contentTools.put("exercise_id", id);
                Uri toolsBaseUri = Uri.parse("content://de.jsauerwein.fitcircle.schedule/exercises/" + id + "/tools");
                Uri uri = getActivity().getContentResolver().insert(toolsBaseUri, contentTools);
            }

            navigateBack();
        }
    }

    private static class AddViewHolder {
        public EditText editName;
        public EditText editPose;
        public EditText editDifficulty;
        public CheckBox ball;
        public CheckBox chair;
        public CheckBox expander;
        public CheckBox mat;
    }
}
