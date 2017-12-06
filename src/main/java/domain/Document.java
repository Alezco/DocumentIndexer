package domain;

import java.net.URL;
import java.util.List;

public class Document {

    private final URL url;

    private final List<Term> terms;

    public URL getUrl() {
        return url;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public Document(final URL url, final List<Term> terms) {
        this.url = url;
        this.terms = terms;
    }
}
