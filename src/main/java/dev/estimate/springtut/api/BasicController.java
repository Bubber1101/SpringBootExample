package dev.estimate.springtut.api;

import dev.estimate.springtut.boundry.Sessions;
import dev.estimate.springtut.domain.SessionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TicTacToe")
@RequiredArgsConstructor
public class BasicController {
    private final Sessions sessions;

    @GetMapping("/activeSessions")
    public List<TicTacToeDto> getActiveSessions() {
        return sessions.getSessions(SessionStatus.IN_PROGRESS);
    }

    @GetMapping("/OverSessions")
    public List<TicTacToeDto> getOverSessions() {
        return sessions.getSessions(SessionStatus.OVER);
    }

    @GetMapping()
    public TicTacToeDto getSession(@RequestParam("id") int id) {
        return sessions.getSession(id);
    }

    @PostMapping("/newSession")
    public TicTacToeDto startSession() {
        return sessions.startSession();
    }

    @PostMapping()
    public TicTacToeDto nextMove(@RequestParam("id") int id, @RequestParam("x") int x, @RequestParam("y") int y) {
        return sessions.nextMove(id,x,y);
    }

}
