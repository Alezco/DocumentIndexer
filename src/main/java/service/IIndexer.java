package service;

import domain.Document;
import domain.RetroIndex;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface IIndexer {

    String request(Repo repo);

    Document index();

    void publish(RetroIndex retroIndex);

}
