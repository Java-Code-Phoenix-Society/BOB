// $Id: Keyword.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: Keyword.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * Keyword
 *
 */

import java.util.Vector;

/**
 * ---------------------------------------------------
 * CLASS: Keyword
 * SUPERCLASS: none
 * PURPOSE: Provide a structure for the databases.
 * ---------------------------------------------------
 */
public class Keyword {
    int t_nr = -1;
    Vector keyword = new Vector();
    Vector text = new Vector();

    /**
     * ---------------------------------------------------
     * METHOD: getText
     * PURPOSE: extract a database entry according
     * to the keyword entered
     * ---------------------------------------------------
     */
    public String getText(String in) {
        for (int i = 0; i < keyword.size(); i++) {
            if (in.indexOf((String) keyword.elementAt(i)) != -1 ||
                    in.equals((String) keyword.elementAt(i))) {
                if (t_nr < text.size())
                    t_nr++;
                else
                    t_nr = text.size();


                //System.out.println((String)text.elementAt(t_nr));

                try {
                    return (String) text.elementAt(t_nr);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return Bob.b.fullOfIt();
                }

            }
        }
        return "-";

    }

    /**
     * ---------------------------------------------------
     * METHOD: returnText
     * PURPOSE: Return the next template in the dbase
     * ---------------------------------------------------
     */
    public String returnText() {
        if (t_nr < text.size())
            t_nr++;
        else
            t_nr = text.size();

        return (String) text.elementAt(t_nr);
    }
}

