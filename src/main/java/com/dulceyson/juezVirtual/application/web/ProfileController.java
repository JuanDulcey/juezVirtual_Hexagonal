package com.dulceyson.juezVirtual.application.web;

import com.dulceyson.juezVirtual.application.services.UsuarioService;
import com.dulceyson.juezVirtual.domain.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UsuarioService usuarioService;

    private final PasswordEncoder passwordEncoder;

    public ProfileController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showPerfilPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        String username = userDetails.getUsername();
        Usuario usuario = usuarioService.getUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        model.addAttribute("title", "Perfil");
        model.addAttribute("usuario", usuario);

        return "profile/profile";
    }

    @PostMapping("/profile")
    public String updatePerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String email,
            @RequestParam(required = false) String password,
            Model model) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        String username = userDetails.getUsername();
        Usuario usuario = usuarioService.getUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        usuario.setEmail(email);

        if (password != null && !password.isBlank()) {
            usuario.setPassword(passwordEncoder.encode(password));
        }

        usuarioService.updateUsuario(usuario.getId(), usuario);

        model.addAttribute("title", "Perfil");
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", "Perfil actualizado correctamente.");

        return "profile/profile";
    }
}