package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetroIndex {

    private List<Document> documents;

    private Map<String, List<Document>> map;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(final List<Document> documents) {
        this.documents = documents;
    }

    public Map<String, List<Document>> getMap() {
        return map;
    }

    public void setMap(final Map<String, List<Document>> map) {
        this.map = map;
    }

    public RetroIndex() {
        this.documents = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public RetroIndex(final List<Document> documents, final Map<String, List<Document>> map) {
        this.documents = documents;
        this.map = map;
    }
}
