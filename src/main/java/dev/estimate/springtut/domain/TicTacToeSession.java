package dev.estimate.springtut.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class TicTacToeSession {

    private final int id;
    private int[][] board;
    private int turn;
    private SessionStatus status;


}
