package de.codecentric.keycloakspring;

import org.keycloak.KeycloakPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping
    public String home()
    {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @RequestMapping("/user")
    public String user(Model model, KeycloakPrincipal principal) {
        model.addAttribute("email", principal.getKeycloakSecurityContext().getIdToken().getEmail());
        model.addAttribute("name", principal.getKeycloakSecurityContext().getIdToken().getName());
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(Model model, KeycloakPrincipal principal) {
        model.addAttribute("identityToken", principal.getKeycloakSecurityContext().getIdTokenString());
        model.addAttribute("accessToken", principal.getKeycloakSecurityContext().getTokenString());
        return "admin";
    }
}
