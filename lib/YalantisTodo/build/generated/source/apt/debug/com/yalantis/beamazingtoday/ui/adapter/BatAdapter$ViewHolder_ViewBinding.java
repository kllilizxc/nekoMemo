// Generated code from Butter Knife. Do not modify!
package com.yalantis.beamazingtoday.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.yalantis.beamazingtoday.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BatAdapter$ViewHolder_ViewBinding<T extends BatAdapter.ViewHolder> implements Unbinder {
  protected T target;

  private View view2131492973;

  private View view2131492993;

  private View view2131492992;

  private View view2131492991;

  public BatAdapter$ViewHolder_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.root, "field 'rootView' and method 'onClick'");
    target.rootView = view;
    view2131492973 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    view = finder.findRequiredView(source, R.id.text_view, "field 'textView' and method 'onClick'");
    target.textView = finder.castView(view, R.id.text_view, "field 'textView'", TextView.class);
    view2131492993 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.radioButton = finder.findRequiredViewAsType(source, R.id.radio_button, "field 'radioButton'", AppCompatCheckBox.class);
    target.divider = finder.findRequiredView(source, R.id.divider, "field 'divider'");
    view = finder.findRequiredView(source, R.id.clickable_view, "method 'onCheck'");
    view2131492992 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCheck();
      }
    });
    view = finder.findRequiredView(source, R.id.full_list_item, "method 'onOutsideClicked'");
    view2131492991 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onOutsideClicked();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rootView = null;
    target.textView = null;
    target.radioButton = null;
    target.divider = null;

    view2131492973.setOnClickListener(null);
    view2131492973 = null;
    view2131492993.setOnClickListener(null);
    view2131492993 = null;
    view2131492992.setOnClickListener(null);
    view2131492992 = null;
    view2131492991.setOnClickListener(null);
    view2131492991 = null;

    this.target = null;
  }
}
