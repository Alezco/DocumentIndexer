package valueobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetroIndex {

    public final List<Document> documents;

    public final Map<String, List<Document>> map;

    public RetroIndex() {
        this.documents = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public RetroIndex(final List<Document> documents, final Map<String, List<Document>> map) {
        this.documents = documents;
        this.map = map;
    }
}
