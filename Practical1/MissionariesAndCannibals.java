import java.util.ArrayList;

import static java.lang.System.exit;

class state{
//    int n = 3;
    int missionariesLeft;
    int cannibalsLeft;
    int missionariesRight;
    int cannibalsRight;
    String transition;

    public state(int ml, int cl, int mr, int cr,String transition)
    {
        missionariesLeft = ml;
        cannibalsLeft = cl;
        missionariesRight = mr;
        cannibalsRight = cr;
        this.transition = transition;
    }
    public state(int ml, int cl,String transition)
    {
        missionariesLeft = ml;
        cannibalsLeft = cl;
        missionariesRight = 0;
        cannibalsRight = 0;
        this.transition = transition;
    }
    public boolean isValid(int ml, int cl, int mr, int cr)
    {
        if(ml >= 0 && cl >= 0 && mr >= 0 && cr >= 0 && (ml == 0 || ml >= cl) && (mr == 0 || mr >= cr))
        {
            return true;
        }else {
            return false;
        }
    }

    public void printState(state s)
    {
        System.out.println("transition: "+s.transition+"< missionaries: "+s.missionariesLeft+",cannibals: "+s.cannibalsLeft+",Left >"+" < missionaries: "+s.missionariesRight+",cannibals: "+s.cannibalsRight+", Right >");
    }

    public boolean isGoalState(state s)
    {
        if(s.missionariesRight == s.cannibalsRight && s.missionariesLeft == 0 && s.cannibalsLeft == 0) return true;
        else return false;
    }

    public void solve(int side, ArrayList<state> ds, ArrayList<ArrayList<state>> ans, state s)
    {
        if(isGoalState(s)){
            for(state ele: ds)
            {
                printState(ele);
            }
            System.out.println("Goal Reached !!!!!");
            exit(0);
        }
        // if side == 0---> left side of river
        // if side == 1--> right side of river
        if(side == 0)
        {
            // can transfer 2 individuals from left side to right side.
            // there can be 3 different combinations:
            // mm, mc, cc.
            if(isValid(s.missionariesLeft-2,s.cannibalsLeft,s.missionariesRight+2,s.cannibalsRight)){
                state temp = new state(s.missionariesLeft-2,s.cannibalsLeft,s.missionariesRight+2,s.cannibalsRight,"After left to right");
                    ds.add(temp);
                    solve(1,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }

            if(isValid(s.missionariesLeft-1,s.cannibalsLeft-1,s.missionariesRight+1,s.cannibalsRight+1)){
                state temp = new state(s.missionariesLeft-1,s.cannibalsLeft-1,s.missionariesRight+1,s.cannibalsRight+1,"After left to right");
                    ds.add(temp);
                    solve(1,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }

            if(isValid(s.missionariesLeft,s.cannibalsLeft-2,s.missionariesRight,s.cannibalsRight+2)){
                state temp = new state(s.missionariesLeft,s.cannibalsLeft-2,s.missionariesRight,s.cannibalsRight+2,"After left to right");
                    ds.add(temp);
                    solve(1,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }
        }
        else if( side == 1){
            // side == 1 it shows that we are on a return trip:
            // now for return trip there has to be at least one individual in boat.
            // combinations can be 1m, 1c, 1m1c, 2m, 2c.
            if(isValid(s.missionariesLeft+1,s.cannibalsLeft,s.missionariesRight-1,s.cannibalsRight)){
                state temp = new state(s.missionariesLeft+1,s.cannibalsLeft,s.missionariesRight-1,s.cannibalsRight,"After right to left");
                    ds.add(temp);
                    solve(0,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }
            if(isValid(s.missionariesLeft,s.cannibalsLeft+1,s.missionariesRight,s.cannibalsRight-1)){
                state temp = new state(s.missionariesLeft,s.cannibalsLeft+1,s.missionariesRight,s.cannibalsRight-1,"After right to left");
                    ds.add(temp);
                    solve(0,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }
            if(isValid(s.missionariesLeft+1,s.cannibalsLeft+1,s.missionariesRight-1,s.cannibalsRight-1)){
                state temp = new state(s.missionariesLeft+1,s.cannibalsLeft+1,s.missionariesRight-1,s.cannibalsRight-1,"After right to left");
                    ds.add(temp);
                    solve(0,ds,ans,temp);
                    ds.remove(ds.size()-1);
            }

            if(isValid(s.missionariesLeft+2,s.cannibalsLeft,s.missionariesRight-2,s.cannibalsRight)){
                state temp = new state(s.missionariesLeft+2,s.cannibalsLeft,s.missionariesRight-2,s.cannibalsRight,"After right to left");
                ds.add(temp);
                solve(0,ds,ans,temp);
                ds.remove(ds.size()-1);
            }

            if(isValid(s.missionariesLeft,s.cannibalsLeft-2,s.missionariesRight,s.cannibalsRight-2)){
                state temp = new state(s.missionariesLeft+2,s.cannibalsLeft,s.missionariesRight-2,s.cannibalsRight,"After right to left");
                ds.add(temp);
                solve(0,ds,ans,temp);
                ds.remove(ds.size()-1);
            }
        }
    }
}
public class MissionariesAndCannibals {
    public static void main(String[] args) {
        int n = 3;
        state s = new state(n,n,"Start: ");
        ArrayList<ArrayList<state>> ans = new ArrayList<>();
        ArrayList<state> ds = new ArrayList<>();
        ds.add(s);
        s.solve(0,ds,ans,s);
    }
}