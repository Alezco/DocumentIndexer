package indexer;

import urlrepo.Repo;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface IIndexer {

    Url request(Repo repo);

}
