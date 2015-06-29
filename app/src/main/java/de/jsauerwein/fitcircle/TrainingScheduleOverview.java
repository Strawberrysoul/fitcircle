package de.jsauerwein.fitcircle;


import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class TrainingScheduleOverview extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ExerciseCursorAdapter cAdapter;
    LoaderManager loaderManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_trainingschedule_overview_fragment, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        loaderManager = getLoaderManager();
        cAdapter = new ExerciseCursorAdapter(this.getActivity(), null);

        final ListView exerciseList = (ListView) this.getActivity().findViewById(R.id.main_trainingschedule_exercises);

//        final List<Exercise> exercises = new ArrayList<Exercise>();
//        exercises.add(new Exercise.Builder().type(3).name("Exercise 3").addTool(1).build());
//        exercises.add(new Exercise.Builder().type(14).name("Exercise 14").addTool(1).build());
//        exercises.add(new Exercise.Builder().type(21).name("Exercise 21").addTool(1).build());
//        exercises.add(new Exercise.Builder().type(28).name("Exercise 28").addTool(1).build());
//        exercises.add(new Exercise.Builder().type(39).name("Exercise 39").addTool(4).build());
//        exercises.add(new Exercise.Builder().type(41).name("Exercise 41").addTool(3).build());
 //      exercises.add(new Exercise.Builder().type(44).name("Exercise 44").addTool(2).addTool(4).build());
//        exercises.add(new Exercise.Builder().type(45).name("Exercise 45").addTool(2).addTool(3).build());

       // exerciseList.setAdapter(new ExerciseAdapter(this.getActivity(), exercises));
        exerciseList.setAdapter(cAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this.getActivity(), TrainingScheduleContract.Exercises.CONTENT_URI,
        null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cAdapter.swapCursor(null);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // open the Exercise Overview
    }

}
