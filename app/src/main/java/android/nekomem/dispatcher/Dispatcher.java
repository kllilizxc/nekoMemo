package android.nekomem.dispatcher;

import android.nekomem.actions.Action;
import android.nekomem.stores.Store;
import com.squareup.otto.Bus;

/**
 * Created by 54179 on 2016/12/8.
 */

public class Dispatcher {
    private final Bus bus;
    private static Dispatcher instance;

    public Dispatcher(Bus bus) {
        this.bus = bus;
    }

    public static Dispatcher get(Bus bus) {
        if(instance == null)
            instance = new Dispatcher(bus);
        return instance;
    }

    public void register(final Object cls) {
        bus.register(cls);
    }

    public void unregister(final Object cls) {
        bus.unregister(cls);
    }

    public void emitChange(Store.StoreChangeEvent o) {
        post(o);
    }

    public void dispatch(String type, Object... data) {
        if(isEmpty(type))
            throw new IllegalArgumentException("Type must not be empty");
        if(data.length % 2 != 0)
            throw new IllegalArgumentException("Data must be a valid list of key, value pairs");

        Action.Builder actionBuilder = Action.type(type);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        post(actionBuilder.build());
    }

    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    private void post(final Object event) {
        bus.post(event);
    }
}
