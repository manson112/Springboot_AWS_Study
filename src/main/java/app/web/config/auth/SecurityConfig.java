package app.web.config.auth;

import app.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        .and()
            .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**") // 권한 관리 대상을 지정하는 옵션 URL, HTTP 메소드 별로 관리 가능
                    .permitAll() // 위의 url들에 대해 전체 열람 권한
                .antMatchers("/api/v1/**")
                    .hasRole(Role.USER.name()) // USER Role을 가진 사용자만 접근 가능
                .anyRequest()// 설정된 값 외 나머지
                    .authenticated() // 인증된 사용자만 가능
        .and()
            .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                .logoutSuccessUrl("/") // 로그아웃 시 redirect 할 URL
        .and()
            .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                    .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체
    }
}
