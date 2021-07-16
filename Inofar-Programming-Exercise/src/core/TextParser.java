package core;

import java.io.BufferedReader;

public class TextParser extends AParser{


	public TextParser(IFindAndReplace pIFindAndReplace, IPrinter pPrinter) {
		super(pIFindAndReplace, pPrinter);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void parse(BufferedReader pReader, String pTargetString, String pReplacementString) {
		
		pReader.lines().forEach(lLineString -> {
			cPrinter.print(cIFindAndReplace.findAndReplace(lLineString, pTargetString, pReplacementString));
		});
		
	}


	
}
