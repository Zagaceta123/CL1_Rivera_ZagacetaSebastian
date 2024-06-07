package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Service;

import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.bd.Usuario;

public interface IUsuarioService {
    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
}
