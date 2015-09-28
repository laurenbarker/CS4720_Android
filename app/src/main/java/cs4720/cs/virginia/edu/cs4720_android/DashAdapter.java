package cs4720.cs.virginia.edu.cs4720_android;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by reinaH on 9/28/15.
 */
public class DashAdapter extends ArrayAdapter<Goal>{

    public DashAdapter(Context context, ArrayList<Goal> goals){
        super (context, 0, goals);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Goal goal = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        // Lookup view for data population
        TextView goal_text = (TextView) convertView.findViewById(R.id.text_goal);
        TextView description = (TextView) convertView.findViewById(R.id.view_description);


        // Populate the data into the template view using the data object
        goal_text.setText(goal.GOAL_TEXT);
        description.setText(goal.DESCRIPTION);


        // Return the completed view to render on screen
        return convertView;

    }

}
