package lotto.view;

import lotto.dto.LottoDto;

import java.util.List;

public class OutputView {

    private OutputView() {}
    public static OutputView create() {
        return new OutputView();
    }

    public void printLottos(List<LottoDto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (LottoDto dto : lottos) {
            System.out.println(dto.sorted());
        }

        System.out.println();
    }
}
