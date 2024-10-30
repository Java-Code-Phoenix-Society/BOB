package bob;
// $Id: bob.Keyword.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: bob.Keyword.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * bob.Keyword
 *
 */

import java.util.Vector;

/**
 * ---------------------------------------------------
 * CLASS: bob.Keyword
 * SUPERCLASS: none
 * PURPOSE: Provide a structure for the databases.
 * ---------------------------------------------------
 */
public class Keyword {
    int tNr = -1;
    Vector<String> keywords = new Vector<>();
    Vector<String> text = new Vector<>();

    /**
     * ---------------------------------------------------
     * METHOD: getText
     * PURPOSE: extract a database entry according
     * to the keyword entered
     * ---------------------------------------------------
     */
    public String getText(String in) {
        for (int i = 0; i < keywords.size(); i++) {
            if (in.indexOf(keywords.elementAt(i)) != -1 ||
                    in.equals(keywords.elementAt(i))) {
                if (tNr < text.size())
                    tNr++;
                else
                    tNr = text.size();

                try {
                    return text.elementAt(tNr);
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
        if (tNr < text.size())
            tNr++;
        else
            tNr = text.size();

        return text.elementAt(tNr);
    }
}

