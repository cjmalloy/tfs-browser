package com.cjmalloy.tfsbrowser.client.ui.view;

import com.cjmalloy.multipage.client.ui.Page;
import com.cjmalloy.tfsbrowser.client.remote.TfsAsync;
import com.cjmalloy.tfsbrowser.client.remote.TfsAsync.TfsAsyncFactory;
import com.cjmalloy.torrentfs.shared.model.TorrentStatus;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class MainPage extends Page {

  private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);
  private static TfsAsync remote = TfsAsyncFactory.get();
  private static int HEADER_SIZE = 40;

  interface MainPageUiBinder extends UiBinder<Widget, MainPage> {}
  @UiField(provided = true)
  MainPage page = this;
  @UiField
  PushButton selectTorrent;
  @UiField
  TextBox infoHash;
  @UiField
  Frame iframe;

  public MainPage() {
    initWidget(uiBinder.createAndBindUi(this));
    Window.setTitle("Tfs Browser");
    Window.addResizeHandler(this);
  }

  @Override
  public void onHide() {}

  @Override
  public void onShow() {}

  @Override
  public void setPixelSize(int width, int height) {
    super.setPixelSize(width, height);
    iframe.setPixelSize(width, height - 40);
  }

  @UiHandler("selectTorrent")
  void onSelectTorrent(ClickEvent event) {
    selectTorrent.setEnabled(false);
    remote.getStatus(infoHash.getText(), new MethodCallback<TorrentStatus>() {
      @Override
      public void onFailure(Method method, Throwable e) {
        selectTorrent.setEnabled(true);

        Window.alert(e.getMessage());
        throw new Error(e);
      }

      @Override
      public void onSuccess(Method method, TorrentStatus response) {
        selectTorrent.setEnabled(true);
        iframe.setUrl("/ext/html/index/" + infoHash.getText());
      }
    });
  }

  public int headerSize() {
    return HEADER_SIZE;
  }

  public static PageFactory getFactory() {
    return new PageFactory() {
      @Override
      public void create(final Callback<Page, Throwable> callback) {
        callback.onSuccess(new MainPage());
      }
    };
  }

}
