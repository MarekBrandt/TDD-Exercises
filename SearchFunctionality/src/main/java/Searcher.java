import java.util.List;

public class Searcher {
    public List<String> search(String searchPhrase) {
        if(searchPhrase.length() < 2) {
            return List.of();
        }
        return List.of(searchPhrase);
    }
}
