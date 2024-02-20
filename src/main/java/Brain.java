// $Id: Brain.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: Brain.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * Brain
 * Baisic Recipient Artificial Intelligence Narrator
 */

import java.util.Vector;


/**
 * ---------------------------------------------------
 * Class: Brain
 * Superclass: none
 * PURPOSE: Link all the submodules together. Provide
 * a good interface to other methods.
 * ---------------------------------------------------
 */
public class Brain {
    public static String fname = "bob.dat";
    public static String tname = "bob.tpl";
    public Vector dat; //the database
    public Vector tpl; //the template-database
    public Keyword[] collection; //dbase
    public Keyword[] tcol; //tdbase
    public String returnText;

    /**
     * ---------------------------------------------------
     * CONSTRUCTOR: Brain
     * PURPOSE: Load all temlplates and the database
     * ---------------------------------------------------
     */
    public Brain() {
        Spam.fname = fname;
        Spam.tname = tname;

        Spam sp = new Spam(); //Create the structure

        dat = sp.load_structure(this.fname); //Load keywords

        //sp = new Spam();
        // Spam.isApplet = true;
        tpl = sp.load_structure(this.tname);//Load templates

        collection = new Keyword[dat.size()]; //Array of keywords/templates
        tcol = new Keyword[tpl.size()];         //should be made as a thread?

        for (int i = 0; i < dat.size(); i++)
            collection[i] = (Keyword) dat.elementAt(i);
        for (int i = 0; i < tpl.size(); i++)
            tcol[i] = (Keyword) tpl.elementAt(i);

        //System.out.println(tcol[3].returnText());
    }

    /**
     * ---------------------------------------------------
     * METHOD: main
     * PURPOSE: make so that BRAIN can run stand-alone
     * ---------------------------------------------------
     */
    public static void main(String args[]) {
        Brain me = new Brain();
        if (args.length != 0) System.out.println(me.basic_answer(args[0]));

    }

    /**
     * ---------------------------------------------------
     * METHOD: basic_answer
     * PURPOSE: Get an answer from the hardwired database
     * based on keywords.
     * ---------------------------------------------------
     */
    public String basic_answer(String in) {


        String[] seg = Lap.segment(in.toLowerCase());
        Lap.get_words(seg);
        String all = new String();
        for (int i = 0; i < seg.length; i++)
            all += seg[i] + " ";
        returnText = new String();

        for (int i = 0; i < dat.size(); i++) {
            returnText = collection[i].getText(all);
            if (!returnText.equals("-")) return returnText;
        }
        return returnText;


    }

    /**
     * ---------------------------------------------------
     * METHOD: avoid_answer
     * PURPOSE: Avoid answering intelligent by reversing
     * the user input and placing it inside a question.
     * ---------------------------------------------------
     */
    public String avoid_answer(String in) {
        Lap.init();
        String[] seg = Lap.segment(in.toLowerCase());
        Lap.get_words(seg);

        Lap.template = chooseAnswerType(); //the general approach. get the template based on the question
        in = Lap.formulateReply();
        in = Lap.polish(in);

        return in;
    }

    /**
     * ---------------------------------------------------
     * METHOD:math_answer
     * PURPOSE: Refuse to do calculations
     * ---------------------------------------------------
     */
    void math_answer() {
    }

    /**
     * ---------------------------------------------------
     * METHOD: gram_answer
     * PURPOSE: Check the grammar of the user input
     * ---------------------------------------------------
     */
    String gram_answer(String in) {
        if (Lap.check_grammar()) return "-";
        else return "#";
    }

    /**
     * ---------------------------------------------------
     * METHOD: getNewQuestion
     * PURPOSE: Introduce a new subject
     * ---------------------------------------------------
     */
    public String getNewQuestion() {
        for (int i = 0; i < tpl.size(); i++) {
            String returnText = tcol[i].getText("change_subject");
            if (!returnText.equals("-")) return returnText;
        }

        return "";
    }

    /**
     * ---------------------------------------------------
     * METHOD: fullOfIt
     * PURPOSE: When you run out of answers...
     * ---------------------------------------------------
     */
    public String fullOfIt() {
        for (int i = 0; i < tpl.size(); i++) {
            String returnText = tcol[i].getText("full");
            if (!returnText.equals("-")) return (returnText + "\n" + this.getNewQuestion());
        }

        return "";
    }

    /**
     * ---------------------------------------------------
     * METHOD: chooseAnswerType
     * PURPOSE: find what question is actual, search
     * the template-database and give an answer.
     * ---------------------------------------------------
     */
    public String chooseAnswerType() {
        for (int i = 0; i < tpl.size(); i++) {
            String returnText = tcol[i].getText(Lap.sent[0].toLowerCase());

            if (!returnText.equals("-")) return (returnText);
        }

        return "";
    }
}

