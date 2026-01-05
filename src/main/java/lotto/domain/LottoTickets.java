package lotto.domain;

import java.util.List;

public class LottoTickets {

    private final List<Lotto> LottoTickets;

    private LottoTickets(List<Lotto> lottoTickets) {
        LottoTickets = List.copyOf(lottoTickets);
    }

    public static LottoTickets from(List<Lotto> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }
}
