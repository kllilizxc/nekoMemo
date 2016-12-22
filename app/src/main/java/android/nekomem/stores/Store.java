package android.nekomem.stores;

import android.nekomem.actions.Action;
import android.nekomem.dispatcher.Dispatcher;

/**
 * Created by 54179 on 2016/12/8.
 */

public abstract class Store {
    final Dispatcher dispatcher;

    public interface StoreChangeEvent {}

    abstract StoreChangeEvent changeEvent();

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    public abstract void onAction(Action action);
}
