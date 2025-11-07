package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public static LottoService createService() {
        return new LottoService();
    }

    public static LottoController createController() {
        return new LottoController(InputView.Companion.create(), OutputView.create(), createService());
    }
}
