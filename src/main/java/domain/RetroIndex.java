package domain;

import java.util.List;
import java.util.Map;

/**
 * Created by Guillaume on 06/12/2017.
 */

// Construit par l'indexer
public class RetroIndex {

    private List<Document> documents;

    private Map<String, List<Document>> map;

    public RetroIndex(final List<Document> documents, final Map<String, List<Document>> map) {
        this.documents = documents;
        this.map = map;
    }
}
