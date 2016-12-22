// Generated code from Butter Knife. Do not modify!
package com.yalantis.beamazingtoday.ui.widget;

import android.support.v7.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.yalantis.beamazingtoday.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AddView_ViewBinding<T extends AddView> implements Unbinder {
  protected T target;

  public AddView_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.mHorizontalView = finder.findRequiredViewAsType(source, R.id.view_horizontal, "field 'mHorizontalView'", AppCompatImageView.class);
    target.mVerticalView = finder.findRequiredViewAsType(source, R.id.view_vertical, "field 'mVerticalView'", AppCompatImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mHorizontalView = null;
    target.mVerticalView = null;

    this.target = null;
  }
}
