// Generated code from Butter Knife. Do not modify!
package com.yalantis.beamazingtoday.ui.widget;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.yalantis.beamazingtoday.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BatRecyclerView_ViewBinding<T extends BatRecyclerView> implements Unbinder {
  protected T target;

  public BatRecyclerView_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.mRecyclerView = finder.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.mHeaderView = finder.findRequiredViewAsType(source, R.id.header_view, "field 'mHeaderView'", BatHeaderView.class);
    target.mBackground = finder.findRequiredViewAsType(source, R.id.view, "field 'mBackground'", AppCompatImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRecyclerView = null;
    target.mHeaderView = null;
    target.mBackground = null;

    this.target = null;
  }
}
