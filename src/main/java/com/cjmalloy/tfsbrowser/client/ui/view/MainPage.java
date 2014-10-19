package com.cjmalloy.tfsbrowser.client.ui.view;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.cjmalloy.multipage.client.ui.Page;
import com.cjmalloy.tfsbrowser.client.remote.TfsAsync;
import com.cjmalloy.tfsbrowser.client.remote.TfsAsync.TfsAsyncFactory;
import com.cjmalloy.torrentfs.shared.model.TorrentStatus;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Page
{
    private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);
    interface MainPageUiBinder extends UiBinder<Widget, MainPage> {}

    private static TfsAsync remote = TfsAsyncFactory.get();

    @UiField
    PushButton selectTorrent;
    @UiField
    TextBox infoHash;
    @UiField
    Frame iframe;

    public MainPage()
    {
        initWidget(uiBinder.createAndBindUi(this));
        Window.setTitle("Tfs Browser");
        Window.addResizeHandler(this);
    }

    @Override
    public void onHide() {}

    @Override
    public void onShow() {}

    @Override
    public void setPixelSize(int width, int height)
    {
        super.setPixelSize(width, height);
        iframe.setPixelSize(width, height-40);
    }

    @UiHandler("selectTorrent")
    void onSelectTorrent(ClickEvent event)
    {
        selectTorrent.setEnabled(false);
        remote.getStatus(infoHash.getText(), new MethodCallback<TorrentStatus>()
        {
            @Override
            public void onFailure(Method method, Throwable e)
            {
                selectTorrent.setEnabled(true);

                Window.alert(e.getMessage());
                throw new Error(e);
            }

            @Override
            public void onSuccess(Method method, TorrentStatus response)
            {
                selectTorrent.setEnabled(true);
                iframe.setUrl("/ext/html/index/" + infoHash.getText());
            }
        });
    }

    public static PageFactory getFactory()
    {
        return new PageFactory()
        {
            @Override
            public void create(final Callback<Page, Throwable> callback)
            {
                callback.onSuccess(new MainPage());
            }
        };
    }

}
