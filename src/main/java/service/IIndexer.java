package service;

import domain.Document;

public interface IIndexer {

    String request(URLRepo repo);

    /*Document index();

    void publish(RetroIndex retroIndex);*/

    String cleanup(final String input);

    String tokenize(final String input);

    String reduce(final String input);

    Document index(final String input);

}
