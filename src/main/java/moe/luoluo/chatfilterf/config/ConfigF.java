package moe.luoluo.chatfilterf.config; /* /config/ConfigF.java */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import moe.luoluo.chatfilterf.filter.FilterEntry;

public class ConfigF {

private File file;

public ConfigF() {
this.file = new File("./config/cff.json");
}

public HashSet<FilterEntry> load() {

HashSet<FilterEntry> activedFilters = new HashSet<>();
try (FileReader reader = new FileReader(file)) {
Gson gson = new Gson();
Type filterListType = new TypeToken<List<List<String>>>() {}.getType();
List<List<String>> filters = gson.fromJson(reader, filterListType);

String name;
String pattern;
String replacement;
String saychat;
FilterEntry filter;

for (List<String> filterData : filters) {

if (filterData.size() == 4) {
name = filterData.get(0);
pattern = filterData.get(1);
replacement = filterData.get(2);
saychat = filterData.get(3);

filter = new FilterEntry(name, pattern, replacement, saychat);
activedFilters.add(filter);

}}} catch (IOException e) {e.printStackTrace(); }

return activedFilters;

}

public void save(HashSet<FilterEntry> activedFilters) {

List<List<String>> filtersData = new ArrayList<>();
for (FilterEntry filter : activedFilters) {

List<String> filterInfo = new ArrayList<>();
filterInfo.add(filter.getName());
filterInfo.add(filter.getPattern().pattern());
filterInfo.add(filter.getReplacement());
filterInfo.add(filter.getSaychat());
filtersData.add(filterInfo);

}

try (FileWriter writer = new FileWriter(file)) {
Gson gson = new Gson();
gson.toJson(filtersData, writer);

} catch (IOException e) {e.printStackTrace(); }

}
}