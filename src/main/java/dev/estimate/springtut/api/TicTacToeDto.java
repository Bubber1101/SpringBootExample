package dev.estimate.springtut.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TicTacToeDto {
    private String message;
    private int id;
    private char[][] board;
}
