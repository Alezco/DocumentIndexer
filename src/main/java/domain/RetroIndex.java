package domain;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class RetroIndex {

    private List<Document> documents;

    private Map<URL, List<Document>> map;

    public RetroIndex(final List<Document> documents, final Map<URL, List<Document>> map) {
        this.documents = documents;
        this.map = map;
    }
}
