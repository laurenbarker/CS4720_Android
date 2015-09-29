package cs4720.cs.virginia.edu.cs4720_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cs4720.cs.virginia.edu.cs4720_android.GoalContract.GoalEntry;

/**
 * Created by laurenbarker on 9/26/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private final ArrayList<Goal> goal_list = new ArrayList<Goal>();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "GoalReader.db";


    // Goals table name
    private static final String TABLE_GOALS = GoalEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GoalEntry.TABLE_NAME + " (" +
                    GoalEntry._ID + " INTEGER PRIMARY KEY," +
                    GoalEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    GoalEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    GoalEntry.COLUMN_NAME_GOAL + REAL_TYPE + COMMA_SEP +
                    GoalEntry.COLUMN_NAME_UNIT + TEXT_TYPE + COMMA_SEP +
                    GoalEntry.COLUMN_NAME_INCREMENT + REAL_TYPE + COMMA_SEP +
                    GoalEntry.COLUMN_NAME_INTERVAL + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GoalEntry.TABLE_NAME;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Adding new goal
    public void Add_Goal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalEntry.COLUMN_NAME_ENTRY_ID, goal.getID());
        values.put(GoalEntry.COLUMN_NAME_TITLE, goal.getEXTRA_TITLE());
        values.put(GoalEntry.COLUMN_NAME_GOAL, goal.getEXTRA_GOAL());
        values.put(GoalEntry.COLUMN_NAME_UNIT, goal.getEXTRA_UNIT());
        values.put(GoalEntry.COLUMN_NAME_INCREMENT, goal.getEXTRA_INCREMENT());
        values.put(GoalEntry.COLUMN_NAME_INTERVAL, goal.getEXTRA_INTERVAL());

        // Inserting Row
        db.insert(TABLE_GOALS, null, values);
        db.close(); // Closing database connection

    }


    // Getting single goal
    Goal Get_Goal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GOALS, new String[] { GoalEntry.COLUMN_NAME_ENTRY_ID,
                        GoalEntry.COLUMN_NAME_TITLE, GoalEntry.COLUMN_NAME_GOAL,
                        GoalEntry.COLUMN_NAME_UNIT, GoalEntry.COLUMN_NAME_INCREMENT,
                        GoalEntry.COLUMN_NAME_INTERVAL }, GoalEntry.COLUMN_NAME_ENTRY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Goal contact = new Goal(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Double.parseDouble(cursor.getString(2)), cursor.getString(3),
                Double.parseDouble(cursor.getString(4)), cursor.getString(5));
        // return contact
        cursor.close();
        db.close();

        return contact;
    }

    // Getting All Goals
    public ArrayList<Goal> Get_Goals() {
        try {
            goal_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_GOALS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Goal goal = new Goal();
                    goal.setID(Integer.parseInt(cursor.getString(0)));
                    goal.setTitle(cursor.getString(1));
                    goal.setGoal(Double.parseDouble(cursor.getString(2)));
                    goal.setUnit(cursor.getString(3));
                    goal.setIncrement(Double.parseDouble(cursor.getString(4)));
                    goal.setInterval(cursor.getString(5));
                    // Adding contact to list
                    goal_list.add(goal);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return goal_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_goal", "" + e);
        }

        return goal_list;
    }

    // Updating single goal
    public int Update_Goal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GoalEntry.COLUMN_NAME_ENTRY_ID, goal.getID());
        values.put(GoalEntry.COLUMN_NAME_TITLE, goal.getEXTRA_TITLE());
        values.put(GoalEntry.COLUMN_NAME_GOAL, goal.getEXTRA_GOAL());
        values.put(GoalEntry.COLUMN_NAME_UNIT, goal.getEXTRA_UNIT());
        values.put(GoalEntry.COLUMN_NAME_INCREMENT, goal.getEXTRA_INCREMENT());
        values.put(GoalEntry.COLUMN_NAME_INTERVAL, goal.getEXTRA_INTERVAL());

        // updating row
        return db.update(TABLE_GOALS, values, GoalEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                new String[] { String.valueOf(goal.getID()) });
    }

    // Deleting single goal
    public void Delete_Goal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GOALS, GoalEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Getting goals Count
    public int Get_Total_Goals() {
        String countQuery = "SELECT  * FROM " + TABLE_GOALS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}

