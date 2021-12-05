package br.com.online.livraria.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.online.livraria.modelo.Usuario;
import br.com.online.livraria.repository.UsuarioRepository;


public class VerificacaoTokenFilter extends OncePerRequestFilter{
	
	private UsuarioRepository usuarioRepository;
	private TokenService tokenService;

	public VerificacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if(token == null || token.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		token = token.replace("Bearer", "");
		
		boolean tokenValido = tokenService.isValido(token);
		
		if(tokenValido) {
			Long idUsuario = tokenService.extrairIdUsuario(token);
			Usuario logado = usuarioRepository.carregarPorIdComPerfis(idUsuario);
			
			Authentication authetication = new UsernamePasswordAuthenticationToken(logado, null, logado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authetication);
		}
		filterChain.doFilter(request, response);
	}
}
 