package com.cjmalloy.tfsbrowser.client.remote;

import javax.ws.rs.*;

import com.cjmalloy.torrentfs.shared.model.TorrentStatus;
import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

@Path("/")
public interface TfsAsync extends RestService {

  //  @POST
  //  @Path("/add")
  //  void addTorrent(Byte[] torrent, MethodCallback<String> callback);
  //
  //  @POST
  //  @Path("/print")
  //  void printTorrent(Byte[] torrent, MethodCallback<String> callback);

  @GET
  @Path("/status/{info_hash}")
  void getStatus(@PathParam("info_hash") String infoHash, MethodCallback<TorrentStatus> callback);

  final class TfsAsyncFactory {

    private static TfsAsync instance;

    public static TfsAsync get() {
      if (instance == null) {
        instance = GWT.create(TfsAsync.class);
      }
      return instance;
    }
  }
}
