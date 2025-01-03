package com.zambetti;

import com.zambetti.service.CustomUserDetailsService;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;


public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = extractJwtFromRequest(exchange);

        if (token != null) {
            String username = jwtUtil.extractUsername(token);
            Long userId = jwtUtil.extractUserId(token);
            String role = jwtUtil.extractRole(token);

            if (jwtUtil.validateToken(token, customUserDetailsService.loadUserByUsername(username))) {
               /* HttpHeaders headers = exchange.getRequest().getHeaders();
                headers.set("X-Username", username);  // Aggiungi username
                headers.set("X-User-Id", userId.toString());  // Aggiungi userId
                headers.set("X-Role", role);
*/
                JwtAuthenticationToken authentication = new JwtAuthenticationToken(username, userId, role);
                Context context = ReactiveSecurityContextHolder.withAuthentication(authentication);
                return chain.filter(exchange).contextWrite(context);

            }
        }

        return chain.filter(exchange);
    }

    private String extractJwtFromRequest(ServerWebExchange exchange) {
        String bearerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}