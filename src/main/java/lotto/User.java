package lotto;

import utils.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

    private static final int ONE_LOTTO = 1000;
    private List<Lotto> lottos;
    private final int money;

    public User(int money) {
        validate(money);
        this.money = money;
    }

    public void purchaseLotto(LottoSeller lottoSeller) {
        this.lottos = lottoSeller.sellLotto(money);
        Printer.buyLotto(lottos.size());
    }

    public void printLotto() {
        for (Lotto lotto : this.lottos) {
            lotto.printNumbers();
        }
    }

    public HashMap<Rank,Integer> statisticRank(WinningNumber winningNumber) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            Rank rank = lotto.checkWinning(winningNumber);
            ranks.add(rank);
        }

        HashMap<Rank, Integer> rankAggregation = new HashMap<>();

        for (Rank rank : ranks) {
            if (rank.equals(Rank.NO_PRIZE)) {
                continue;
            }

            rankAggregation.put(rank, rankAggregation.getOrDefault(rank, 0) + 1);
        }

        return rankAggregation;
    }

    private void validate(int money) {
        if (money % ONE_LOTTO != 0) {
            throw new IllegalArgumentException(Error.ERROR_MSG_UNIT.getMessage());
        }
    }
}
