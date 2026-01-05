package lotto.controller;

import lotto.service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void start() {
        // 구입 금액 입력 받기
        // 구입 금액에 맞게 로또 생성
        // 당첨번호 입력받기
        // 보너스 번호 입력받기
        // 결과 출력
    }
}
