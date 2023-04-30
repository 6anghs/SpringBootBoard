package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") // localhost:8090/board/write 로 접속하면
    public String boardWriteForm() {
        return "boardwrite"; // boardwrite.html 파일을 보여줌
    }

    @PostMapping("/board/writepro") // 주어진 URI 표현식과 일치하는 HTTP POST 요청을 처리
    public String boardWritePro(Board board) { // PostMapping 을 통해 데이터가 넘어옴
        boardService.write(board);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) { // 데이터를 담아서 html 페이지로 보내줌
        model.addAttribute("list", boardService.boardList()); // 반환된 리스트를 list 라는 이름으로 받아서 넘김
        return "boardList";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1 입력 시
    public String boardView(Model model, Integer id) { // id 값은 1
        model.addAttribute("board", boardService.boardView(id)); // 게시글 1 불러옴
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list"; // list 페이지로 이동
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {
        Board boardTemp = boardService.boardView(id); // 기존에 있던 글 불러옴
        boardTemp.setTitle(board.getTitle()); // 기존 제목에 덮어쓰기
        boardTemp.setContent(board.getContent()); // 기존 내용에 덮어쓰기

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }
}
