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

    @Test
    @DisplayName("여러 로또에 대해 등수를 계산하고, 등수별 개수를 집계한다")
    void 로또_등수_계산_및_개수_집계() {
        LottoDto mine1 = new LottoDto(List.of(1, 2, 3, 4, 5, 6));   // 6개 일치 -> FIRST
        LottoDto mine2 = new LottoDto(List.of(1, 2, 3, 10, 11, 12));// 3개 일치 -> FIFTH
        LottoDto mine3 = new LottoDto(List.of(7, 8, 9, 10, 11, 12));// 0개 + 보너스(7) 포함 -> MISS
        List<LottoDto> lottos = List.of(mine1, mine2, mine3);

        //당첨로또
        LottoDto winner = new LottoDto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 7;

        List<Result> results = lottoService.calculateResults(lottos, winner, bonus);

        assertThat(results).containsExactly(
                Result.FIRST, Result.FIFTH, Result.MISS
        );

        Map<Result, Long> summary = lottoService.summarizeResults(results);
        assertThat(summary.get(Result.FIRST)).isEqualTo(1L);
        assertThat(summary.get(Result.FIFTH)).isEqualTo(1L);
        assertThat(summary.get(Result.MISS)).isEqualTo(1L);
    }
}
