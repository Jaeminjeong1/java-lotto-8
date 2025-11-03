package lotto.service;

import lotto.domain.Result;
import lotto.dto.LottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static lotto.utils.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

public class LottoServiceTest {

    LottoService lottoService = new LottoService();

    @DisplayName("구매금액/1000 = 생성 장수")
    @ParameterizedTest
    @ValueSource(longs = {1000, 3000, 15000})
    void 로또_장수_테스트(long userPrice) {
        List<LottoDto> lottos = lottoService.generateLottos(userPrice);

        assertThat(lottos).hasSize((int) (userPrice / 1000));
    }

    @Test
    @DisplayName("trim된 정수로 잘 파싱하여 LottoDto로 반환")
    void 파싱_테스트() {
        String[] parsed = {" 1", "2 ", " 3 ", "4", "5", "6"};
        LottoDto winner = lottoService.generateWinnerLotto(parsed);

        assertThat(winner.numbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

}
