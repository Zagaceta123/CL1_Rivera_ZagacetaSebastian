package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/register")
    public String registro(){
        return "/auth/frmregistrar";
    }

    @PostMapping("/save-user")
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
}
