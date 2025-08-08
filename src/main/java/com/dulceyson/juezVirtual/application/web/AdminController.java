package com.dulceyson.juezVirtual.application.web;

import com.dulceyson.juezVirtual.application.services.UsuarioService;
import com.dulceyson.juezVirtual.domain.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService; // Servicio para manejar la lógica de usuario

    // Mostrar la lista de usuarios en la vista de administración
    @GetMapping("/admin/users")
    public String showUserManagement(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "admin/users"; // Apunta al archivo HTML en templates/admin/users.html
    }

    // Actualizar rol de usuario
    @PostMapping("/admin/updateRole")
    public String updateUserRole(@RequestParam Long userId, @RequestParam String role) {
        usuarioService.updateUserRole(userId, role);
        return "redirect:/admin/users";
    }

    // Eliminar usuario con validación
    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam Long userId) {
        usuarioService.deleteUsuario(userId);
        return "redirect:/admin/users";
    }

    // **Actualizar datos del usuario (incluye estado activo)**
    @PostMapping("/admin/updateUser")
    public String updateUser(@RequestParam Long userId, @RequestParam String username,
                             @RequestParam String email, @RequestParam String affiliation,
                             @RequestParam String mail, @RequestParam String policy,
                             @RequestParam String url, @RequestParam String country,
                             @RequestParam boolean activo) {  // Estado activo agregado

        usuarioService.updateUser(userId, username, email, affiliation, mail, policy, url, country, activo);
        return "redirect:/admin/users";
    }
}