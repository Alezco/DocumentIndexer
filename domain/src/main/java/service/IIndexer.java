package service;

import valueobject.Document;

import java.net.URL;

public interface IIndexer {

    void request(final URL url);

    String cleanup(final String input);

    String tokenize(final String input);

    String reduce(final String input);

    Document index(final String input);
}
