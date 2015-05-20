package helper;

/** 
 * macht das Einlesen von Einfachen Daten einfacher 
 * durch Verdecken des Exception-Handlings
*/


import java.io.*;

public class IO {

	public static BufferedReader input
          = new BufferedReader(new InputStreamReader(System.in));
	public static String eingabe = "";

	// Einlesen eines int
	public static int readInt() {
		try {
			eingabe = input.readLine();
			Integer string_to_int = new Integer(eingabe);
			return string_to_int.intValue();
		}
		catch (Exception e) {
			System.out.println(ConsoleColor.ANSI_RED+"=============================================="+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Falsche Eingabe. Bitte versuchen Sie es erneut"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"=============================================="+ConsoleColor.ANSI_RESET);
		  return readInt();
		}
	}
	
	// Einlesen eines int
	public static int readInt(String errorMessage) {
		try {
			eingabe = input.readLine();
			Integer string_to_int = new Integer(eingabe);
			return string_to_int.intValue();
		}catch (Exception e) {
			System.out.println(ConsoleColor.ANSI_RED+errorMessage+ConsoleColor.ANSI_RESET);
			return readInt(errorMessage);
		}
	}

	// Einlesen eines string
	public static String readString() {
		try {
			return input.readLine();
		}
		catch (Exception e) {
			return "";
		}
	}
	
}


