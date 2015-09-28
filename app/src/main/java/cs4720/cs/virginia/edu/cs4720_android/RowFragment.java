package cs4720.cs.virginia.edu.cs4720_android;

/**
 * Created by reinaH on 9/28/15.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class RowFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.row,
                container, false);
        // Return view
        return view;
    }
}