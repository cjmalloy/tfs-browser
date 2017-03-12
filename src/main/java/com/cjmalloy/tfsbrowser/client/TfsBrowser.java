package com.cjmalloy.tfsbrowser.client;

import com.cjmalloy.multipage.client.MultiPage;
import com.cjmalloy.tfsbrowser.client.ui.skin.StyleBundle;
import com.cjmalloy.tfsbrowser.client.ui.view.MainPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import org.fusesource.restygwt.client.Defaults;


public class TfsBrowser implements EntryPoint {

  public static final String PAGE_INDEX = "";
  public static final String PAGE_GWTGAMEUI = "gwtgameui";
  public static final String PAGE_GWTGAMEUIDEMOS = "gwtgameuidemos";

  private static MultiPage multiPage = new MultiPage(StyleBundle.INSTANCE.transition(), StyleBundle.TRANSITION_REMOVE_DELAY);

  static {
    // RestyGWT config
    Defaults.setServiceRoot("/");
    Defaults.setDateFormat(null);
  }

  static {
    multiPage.put(PAGE_INDEX, MainPage.getFactory(), true);
  }

  public void onModuleLoad() {
    StyleBundle.EnsureInjected.inject();
    History.fireCurrentHistoryState();
  }

}
