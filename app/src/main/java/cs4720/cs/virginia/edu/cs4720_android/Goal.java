package cs4720.cs.virginia.edu.cs4720_android;

/**
 * Created by reinaH on 9/28/15.
 */



public class Goal {
    public String EXTRA_TITLE = "";
    public String EXTRA_GOAL = "";
    public String EXTRA_UNIT = "";
    public String EXTRA_INCREMENT = "";
    public String EXTRA_INTERVAL = "";
    public String GOAL_TEXT = "";
    public String DESCRIPTION = "";

    //CONSTRUCTORS
    public Goal (){
        this.EXTRA_TITLE= "";
        this.EXTRA_GOAL = "";
        this.EXTRA_UNIT = "";
        this.EXTRA_INCREMENT = "";
        this.EXTRA_INTERVAL = "";
        this.DESCRIPTION = "";
        this.GOAL_TEXT = "";

    }

    public Goal (String EXTRA_TITLE, String EXTRA_GOAL, String EXTRA_UNIT, String EXTRA_INCREMENT, String EXTRA_INTERVAL){
        this.EXTRA_TITLE = EXTRA_TITLE;
        this.EXTRA_GOAL = EXTRA_GOAL;
        this.EXTRA_UNIT = EXTRA_UNIT;
        this.EXTRA_INCREMENT = EXTRA_INCREMENT;
        this.EXTRA_INTERVAL = EXTRA_INTERVAL;
        this.DESCRIPTION = null;
        this.GOAL_TEXT = null;
    }

    public void setGoalText(String GOAL_TEXT ){
        this.GOAL_TEXT = GOAL_TEXT;

    }

    public void setDescription(String DESCRIPTION ){
        this.DESCRIPTION = DESCRIPTION;

    }
}
