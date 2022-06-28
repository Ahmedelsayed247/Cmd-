package assignment;

import java.util.Arrays;

/**
 *
 * @author pvv
 */
public class Parser {

    public static String commandName = "";
    public static String allinput = "";
    public int arrcount = 0;
    public static String[] args = new String[30];
    public static int numoflength;  //for check the length of content

//This method will divide the input into commandName and args
//where "input" is the string command entered by the user 
    public void parse(String input) {
        commandName = "";
        allinput = "";
        arrcount = 0;
        args = new String[10];
        numoflength=0;
        allinput = input;
        String split[] = allinput.split(" ", 0);
        numoflength = split.length;
        commandName = split[0];
        for (int i = 1; i < split.length; i++) {
            args[arrcount] = split[i];
            arrcount += 1;
        }
    }

    public String getCommandName(String command) {
        this.commandName = command;
        return command;
    }

    public String[] getArgs() {

        return this.args;

    }
}
