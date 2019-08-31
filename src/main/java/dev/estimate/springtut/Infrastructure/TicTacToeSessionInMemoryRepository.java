package dev.estimate.springtut.Infrastructure;

import dev.estimate.springtut.domain.SessionStatus;
import dev.estimate.springtut.domain.TicTacToeSession;
import dev.estimate.springtut.domain.TicTacToeSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicTacToeSessionInMemoryRepository implements TicTacToeSessionRepository {

    private final ConcurrentHashMap<Integer, TicTacToeSession> sessionRepository = new ConcurrentHashMap<>();

    public Optional<TicTacToeSession> getSession(int id) {
        return Optional.ofNullable(sessionRepository.get(id));
    }

    public TicTacToeSession addSession(TicTacToeSession ticTacToeSession) {
        sessionRepository.put(ticTacToeSession.getId(), ticTacToeSession);
        return ticTacToeSession;
    }

    @Override
    public List<TicTacToeSession> getSessionByStatus(SessionStatus status) {
        return sessionRepository.values().stream().filter(session -> session.getStatus() == status).collect(Collectors.toList());
    }

}
