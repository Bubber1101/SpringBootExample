package dev.estimate.springtut.domain;

import java.util.List;
import java.util.Optional;

public interface TicTacToeSessionRepository {

    Optional<TicTacToeSession> getSession(int id);

    TicTacToeSession addSession(TicTacToeSession ticTacToeSession);

    List<TicTacToeSession> getSessionByStatus(SessionStatus status);

}
