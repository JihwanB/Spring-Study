package com.example.encore_spring_pjt;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

// DI - Autowired, Resource
@SpringBootTest
public class BoardServiceTests {


    // @Autowired
    @Resource(name = "board")
    private BoardService service;

    @Test
    public void serviceSaveTest() {
        System.out.println("debug Test -> serviceSaveTest() ");

        /* 빌더를 사용하지 않는 레거시(legacy) 방식
        BoardRequest request = new BoardRequest();
        request.setTitle("service 수업");
        request.setContent("service를 이용한 mapper 연결");
        request.setWriter("encore");
        request.setNoticeYn(true);
        request.setSecretYn(true);
         */

        BoardRequest request = BoardRequest.builder()
                .title("service")
                .content("mapper")
                .writer("encore")
                .noticeYn(true)
                .secretYn(true)
                .build();

        Integer idx = service.saveBoard(request);
        System.out.println("입력한 데이터의 키 값을 출력 : " + idx);
    }

    @Test
    public void serviceFindTest() {
        System.out.println("debug Test -> serviceFindTest() ");

        BoardRequest request = BoardRequest.builder()
                .idx(1)
                .build();

        Optional<BoardResponse> response = service.findBoard(request);
        System.out.println("findBoard result : " + response);
    }

    @Test
    public void serviceUpdateTest() {
        System.out.println("debug Test -> serviceUpdateTest() ");

        BoardRequest request = BoardRequest.builder()
                .idx(1)
                .title("Title")
                .content("Content")
                .writer("Writer")
                .build();

        Integer idx = service.updateBoard(request);
        System.out.println("updateBoard result : " + idx + "번 레코드를 수정하였습니다.");
    }

    @Test
    public void serviceDeleteTest() {
        System.out.println("debug Test -> serviceDeleteTest() ");

        BoardRequest request = BoardRequest.builder()
                .idx(4)
                .build();

        Integer idx = service.deleteBoard(request);
        System.out.println("deleteBoard result : " + idx + "번 레코드가 삭제되었습니다.");
    }

    @Test
    public void serviceListTest(PageDTO params) {
        System.out.println("debug Test -> serviceListTest() ");

        PageResponse<BoardResponse> responses = service.listBoard(params);

    }

    @Test
    public void serviceCntTest(PageDTO params) {
        System.out.println("debug Test -> serviceCntTest() ");

        Integer cnt = service.cntBoard(params);

        System.out.println("cntBoard result" + cnt + "건의 삭제되지 않은 레코드가 있습니다.");
    }
}
