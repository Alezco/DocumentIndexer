package valueobject;

import java.net.URL;
import java.util.List;

public class Document {

    public final URL url;

    public final List<Term> terms;

    public Document(final URL url, final List<Term> terms) {
        this.url = url;
        this.terms = terms;
    }
}
