package service;

import domain.Document;

import java.net.URL;

public interface IIndexer {

    void request(URL url);

    /*Document index();

    void publish(RetroIndex retroIndex);*/

    String cleanup(final String input);

    String tokenize(final String input);

    String reduce(final String input);

    Document index(final String input);

}
