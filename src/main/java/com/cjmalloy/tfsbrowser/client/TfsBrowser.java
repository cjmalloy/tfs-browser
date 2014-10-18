package com.cjmalloy.tfsbrowser.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;


public class TfsBrowser implements EntryPoint
{

    static
    {
        // RestyGWT config
        Defaults.setServiceRoot("/");
        Defaults.setDateFormat(null);
    }

    @Override
    public void onModuleLoad()
    {
        // TODO Auto-generated method stub

    }

}
