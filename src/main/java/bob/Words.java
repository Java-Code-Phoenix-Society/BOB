package bob;
// $Id: bob.Words.java,v 1.2 1997/03/31 21:55:13 lcrnkovi Exp lcrnkovi $
// $Log: bob.Words.java,v $
// Revision 1.2  1997/03/31 21:55:13  lcrnkovi
// RCS improvements
//

/**
 * ---------------------------------------------------
 * CLASS: bob.Words
 * SUPERCLASS: none
 * PURPOSE: lingual-database.
 * ---------------------------------------------------
 */
class Words {
    private Words() {
        // empty
    }

    static String[] question = {
            "what",
            "when",
            "where",
            "why",
            "which",
            "who",
            "how"
    };

    static String[] verb = {
            "am", "are", "were",
            "is", "was",
            "will", "would",
            "can", "could",
            "do", "does", "did",
            "have", "has", "had",
            "must", "might",
            "shall", "should", "like"
    };

    static String[] pronoun = {
            "you",
            "i",
            "me",
            "they",
            "he",
            "she",
            "we",
            "it",
            "that",
            "us",
            "the"
    };

    static String[] adjective = {
            "your",
            "my",
            "their",
            "his",
            "her",
            "our",
            "its",
            "thats",
            "big",
            "small",
            "narrow",
            "huge",
            "tiny",
            "nice",
            "broad",
            "ugly",
            "disgusting",
            "defying",
            "stinking",
            "smelling",
            "bad",
            "good"
    };

    static String[] possessive = {
            "yours",
            "mine",
            "yourself",
            "myself",
            "theirs",
            "his",
            "hers",
            "ours",
            "its",
            "thats",
            "the"
    };

    static String[] adverb = {
            "not",
            "be",
            "been",
            "doing"
    };

    static String[] number = {
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "number",
            "zero",
            "nought",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety",
            "hundred",
            "thousand",
            "million",
            "billion",
            "dozen",
            "infinit"
    };

    static String[] operator = {
            "+",
            "-",
            "*",
            "/",
            "add",
            "plus",
            "minus",
            "subtract",
            "take",
            "multiply",
            "times",
            "divide",
            "square",
            "root",
            "after",
            "before"
    };

    static String[] day = {
            "monday",
            "tuesday",
            "wednesday",
            "thursday",
            "friday",
            "saturday",
            "sunday"
    };

    static String[] month = {
            "january",
            "february",
            "march",
            "april",
            "may",
            "june",
            "july",
            "august",
            "september",
            "october",
            "november",
            "december"
    };

    static String[] color = {

            "black", "white", "brown",
            "red", "blue", "green",
            "yellow", "cyan", "magneta",
            "pink", "grey", "gray"
    };
}
