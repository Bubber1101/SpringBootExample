package dev.estimate.springtut.api;

import dev.estimate.springtut.domain.SessionStatus;
import dev.estimate.springtut.domain.TicTacToeSession;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TicTacToeFactory {

    public TicTacToeSession createNew() {
        return TicTacToeSession.builder()
                .id(generateId())
                .board(new int[][]{{9,9,9},{9,9,9},{9,9,9}})
                .status(SessionStatus.IN_PROGRESS)
                .turn(0)
                .build();
    }

    private int generateId() {
        return Math.abs(new Random().nextInt());
    }
}
