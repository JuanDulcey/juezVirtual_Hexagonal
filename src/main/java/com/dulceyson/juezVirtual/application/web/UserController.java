package com.dulceyson.juezVirtual.application.web;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import com.dulceyson.juezVirtual.application.services.UsuarioService;
import com.dulceyson.juezVirtual.infrastructure.entities.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;


@Controller
public class UserController {

    @Autowired
    private UsuarioService usuarioService; // Servicio para manejar la lógica de usuario

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                Model model) {
        System.out.println("Mostrando login. error param: " + error);
        if (error != null) {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
        }
        return "auth/login";
    }


    @GetMapping("/auth/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new UsuarioEntity()); // Asegura que está presente
        return "auth/register";
    }


    @PostMapping("/auth/register")
    public String registerUser(@Valid UsuarioEntity usuario, String confirmPassword,
                               BindingResult bindingResult, Model model) {

        // Validación de contraseñas
        if (!usuario.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.usuario", "Las contraseñas no coinciden");
        }

        // Si hay errores de validación, regresamos al formulario
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // Asignar el rol por defecto ROLE_USER
        usuario.setRoles(Set.of("ROLE_USER"));

        // Convertir UsuarioEntity a Usuario (modelo de dominio)
        Usuario usuarioDomain = usuario.toDomainModel();

        // Llamada al servicio para guardar el usuario
        try {
            usuarioService.createUsuario(usuarioDomain);
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al registrar el usuario.");
            return "auth/register";
        }

        // Redirigir al login después de registrar
        return "redirect:/auth/login";
    }

}
