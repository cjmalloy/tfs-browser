package com.cjmalloy.tfsbrowser.client.remote;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.cjmalloy.torrentfs.shared.model.TorrentStatus;
import com.google.gwt.core.client.GWT;

@Path("/")
public interface TfsAsync extends RestService
{
    @GET @Path("/add/{info_hash}")
    void addTorrent(@PathParam("info_hash") String infoHash, MethodCallback<String> callback);

    @POST @Path("/print")
    void requestLoginToken(@PathParam("info_hash") String infoHash, MethodCallback<String> callback);

    @PUT @Path("/status")
    void login(@PathParam("info_hash") String infoHash, MethodCallback<TorrentStatus> callback);

    public static final class TfsAsuncFactory
    {
        private static TfsAsync instance;

        public static final TfsAsync get()
        {
            if (instance == null)
            {
                instance = GWT.create(TfsAsync.class);
            }
            return instance;
        }
    }
}
