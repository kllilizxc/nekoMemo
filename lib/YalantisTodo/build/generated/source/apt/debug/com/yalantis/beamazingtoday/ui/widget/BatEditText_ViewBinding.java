// Generated code from Butter Knife. Do not modify!
package com.yalantis.beamazingtoday.ui.widget;

import android.support.v7.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.yalantis.beamazingtoday.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BatEditText_ViewBinding<T extends BatEditText> implements Unbinder {
  protected T target;

  public BatEditText_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.mCursor = finder.findRequiredViewAsType(source, R.id.cursor, "field 'mCursor'", AppCompatImageView.class);
    target.mEditText = finder.findRequiredViewAsType(source, R.id.edit_text, "field 'mEditText'", WatcherEditText.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mCursor = null;
    target.mEditText = null;

    this.target = null;
  }
}
