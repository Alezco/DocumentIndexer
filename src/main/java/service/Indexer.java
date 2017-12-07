package service;

import domain.RetroIndex;
import domain.Term;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Indexer implements IIndexer {

    private URL crawlingUrl;
    private Document document;
    private RetroIndex retroIndex;

    private final URLRepo urlRepo;

    List<String> stopWords = Arrays.asList("a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero");

    public Indexer(final URLRepo urlRepo) {

        // TEST
        retroIndex = new RetroIndex();

        this.urlRepo = urlRepo;

        requestNextUrl();
    }



    public void request(final URL url) {
        //URL url = repo.request();
        try {
            Document doc = Jsoup.connect(url.toURI().toString()).get();
            String content = doc.body().text();

            domain.Document dd = index(content);
            retroIndex.getDocuments().add(dd);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        requestNextUrl();
    }

    /**
     * Suppression du contenu superflu
     * @param input
     * @return
     */
    @Override
    public String cleanup(final String input) {
        StringBuilder result = new StringBuilder();
        List<String> list = Arrays.asList("\n", ",", ".", ";", "(", ")", "-");
        String[] words = input.split("\\s+");
        for (String word : words)
        {
            if (list.contains(word))
                continue;
            result.append(word);
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * Déstructuration du flux, suppression des stop words
     * @param input
     * @return
     */
    @Override
    public String tokenize(final String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");
        for (String word : words) {

            // Stop Words
            if (stopWords.contains(word))
                continue;
            result.append(word);
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * Stemming naïf
     * @param input
     * @return
     */
    @Override
    public String reduce(final String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");
        for (String word : words) {
            // Stemmer
            final String token = basicStemmer(word);
            result.append(token);
            result.append(" ");
        }
        return result.toString();
    }

    private String basicStemmer(String word) {
        if (word.endsWith("ing"))
            word = word.substring(0, word.lastIndexOf("ing"));
        if (word.endsWith("ed"))
            word = word.substring(0, word.lastIndexOf("ed"));
        if (word.endsWith("ly"))
            word = word.substring(0, word.lastIndexOf("ly"));
        return word;
    }

    @Override
    public domain.Document index(final String input) {

        List<Term> terms = new ArrayList<>();

        String cleanup = this.cleanup(input);
        float nbWords = cleanup.split("\\s+").length;
        String tokenize = this.tokenize(cleanup);
        String reduce = this.reduce(tokenize);

        String[] words = reduce.split("\\s+");
        List<String> tokens = Arrays.asList(words);

        // Frequence et positions
        Map<String, Integer> countsOccurence = new HashMap<>();

        for (String word : words) {

            // Compte des occurences
            if (countsOccurence.containsKey(word))
                countsOccurence.put(word, countsOccurence.get(word) + 1 );
            else
                countsOccurence.put(word, 1);
        }

        Set keys = countsOccurence.keySet();
        Iterator it = keys.iterator();

        while (it.hasNext()) {
            Object key = it.next();
            int occurence = countsOccurence.get(key);
            float tf = (float)occurence / nbWords;

            // Positions
            List<Integer> positions = new ArrayList<>();
            String[] strings = cleanup.split("\\s+");

            for (int i = 0; i < strings.length; i++)
            {
                if (strings[i].equals(key.toString()) || strings[i].equals(key.toString() +"ed")
                        || strings[i].equals(key.toString() +"ing") || strings[i].equals(key.toString() +"ed"))
                    positions.add(i + 1);
            }

            Term term = new Term(key.toString(), positions, tf);
            terms.add(term);
        }

        System.out.println("=====Document=====");
        for (Term t : terms) {
            System.out.println("token: " + t.getToken());
            System.out.println("tf: " + t.getFrequency());
            System.out.println("positions: " + t.getPositions().toString());
        }

        return new domain.Document(crawlingUrl, terms);
    }

    public void requestNextUrl() {
        this.crawlingUrl = urlRepo.request();
        if (crawlingUrl != null)
            request(crawlingUrl);
        else
            System.out.println("retro index document number : " + this.retroIndex.getDocuments().size());
    }
}
