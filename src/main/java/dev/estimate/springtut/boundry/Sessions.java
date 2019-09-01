package dev.estimate.springtut.boundry;

import dev.estimate.springtut.api.TicTacToeDto;
import dev.estimate.springtut.api.TicTacToeDtoFactory;
import dev.estimate.springtut.api.TicTacToeFactory;
import dev.estimate.springtut.domain.SessionNotFoundException;
import dev.estimate.springtut.domain.SessionStatus;
import dev.estimate.springtut.domain.TicTacToeSession;
import dev.estimate.springtut.domain.TicTacToeSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class Sessions {

    TicTacToeSessionRepository sessionRepository;
    TicTacToeDtoFactory ticTacToeDtoFactory;
    TicTacToeFactory ticTacToeFactory;

    public List<TicTacToeDto> getSessions(SessionStatus status) {
        List<TicTacToeDto> list = new ArrayList<>();
        sessionRepository.getSessionByStatus(status).forEach(session -> list.add(ticTacToeDtoFactory.create(session)));
        return list;

    }

    public TicTacToeDto getSession(int id) {
        return ticTacToeDtoFactory.create(
                sessionRepository
                        .getSession(id)
                        .orElseThrow(SessionNotFoundException::new));
    }

    public TicTacToeDto startSession() {
        TicTacToeSession session = ticTacToeFactory.createNew();
        makeMove(session);
        return ticTacToeDtoFactory.create(sessionRepository.addSession(session));
    }

    public TicTacToeDto nextMove(int id, int x, int y) {
        TicTacToeSession session = sessionRepository.getSession(id).orElseThrow(SessionNotFoundException::new);
        if (session.getStatus() != SessionStatus.OVER) {
            if (session.getBoard()[y][x] == 9) {
                session.getBoard()[y][x] = 1;
                if (checkBoard(session, true)) {
                    session.setStatus(SessionStatus.OVER);
                    TicTacToeDto outcomeDto = ticTacToeDtoFactory.create(session);
                    outcomeDto.setMessage("YOU WON!");
                    return outcomeDto;
                }
                makeMove(session);

                if (checkBoard(session, false)) {
                    session.setStatus(SessionStatus.OVER);
                    TicTacToeDto outcomeDto = ticTacToeDtoFactory.create(session);
                    outcomeDto.setMessage("YOU LOST!");
                    return outcomeDto;
                }

                if (boardIsFull(session)){
                    session.setStatus(SessionStatus.OVER);
                    TicTacToeDto outcomeDto = ticTacToeDtoFactory.create(session);
                    outcomeDto.setMessage("DRAW!");
                    return outcomeDto;
                }
                return ticTacToeDtoFactory.create(session);
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Spot already taken");
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This game has already ended");
        }
    }


    private void makeMove(TicTacToeSession session) {
        int x = new Random().nextInt(3);
        try {
            Thread.sleep(67);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int y = new Random().nextInt(3);

        if (session.getBoard()[x][y] == 9) {
            session.getBoard()[x][y] = 2;
            session.setTurn(session.getTurn() + 1);
        } else makeMove(session);

    }

    private boolean checkBoard(TicTacToeSession session, boolean opponent) {
        int desiredSum = 6;
        if (opponent) desiredSum = 3;
        int[][] board = session.getBoard();
        for (int i = 0; i < 3; i++) {
            if (board[i][0] + board[i][1] + board[i][2] == desiredSum) return true;
            if (board[0][i] + board[1][i] + board[2][i] == desiredSum) return true;
        }
        if (board[0][0] + board[1][1] + board[2][2] == desiredSum) return true;
        if (board[0][2] + board[1][1] + board[2][0] == desiredSum) return true;
        return false;
    }

    private boolean boardIsFull(TicTacToeSession session){
        int[][] board = session.getBoard();
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += board[i][j];
            }

        }
        return sum == 14;
    }

}
