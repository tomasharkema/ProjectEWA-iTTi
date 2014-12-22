/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

import entity.User;
import java.util.Date;

/**
 * Class that contains 2 TimeLine objects. The first will always be a user. The
 * second can either be an event or a user.
 *
 * @author Repr
 */
public class TimeLineNode {

    private TimeLine one;
    private TimeLine two;
    private Date date;
    private String mergeLine;

    public TimeLine getOne() {
        return one;
    }

    public void setOne(TimeLine one) {
        this.one = one;
    }

    public TimeLine getTwo() {
        return two;
    }

    public void setTwo(TimeLine two) {
        this.two = two;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return one + this.getMergeLine() + two + " on " + date;
    }

    public String getMergeLine() {
        return mergeLine;
    }

    public void setMergeLine(String mergeLine) {
        this.mergeLine = mergeLine;
    }

    /*
     Method used for setting display puposes. it checks the instance of the 2nd object in the node to determine the appropriate sentence.
     */
    public void findMergeLine() {
        if (two instanceof User) {
            this.setMergeLine(" became friends with ");
        } else {
            this.setMergeLine(" attends ");
        }
    }

}
