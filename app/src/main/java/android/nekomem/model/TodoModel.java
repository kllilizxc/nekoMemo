package android.nekomem.model;

import com.yalantis.beamazingtoday.interfaces.BatModel;

/**
 * Created by 54179 on 2016/12/8.
 */

public class TodoModel implements BatModel, Cloneable, Comparable<TodoModel> {
    private long id;
    private boolean checked;
    private String text;

    public TodoModel(long id, String text, boolean checked) {
        this.id = id;
        this.text = text;
        this.checked = checked;
    }

    public TodoModel(long id, String text) {
        this(id, text, false);
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public TodoModel clone() {
        return new TodoModel(id, text, checked);
    }

    @Override
    public int compareTo(TodoModel todoModel) {
        return (int)(id - todoModel.getId());
    }
}
