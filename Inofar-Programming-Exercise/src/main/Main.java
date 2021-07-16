package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import core.AParser;
import core.CaseInsensitiveFindAndReplace;
import core.IFindAndReplace;
import core.IPrinter;
import core.StandardPrinter;
import core.TextParser;
import core.XmlParser;
import core.exceptions.InvalidArgumentException;

enum FileTypeEnum{
	
	TXT("TXT"),
	XML("XML");
	
	private String cNameString;
	
	FileTypeEnum(String pNameString) {
		this.cNameString = pNameString;
	}

	public String getcNameString() {
		return cNameString;
	}
	
}
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
	
	private static FileTypeEnum cTargetEnum;
	
	public static void main(String...args) throws Exception{
		
		validateArguments(args);
		
	
		final String lTargetString = args[1];
		final String lReplacementString = args[2];
		
		// Some polymorphism for extensibility
		final IPrinter lPrinter = new StandardPrinter();
		final IFindAndReplace lIFindAndReplace = new CaseInsensitiveFindAndReplace();
		//lIFindAndReplace = new CaseSensitiveFindAndReplace();
		AParser lParser = Main.cTargetEnum == FileTypeEnum.TXT ? new TextParser(lIFindAndReplace,lPrinter) : new XmlParser(lIFindAndReplace,lPrinter);
		
		// Buffered Reader is used for efficiency and overriding its buffer size for the same reason
		BufferedReader lBufferedReader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")), 2 * 100);
		lParser.parse(lBufferedReader, lTargetString, lReplacementString);
		
	}
	
	private static void validateArguments(String[] args)throws Exception {
		
		if(args.length < 3) {
			
			throw new InvalidArgumentException("Error : Invalid Arguments. Usage : searchreplace.exe type \"target text\" \"replacement text\" ");
		}
		
		try {
			cTargetEnum = FileTypeEnum.valueOf(args[0].toUpperCase());
		}catch(Exception ex) {
			throw new InvalidArgumentException("Error : Accepted Formats are TXT and XML");
		}
		
	}
}
