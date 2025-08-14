package br.ufjf.cookingup.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufjf.cookingup.exception.SenhaInvalidaException;
import br.ufjf.cookingup.model.entity.Usuario;
import br.ufjf.cookingup.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        //String[] roles = usuario.getTipo()
                //? new String[]{"ADMIN", "USER"}
                //: new String[]{"USER"};
        String[] roles;
        switch (usuario.getTipo()) {
	        case "MEMBRO":
	        	roles = new String[]{"MEMBRO"};
	            break; 
	        case "CHEF":
	        	roles = new String[]{"CHEF"};
	            break; 
	        case "ADMIN":
	        	roles = new String[]{"MEMBRO", "ADMIN"};
	            break;
        }

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    	}
}
