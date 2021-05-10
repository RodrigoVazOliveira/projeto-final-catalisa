package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
}
