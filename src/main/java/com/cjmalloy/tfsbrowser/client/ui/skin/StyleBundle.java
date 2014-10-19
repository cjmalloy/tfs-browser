package com.cjmalloy.tfsbrowser.client.ui.skin;

import com.cjmalloy.multipage.client.ui.skin.PageTransition;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;


public interface StyleBundle extends ClientBundle
{
    public static final StyleBundle INSTANCE = GWT.create(StyleBundle.class);
    public static final int TRANSITION_REMOVE_DELAY = 0;

    Style style();

    PageTransition transition();

    public interface Style extends CssResource
    {
        String header();
    }

    public static class EnsureInjected
    {
        public static void inject()
        {
            INSTANCE.style().ensureInjected();
            INSTANCE.transition().ensureInjected();
        }
    }
}
