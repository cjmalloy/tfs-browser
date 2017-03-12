package com.cjmalloy.tfsbrowser.client.ui.skin;

import com.cjmalloy.multipage.client.ui.skin.PageTransition;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;


public interface StyleBundle extends ClientBundle {

  StyleBundle INSTANCE = GWT.create(StyleBundle.class);
  int TRANSITION_REMOVE_DELAY = 0;

  interface Style extends CssResource {
    String header();
  }

  Style style();

  PageTransition transition();

  class EnsureInjected {
    public static void inject() {
      INSTANCE.style().ensureInjected();
      INSTANCE.transition().ensureInjected();
    }
  }
}
