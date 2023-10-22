package cz.muni.fi.pv168.project.todoapp.ui.filter;

public interface Matcher<T> {
    boolean match(T other);
}
