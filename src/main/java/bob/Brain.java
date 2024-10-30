package bob;
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

import org.jetbrains.annotations.NotNull;

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
        Spam.fName = fname;
        Spam.tName = tname;

        Spam sp = new Spam(); //Create the structure

        dat = sp.loadStructure(this.fname); //Load keywords

        //sp = new bob.Spam();
        // bob.Spam.isApplet = true;
        tpl = sp.loadStructure(this.tname);//Load templates

        collection = new Keyword[dat.size()]; //Array of keywords/templates
        tcol = new Keyword[tpl.size()];         //should be made as a thread?

        for (int i = 0; i < dat.size(); i++)
            collection[i] = (Keyword) dat.elementAt(i);
        for (int i = 0; i < tpl.size(); i++)
            tcol[i] = (Keyword) tpl.elementAt(i);
    }

    /**
     * ---------------------------------------------------
     * METHOD: main
     * PURPOSE: make so that BRAIN can run stand-alone
     * ---------------------------------------------------
     */
    public static void main(@NotNull String @NotNull [] args) {
        Brain me = new Brain();
        if (args.length != 0) System.out.println(me.basicAnswer(args[0]));

    }

    /**
     * ---------------------------------------------------
     * METHOD: basicAnswer
     * PURPOSE: Get an answer from the hardwired database
     * based on keywords.
     * ---------------------------------------------------
     */
    public String basicAnswer(@NotNull String in) {


        String[] seg = Lap.segment(in.toLowerCase());
        Lap.getWords(seg);
        StringBuilder all = new StringBuilder();
        for (String s : seg) all.append(s).append(" ");
        returnText = "";

        for (int i = 0; i < dat.size(); i++) {
            returnText = collection[i].getText(all.toString());
            if (!returnText.equals("-")) return returnText;
        }
        return returnText;


    }

    /**
     * ---------------------------------------------------
     * METHOD: avoidAnswer
     * PURPOSE: Avoid answering intelligent by reversing
     * the user input and placing it inside a question.
     * ---------------------------------------------------
     */
    public String avoidAnswer(@NotNull String in) {
        Lap.init();
        String[] seg = Lap.segment(in.toLowerCase());
        Lap.getWords(seg);

        Lap.template = chooseAnswerType(); //the general approach. get the template based on the question
        in = Lap.formulateReply();
        in = Lap.polish(in);

        return in;
    }

    /**
     * ---------------------------------------------------
     * METHOD:mathAnswer
     * PURPOSE: Refuse to do calculations
     * ---------------------------------------------------
     */
    void mathAnswer() {
        // Refuse to do calculations
    }

    /**
     * ---------------------------------------------------
     * METHOD: gramAnswer
     * PURPOSE: Check the grammar of the user input
     * ---------------------------------------------------
     */
    String gramAnswer(String in) {
        if (Lap.checkGrammar()) return "-";
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
            String fReturnText = tcol[i].getText("change_subject");
            if (!fReturnText.equals("-")) return fReturnText;
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
            String fReturnText = tcol[i].getText("full");
            if (!fReturnText.equals("-")) return (fReturnText + "\n" + this.getNewQuestion());
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
            String fReturnText = tcol[i].getText(Lap.sent[0].toLowerCase());

            if (!fReturnText.equals("-")) return (fReturnText);
        }

        return "";
    }
}

