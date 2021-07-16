package core;

import java.io.BufferedReader;

public abstract class AParser {

	protected IFindAndReplace cIFindAndReplace;
	protected IPrinter cPrinter;
	
	public AParser(IFindAndReplace pIFindAndReplace, IPrinter pPrinter) {
		this.cIFindAndReplace = pIFindAndReplace;
		this.cPrinter = pPrinter;
	}
	
	public abstract <T extends BufferedReader> void parse(T pStreamOfString, String pTargetString, String pReplacementString) throws Exception;
	
	
}
