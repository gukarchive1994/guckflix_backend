package guckflix.backend.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class RottoServiceTest {

    MemberService memberService = new MemberService();

    @Test
    public void test(){

        String name = memberService.findById(1);
        Assertions.assertThat(name).isEqualTo("테스트");
    }

}

class MemberService {

    MemberDao dao = new MemberDao();

    public String findById(Long id){
        return dao.findById(id);
    }

    public void

}

class MemberDao {
    Map<Integer, String> map = new HashMap<>();

    public String findById(Long id){
        return map.get(id);
    }
}

