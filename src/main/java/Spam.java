// $Id: Spam.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: Spam.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/*
 *
 * Spam
 *
 *Stream Processing and Analysing Module
 */

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * ---------------------------------------------------
 * CLASS: Spam
 * SUPERCLASS: none
 * PURPOSE: Handles the I/O actually only input.
 * ---------------------------------------------------
 */
public class Spam {
    public static String fname;
    public static String tname;
    public static boolean isApplet = false;
    public static String n_subj = "#";
    public static String n_txt = "-";
    public static String comment = "//";


    /**
     * ---------------------------------------------------
     * CONSTRUCTOR: Spam
     * PURPOSE: transfer the isApplet attribute from Bob.
     * ---------------------------------------------------
     */
    Spam() {
        isApplet = Bob.isApplet; //hmm, there goes the modularity
    }

    /**
     * ---------------------------------------------------
     * METHOD: load_structure
     * PURPOSE: load the database into a vector
     * ---------------------------------------------------
     */
    public static final Vector load_structure(String fn) {

        Vector data = new Vector();
        DataInputStream dis = Spam.set_stream(fn);
        String line;
        String txt = new String();
        String kword = new String();
        int nr = -1;

        boolean isKword = true;

        try {

            while ((line = dis.readLine()) != null) {
                if (line.equals(n_subj))//new keyword
                {
                    line = dis.readLine();
                    isKword = true;
                    data.addElement(new Keyword());

                    if (!txt.equals(""))
                        ((Keyword) data.elementAt(nr)).text.addElement(new String(txt));

                    nr++;
                    txt = "";
                }
                if (line.equals(n_txt)) //new text
                {
                    line = dis.readLine();
                    if (!txt.equals(""))
                        ((Keyword) data.elementAt(nr)).text.addElement(new String(txt));
                    txt = "";
                    isKword = false;

                }
                if (line.indexOf("//") != -1)
                    line = dis.readLine();


                if (isKword) //add new keyword
                    ((Keyword) data.elementAt(nr)).keyword.addElement(new String(line));


                if (!isKword)         //if allright add text
                    txt += line + "\n";


            }


        } catch (IOException e) {
        }

        return data;
    }

    /**
     * ---------------------------------------------------
     * METHOD: set_stream
     * PURPOSE: Set the input-stream depending on if it runs
     * as an applet or program.
     * ---------------------------------------------------
     */
    static DataInputStream set_stream(String fn) {
        DataInputStream dis = null;

        if (!isApplet) {
            try {

                FileInputStream fis = new FileInputStream(fn);
                dis = new DataInputStream(fis);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else if (isApplet) {
            URL theUrl;
            String url = fn;
            try {
                //System.out.print(Bob.url.toString());
                if (Bob.url != null)
                    theUrl = new URL(Bob.url, url);
                else
                    theUrl = new URL(url);
                InputStream conn = theUrl.openStream();
                dis = new DataInputStream(new BufferedInputStream(conn));

            } catch (MalformedURLException e) {
                System.out.println("Error loding URL");
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }

        return dis;
    }

    /**
     * ---------------------------------------------------
     * METHOD: main
     * PURPOSE: stand-alone support for debbuging.
     * ---------------------------------------------------
     */
    public static void main(String args[]) {


        fname = "bob.dat";
        tname = "bob.tpl";
        Vector bob = load_structure(fname);
        //System.out.println(bob.size());


        if (args.length == 0) {


            for (int i = 0; i < bob.size(); i++) {
                Keyword tmp = (Keyword) bob.elementAt(i);


                for (int j = 0; j < tmp.keyword.size(); j++) {
                    System.out.println((String) tmp.keyword.elementAt(j));
                }


            }


        } else {
            if (args[0].equals("1") && args.length == 2) {
                Keyword p = (Keyword) bob.elementAt(Integer.parseInt(args[1]));
                for (int i = 0; i < p.text.size(); i++) {
                    System.out.println("Text " + i + " : " + (String) p.text.elementAt(i));
                }
            }

            String[] seg = Lap.segment(args[0].toLowerCase());
            Lap.get_words(seg);
            String all = "";

            for (int i = 0; i < seg.length; i++)
                all += seg[i] + " \n";
            //System.out.println(all);

            Keyword[] k = new Keyword[bob.size()];
            for (int i = 0; i < bob.size(); i++) {
                k[i] = (Keyword) bob.elementAt(i);

                if (!(k[i].getText(all).equals("-"))) {
                    System.out.println(k[i].getText(all));
                    break;
                }

            }

        }

    }
}

