package android.nekomem.actions;

/**
 * Created by 54179 on 2016/12/8.
 */

public interface TodoActions {
    String TODO_CREATE = "todo-create";
    String TODO_COMPLETE = "todo-complete";
    String TODO_DESTROY = "todo-destroy";
    String TODO_DESTROY_COMPLETED = "todo-destroy-completed";
    String TODO_TOGGLE_COMPLETE_ALL = "todo-toggle-complete-all";
    String TODO_UNDO_COMPLETE = "todo-undo-complete";
    String TODO_UNDO_DESTROY = "todo-undo-destroy";
    String TODO_MOVE = "todo-move";

    String KEY_TEXT = "key-text";
    String KEY_ID = "key-id";
    String POS_FROM = "pos-from";
    String POS_TO = "pos-to";
}