package service;

import domain.Document;
import domain.RetroIndex;

public interface IIndexer {

    String request(URLRepo repo);

    Document index();

    void publish(RetroIndex retroIndex);

}
