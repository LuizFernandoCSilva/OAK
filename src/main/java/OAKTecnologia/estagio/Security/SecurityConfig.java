package OAKTecnologia.estagio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // Rotas públicas para o Swagger
    private static final String[] SWAGGER_LIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilitar o CSRF (recomendado para APIs REST, mas avalie conforme o caso)
            .csrf(csrf -> csrf.disable())
            // Configuração de autorizações
            .authorizeHttpRequests(auth -> auth
                // Permitir acesso público às rotas do Swagger
                .requestMatchers(SWAGGER_LIST).permitAll()
                .requestMatchers("/error").permitAll()
                // Permitir acesso público a outras rotas específicas
                .requestMatchers("/cadastro/produto", "/find").permitAll()
                // Exigir autenticação para qualquer outra requisição
                .anyRequest().authenticated()
            );
        // Construir e retornar a configuração
        return http.build();
    }
}
