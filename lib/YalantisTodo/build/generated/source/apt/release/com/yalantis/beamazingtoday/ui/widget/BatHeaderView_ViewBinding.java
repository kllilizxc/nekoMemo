// Generated code from Butter Knife. Do not modify!
package com.yalantis.beamazingtoday.ui.widget;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.yalantis.beamazingtoday.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BatHeaderView_ViewBinding<T extends BatHeaderView> implements Unbinder {
  protected T target;

  private View view2131492974;

  private View view2131492977;

  private View view2131492973;

  public BatHeaderView_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.add_view, "field 'mAddView' and method 'onClick'");
    target.mAddView = finder.castView(view, R.id.add_view, "field 'mAddView'", AddView.class);
    view2131492974 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.mEditText = finder.findRequiredViewAsType(source, R.id.bat_edit_text, "field 'mEditText'", BatEditText.class);
    view = finder.findRequiredView(source, R.id.button_add, "field 'mButtonAdd' and method 'addItem'");
    target.mButtonAdd = finder.castView(view, R.id.button_add, "field 'mButtonAdd'", Button.class);
    view2131492977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addItem();
      }
    });
    view = finder.findRequiredView(source, R.id.root, "field 'mRoot' and method 'onClick'");
    target.mRoot = view;
    view2131492973 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.mRadioButton = finder.findRequiredViewAsType(source, R.id.radio_button, "field 'mRadioButton'", AppCompatCheckBox.class);
    target.mDivider = finder.findRequiredView(source, R.id.divider, "field 'mDivider'");
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mAddView = null;
    target.mEditText = null;
    target.mButtonAdd = null;
    target.mRoot = null;
    target.mRadioButton = null;
    target.mDivider = null;

    view2131492974.setOnClickListener(null);
    view2131492974 = null;
    view2131492977.setOnClickListener(null);
    view2131492977 = null;
    view2131492973.setOnClickListener(null);
    view2131492973 = null;

    this.target = null;
  }
}
