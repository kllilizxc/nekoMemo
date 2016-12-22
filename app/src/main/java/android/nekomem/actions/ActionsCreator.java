package android.nekomem.actions;

import android.nekomem.dispatcher.Dispatcher;
import android.nekomem.model.TodoModel;

/**
 * Created by 54179 on 2016/12/8.
 */

public class ActionsCreator {
    private static ActionsCreator instance;
    final Dispatcher dispatcher;

    public ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if(instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    public void create(String text) {
        dispatcher.dispatch(TodoActions.TODO_CREATE,
                            TodoActions.KEY_TEXT, text);
    }

    public void destroy(long id){
        dispatcher.dispatch(TodoActions.TODO_DESTROY,
                            TodoActions.KEY_ID, id);
    }

    public void move(int from, int to) {
        dispatcher.dispatch(TodoActions.TODO_MOVE,
                            TodoActions.POS_FROM, from,
                            TodoActions.POS_TO, to);
    }

    public void incScore() {
        dispatcher.dispatch(ScoreActions.SCORE_INC);
    }

    public void decScore() {
        dispatcher.dispatch(ScoreActions.SCORE_DEC);
    }

    public void undoDestroy() {
        dispatcher.dispatch(TodoActions.TODO_UNDO_DESTROY);
    }

    public void toggleComplete(TodoModel todoModel) {
        long id = todoModel.getId();
        String actionType = todoModel.isChecked() ? TodoActions.TODO_UNDO_COMPLETE : TodoActions.TODO_COMPLETE;

        dispatcher.dispatch(actionType,
                            TodoActions.KEY_ID, id);
    }

    public void toggleCompleteAll() {
        dispatcher.dispatch(TodoActions.TODO_TOGGLE_COMPLETE_ALL);
    }

    public void destroyCompleted() {
        dispatcher.dispatch(TodoActions.TODO_DESTROY_COMPLETED);
    }
}
