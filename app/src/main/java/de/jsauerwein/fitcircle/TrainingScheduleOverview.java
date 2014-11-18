package de.jsauerwein.fitcircle;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.*;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class TrainingScheduleOverview extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_trainingschedule_overview_fragment, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        final ListView exerciseList = (ListView) this.getActivity().findViewById(R.id.main_trainingschedule_exercises);

        final List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise.Builder().type(3).name("Exercise 3").addTool(1).build());
        exercises.add(new Exercise.Builder().type(14).name("Exercise 14").addTool(1).build());
        exercises.add(new Exercise.Builder().type(21).name("Exercise 21").addTool(1).build());
        exercises.add(new Exercise.Builder().type(28).name("Exercise 28").addTool(1).build());
        exercises.add(new Exercise.Builder().type(39).name("Exercise 39").addTool(4).build());
        exercises.add(new Exercise.Builder().type(41).name("Exercise 41").addTool(3).build());
        exercises.add(new Exercise.Builder().type(44).name("Exercise 44").addTool(2).addTool(4).build());
        exercises.add(new Exercise.Builder().type(45).name("Exercise 45").addTool(2).addTool(3).build());

        exerciseList.setAdapter(new ExerciseAdapter(this.getActivity(), exercises));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.workout_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent add = new Intent(AppContract.BROADCAST_ACTION_WORKOUT);
                add.putExtra(InteractionModel.TAG_CURRENT_FRAGMENT, InteractionModel.WORKOUT_ADDING);
                LocalBroadcastManager.getInstance(this.getActivity()).sendBroadcast(add);
        }
        return super.onOptionsItemSelected(item);
    }
}
