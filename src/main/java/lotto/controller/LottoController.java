package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        //돈 받기
        long userPrice = inputView.inputUserPrice();
        //돈 받은걸 바탕으로 로또 생성
        lottoService
        //당첨 번호 받기
        //보너스 번호 받기
        //당첨 통계
        //결과 출력
    }
}
