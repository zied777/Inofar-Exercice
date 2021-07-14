package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaseSensitiveFindAndReplace implements IFindAndReplace{

	@Override
	public String findAndReplace(String input, String target, String replacement) {
		
		return Pattern.compile(target, 
				Pattern.LITERAL |Pattern.UNICODE_CASE).matcher(input)
				.replaceAll(Matcher.quoteReplacement(replacement));
	}

}
