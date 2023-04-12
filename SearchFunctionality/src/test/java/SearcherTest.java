import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SearcherTest {

    Searcher searcher;
    List<String> searchResult;

    @BeforeEach
    void setup() {
        searcher = new Searcher();
    }

    @Test
    void noResult_whenZeroCharacters() {
        searchResult = searcher.search("");

        assertThat(searchResult).isEmpty();
    }

    @Test
    void noResult_whenOneCharacter() {
        searchResult = searcher.search("V");

        assertThat(searchResult).isEmpty();
    }

    @Test
    void shouldReturnCityNamesStartingAsGiven_whenMoreEqualTwoCharacters() {
        searchResult = searcher.search("Va");

        assertThat(searchResult).isNotEmpty();
    }

    @Test
    void shouldReturnValenciaAndVancouver_whenVaSearchPhrase() {
        searchResult = searcher.search("Va");

        assertThat(searchResult).contains("Valencia", "Vancouver");
    }
}
