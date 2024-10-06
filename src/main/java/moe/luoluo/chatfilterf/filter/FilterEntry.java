package moe.luoluo.chatfilterf.filter; /* /Filter/FilterEntry.java */

import java.util.regex.Pattern;

public class FilterEntry {

private String name;
private Pattern pattern;
private String replacement;
private String saychat;

public FilterEntry (
String name, String pattern,
String replacement, String saychat
) {
this.name = name;
this.pattern = Pattern.compile(pattern);
this.replacement = replacement;
this.saychat = saychat;
}

public String getName() {return name; }
public Pattern getPattern() {return pattern; }
public String getReplacement() {return replacement; }
public String getSaychat() {return saychat; }

}