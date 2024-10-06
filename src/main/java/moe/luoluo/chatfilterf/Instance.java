package moe.luoluo.chatfilterf; /* /Instance.java */

import java.util.HashSet;
import moe.luoluo.chatfilterf.filter.FilterEntry;

public enum Instance {

INSTANCE;

public HashSet<FilterEntry> filters = new HashSet<>();

}