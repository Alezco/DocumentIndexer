package service;

import valueobject.Document;

public interface IIndexer {

    String cleanup(final String input);

    String tokenize(final String input);

    String reduce(final String input);

    Document index(final String input);
}
