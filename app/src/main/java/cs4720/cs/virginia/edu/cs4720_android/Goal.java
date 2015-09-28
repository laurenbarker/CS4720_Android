package cs4720.cs.virginia.edu.cs4720_android;

/**
 * Created by reinaH on 9/28/15.
 */



public class Goal {
    public String EXTRA_TITLE = "";
    public Double EXTRA_GOAL = 0.0;
    public String EXTRA_UNIT = "";
    public Double EXTRA_INCREMENT = 0.0;
    public String EXTRA_INTERVAL = "";
    public String GOAL_TEXT = "";
    public String DESCRIPTION = "";
    public Integer _id = null;

    //CONSTRUCTORS
    public Goal (){
        this._id = null;
        this.EXTRA_TITLE= "";
        this.EXTRA_GOAL = 0.0;
        this.EXTRA_UNIT = "";
        this.EXTRA_INCREMENT = 0.0;
        this.EXTRA_INTERVAL = "";
        this.DESCRIPTION = "";
        this.GOAL_TEXT = "";

    }

    public Goal (String EXTRA_TITLE, Double EXTRA_GOAL, String EXTRA_UNIT, Double EXTRA_INCREMENT, String EXTRA_INTERVAL){
        this._id = null;
        this.EXTRA_TITLE = EXTRA_TITLE;
        this.EXTRA_GOAL = EXTRA_GOAL;
        this.EXTRA_UNIT = EXTRA_UNIT;
        this.EXTRA_INCREMENT = EXTRA_INCREMENT;
        this.EXTRA_INTERVAL = EXTRA_INTERVAL;
        this.DESCRIPTION = null;
        this.GOAL_TEXT = null;
    }

    public Goal (Integer _id, String EXTRA_TITLE, Double EXTRA_GOAL, String EXTRA_UNIT, Double EXTRA_INCREMENT, String EXTRA_INTERVAL){
        this._id = _id;
        this.EXTRA_TITLE = EXTRA_TITLE;
        this.EXTRA_GOAL = EXTRA_GOAL;
        this.EXTRA_UNIT = EXTRA_UNIT;
        this.EXTRA_INCREMENT = EXTRA_INCREMENT;
        this.EXTRA_INTERVAL = EXTRA_INTERVAL;
        this.DESCRIPTION = null;
        this.GOAL_TEXT = null;
    }

    //SETTERS
    public void setTitle(String title ){
        this.EXTRA_TITLE = title;
    }

    public void setGoal(Double goal ){
        this.EXTRA_GOAL = goal;
    }

    public void setUnit(String unit ){
        this.EXTRA_UNIT = unit;
    }

    public void setIncrement(Double increment ){
        this.EXTRA_INCREMENT = increment;
    }

    public void setInterval(String interval ){
        this.EXTRA_INTERVAL = interval;
    }

    public void setID(Integer _id ){
        this._id = _id;
    }

    public void setDESCRIPTION(String DESCRIPTION ){
        this.DESCRIPTION = DESCRIPTION;
    }

    public void setGoalText(String GOAL_TEXT ){
        this.GOAL_TEXT = GOAL_TEXT;
    }




    //GETTERS
    public String getDESCRIPTION(){
        return DESCRIPTION;
    }

    public String getEXTRA_TITLE(){
        return EXTRA_TITLE;
    }

    public Double getEXTRA_GOAL(){
        return EXTRA_GOAL;
    }

    public String getEXTRA_UNIT(){
        return EXTRA_UNIT;
    }

    public Double getEXTRA_INCREMENT(){
        return EXTRA_INCREMENT;
    }

    public String getEXTRA_INTERVAL(){
        return EXTRA_INTERVAL;
    }

    public String getGOAL_TEXT(){
        return GOAL_TEXT;
    }

    public Integer getID(){
        return _id;
    }


}
