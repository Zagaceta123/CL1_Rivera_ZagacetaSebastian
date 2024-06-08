package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.bd.Usuario;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.dto.UsuarioDto;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/registrar")
    public String registro(){
        return "/auth/frmregistrar";
    }

    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "/auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication, Model model){
        String username = authentication.getName();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNomusuario(username);
        model.addAttribute("usuario", usuarioDto);
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model){
        String username = authentication.getName();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNomusuario(username);
        model.addAttribute("usuario", usuarioDto);
        return "auth/home";
    }
    @GetMapping("/cambiar-contrasena")
    public String mostrarCambiarContraseña(Model model) {
        return "auth/frmcontrasena";
    }

    @PostMapping("/cambiar-contrasena")
    public String cambiarContraseña(@RequestParam("nuevaContraseña") String newPassword,
                                  Authentication authentication,
                                  Redigit arectAttributes redirectAttributes) {
        String username = authentication.getName();
        try {
            usuarioService.CambiarPassword(username, newPassword);
            redirectAttributes.addFlashAttribute("success", "Contraseña cambiada exitosamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/cambiar-contrasena";
    }

}
