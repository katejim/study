package undo;

import java.util.Stack;

/**
 * Created by KateKate on 15.05.14.
 */
public class ActionList {
    public Stack<Action> listActions = new Stack<Action>();

    public Action getLastState() {
        Action rez = null;
        if (!listActions.empty()){
            rez = listActions.peek();
            listActions.pop();
        }
        return rez;
    }

    public void addAction(Action action) {
        listActions.add(action);
    }
}
