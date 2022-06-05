package unit;

import fr.zbar.kata.dictionaryreplacer.Dictionary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DictionaryReplacerTest {

    @Test
    void should_be_empty_when_no_input_and_no_dictionary() {
        String input = "";
        Dictionary dictionary = new Dictionary();

        assertThat(Dictionary.replace(input, dictionary)).isEmpty();
    }

    @Test
    void should_be_empty_when_no_input_and_dictionary() {
        String input = "";
        Dictionary dictionary = new Dictionary(
                new Dictionary.Entry("temp", "temporary")
        );

        assertThat(Dictionary.replace(input, dictionary)).isEmpty();
    }

    @Test
    void should_throw_exception_when_input_is_null() {
        String input = null;
        Dictionary dictionary = new Dictionary(
                new Dictionary.Entry("temp", "temporary")
        );

        assertThatThrownBy(() -> Dictionary.replace(input, dictionary))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("input is null");
    }

    @Test
    void should_throw_exception_when_dictionary_is_null() {
        String input = "";
        Dictionary dictionary = null;

        assertThatThrownBy(() -> Dictionary.replace(input, dictionary))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("dictionary is null");
    }

    @Test
    void should_replace_input_template_when_matching_dictionary() {
        String input = "$temp$";
        Dictionary dictionary = new Dictionary(
                new Dictionary.Entry("temp", "temporary")
        );

        assertThat(Dictionary.replace(input, dictionary)).isEqualTo("temporary");
    }

    @Test
    void should_replace_input_multiple_templates_when_matching_dictionary() {
        String input = "$temp$ here comes the name $name$";
        Dictionary dictionary = new Dictionary(
                new Dictionary.Entry("temp", "temporary"),
                new Dictionary.Entry("name", "John Doe")
        );

        assertThat(Dictionary.replace(input, dictionary)).isEqualTo("temporary here comes the name John Doe");
    }
}
