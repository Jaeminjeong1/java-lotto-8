package lotto.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParserTest {

    @DisplayName("공백이 포함되어도 파싱된다.")
    @Test
    fun `공백 포함 파싱 테스트`() {
        val input = "1, ,3,4,5,6"

        val result = Parser.parse(input)

        assertThat(result).containsExactly("1", " ", "3", "4", "5", "6")
    }

    @DisplayName("빈 토큰이 있거나 마지막에 Delimiter가 있어도 파싱된다.")
    @Test
    fun `빈 토큰 및 마지막 Delimiter 파싱 테스트`() {
        val input = "1,,3,4,5,6,"

        val result = Parser.parse(input)

        assertThat(result).containsExactly("1", "", "3", "4", "5", "6", "")
    }
}
