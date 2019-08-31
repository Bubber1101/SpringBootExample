package dev.estimate.springtut.api;

import dev.estimate.springtut.domain.TicTacToeSession;
import org.springframework.stereotype.Component;

@Component
public class TicTacToeDtoFactory {

    public TicTacToeDto create(TicTacToeSession session){
        return TicTacToeDto.builder()
                .id(session.getId())
                .board(tranlateBoard(session.getBoard()))
                .build();
    }

    private char[][] tranlateBoard(int[][] board){
        char[][] boardDto = new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};

        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j <board[i].length ; j++) {
                if(board[i][j] == 1 ) boardDto[i][j] = 'X';
                if(board[i][j] == 2 ) boardDto[i][j] = 'O';
            }
        }

        return boardDto;
    }
}
