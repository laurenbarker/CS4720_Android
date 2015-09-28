package cs4720.cs.virginia.edu.cs4720_android;

import android.provider.BaseColumns;

/**
 * Created by laurenbarker on 9/26/15.
 */
public final class GoalContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public GoalContract() {}

    /* Inner class that defines the table contents */
    public static abstract class GoalEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

}
