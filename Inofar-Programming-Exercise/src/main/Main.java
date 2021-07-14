package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import core.CaseInsensitiveFindAndReplace;
import core.CaseSensitiveFindAndReplace;
import core.IFindAndReplace;
import core.exceptions.InvalidArgumentException;

public class Main {

	/**
	 * Single Responsibility paradigm : main is the orchester , divides into helper functions, no need to create separate classes
	 * Converions used (or anything you know from Code Complete Book) :
	 * 	---- Variable naming :
	 * 			scopeNameType 
	 * 
	 * @param args
	 * @throws Exception
	 * @author ZIED NASRI
	 */
	public static void main(String...args) throws Exception{
		
		validateArguments(args);
		
		// From here on , we treat xml and txt inputs indifferently since we prevented using quotes with xml type, if the input is well formed, the output will be too
		
		String lTargetString = args[1];
		String lReplacementString = args[2];
		
		// Some polymorphism for extensibility
		IFindAndReplace lIFindAndReplace = null;
		//cCaseIFindAndReplace = new CaseSensitiveFindAndReplace();
		lIFindAndReplace = new CaseInsensitiveFindAndReplace();
		
		// Buffered Reader is used for efficiency and overriding its buffer size for the same reason
		BufferedReader lBufferedReader = new BufferedReader(new InputStreamReader(System.in), 8 * 1024);
		
		String lOutputString;
	    while ((lOutputString = lBufferedReader.readLine()) != null)
	    	// Since we are writing the same size of line returned by BufferedReader, buffering is useless here, so we throw to system.out directly 
			System.out.println(lIFindAndReplace.findAndReplace(lOutputString, lTargetString, lReplacementString));

	}
	
	private static void validateArguments(String[] args)throws Exception {
		
		if(args.length < 3) {
			
			throw new InvalidArgumentException("Error : Invalid Arguments. Usage : searchreplace.exe type \"target text\" \"replacement text\" ");
		}
		
		String lTypeString = args[0];
		
		if(!lTypeString.equalsIgnoreCase("txt") && !lTypeString.equalsIgnoreCase("xml") ) {
			throw new InvalidArgumentException("Error : Accepted Formats are TXT and XML");
		}
		
		String lTargetString = args[1];
		String lReplacementString = args[2];
		
		//for simplicity and since we handle xml attributes we prevent using quotes escaped or not in case of xml type
		if(lTypeString.equalsIgnoreCase("xml")) {
			
			if(lTargetString.contains("'") || lTargetString.equals("\"") || lReplacementString.contains("'") || lReplacementString.equals("\"")){
				throw new InvalidArgumentException("Error : You can't use quotes with XML type");
			}
		}
		
		
	}
}
