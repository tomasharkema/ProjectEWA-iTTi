/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

import java.util.Comparator;

/**
 *
 * @author Repr
 */
public class dateComparetor implements Comparator<TimeLineNode> {

    public dateComparetor() {
    }

    @Override
    public int compare(TimeLineNode n1, TimeLineNode n2) {
       return n1.getDate().before(n2.getDate()) ? 1 : -1;
    }

}
