package app.web.config.auth.dto;

import app.web.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
 * 세션에 User Class를 직접 직렬화하여 저장하면 User Entity가 다른 Entity들과 관계를 갖게 되었을 경우,
 * 하위 Entity들도 모두 직렬화 되는 성능 이슈, Side Effect의 발생 가능성이 존재한다.
 */

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
