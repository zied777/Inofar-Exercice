package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaseInsensitiveFindAndReplace implements IFindAndReplace{

	@Override
	public String findAndReplace(String input, String target, String replacement) {
		
		return Pattern.compile(target, 
				Pattern.LITERAL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(input)
				.replaceAll(Matcher.quoteReplacement(replacement));
	}

}
