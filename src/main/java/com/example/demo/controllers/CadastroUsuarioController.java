package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.email.Mailer;
import com.example.demo.models.Imagem;
import com.example.demo.models.Usuario;
import com.example.demo.services.ImagemService;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;

@Controller
public class CadastroUsuarioController {
	
	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired 
	private ImagemService serviceImagem;
	
	@Autowired
	private Mailer mail;
	
	@RequestMapping("/continuar")
	public String showCadUser(@RequestParam(name = "cpf")String cpf,Model model, Usuario usuario) {
		model.addAttribute("usuario", usuario);		
		model.addAttribute("cpf", cpf);
		
		return "cadastro";
	}
	
	@PostMapping("/salvarUsuario" )
	public String cadUsu(Usuario usuario ,@RequestParam("imgUser") MultipartFile file, Model model,RedirectAttributes ra) {

		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		usuario.setAtivo(0);
		
		Date dataCriacao = new Date();
		usuario.setDataDeCriacao(dataCriacao);
		
		usuario.setPrioridade(1);
		usuario.setSaltera(0);
		
		serviceUser.save(usuario);
		
		Imagem imagem = new Imagem();
		imagem.setUsuario(usuario);
		try {
			if(!file.isEmpty()) {
				byte[] arquivo = Files.readAllBytes( Paths.get("/assets/images/images.jpg") );
				imagem.setImagem(arquivo);
			}else {
				imagem.setImagem(file.getBytes());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		serviceImagem.save(imagem);
		
		
		mail.enviar(usuario);
		
		ra.addFlashAttribute("mensagemErro", "1");
		
		return "redirect:/login";		
		
	}
	
	//LIST OF IMAGE
	@GetMapping("/imagem/{idUser}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("idUser") Integer idUser) {
		
		Imagem imagem = serviceImagem.getImagem(idUser);
		return imagem.getImagem();
	}

	@PostMapping("/verificationCPF")
	@ResponseBody
	public String valideCpf(@RequestParam(name = "cpf") String cpf) {

		Boolean cpfChecado = serviceUser.verificaCPF(cpf) == null;
		
		return cpfChecado.toString();
		
	}
	
	@PostMapping("/verificationEmail")
	@ResponseBody
	public String valideEmail(@RequestParam(name = "email") String email) {
		
		Boolean emailChecado = serviceUser.verificaEmail(email) == null; 
		
		return emailChecado.toString();
		
		
	}
}
