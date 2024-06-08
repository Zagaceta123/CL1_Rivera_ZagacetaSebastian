package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.bd.Rol;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.bd.Usuario;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Repository.RolRepository;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
    public void CambiarPassword(String username, String newPassword) {
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        if (usuario != null) {
            if (!validarPassword(newPassword)) {
                throw new IllegalArgumentException("La contraseña no cumple con los parametros establecidos.");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(newPassword);
            usuario.setPassword(password);
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado: " + username);
        }
    }

    private boolean validarPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
}
