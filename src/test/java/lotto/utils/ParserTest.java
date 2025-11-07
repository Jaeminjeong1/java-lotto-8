package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    @DisplayName("공백이 포함되도 파싱된다.")
    @Test
    void 공백_포함_파싱_테스트() {
        String input = "1, ,3,4,5,6";

        List<String> result = Parser.parse(input);

        assertThat(result).containsExactly("1", " ", "3", "4", "5", "6");
    }

    @DisplayName("빈 토큰이 있거나, 마지막에 Delimiter가 있어도 파싱된다.")
    @Test
    void 파싱_테스트() {
        String input = "1,,3,4,5,6,";

        List<String> result = Parser.parse(input);

        assertThat(result).containsExactly("1", "", "3", "4", "5", "6", "");
    }
}
