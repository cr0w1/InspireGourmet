package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.email.MailerRestaurante;
import com.example.demo.models.Categoria;
import com.example.demo.models.Imagem;
import com.example.demo.models.Oferta;
import com.example.demo.models.Restaurante;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ImagemService;
import com.example.demo.services.OfertaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.util.Functions;

@Controller
public class RestauranteController {

	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private OfertaService serviceOferta;
	
	@Autowired 
	private ImagemService serviceImagem;
	
	@Autowired
	private MailerRestaurante mail;
	
	@GetMapping("/restaurante/homeRest/{hash}")
	public String showHomeRes(@PathVariable("hash")String hash,Model model) {
		
		Restaurante restaurante = serviceRestaurante.findByHashId(hash);
     	
     	Oferta oferta = serviceOferta.get(restaurante.getIdRestaurante());
		model.addAttribute("oferta", oferta);
		
		return "/restaurante/homeRest";
	}

	//cad Restaurante
	@GetMapping("/restaurante")
	public String showRestaurante(Restaurante restaurante,Model model,HttpSession session) {
		model.addAttribute("loginRestaurante", restaurante);
		
		return "/admin/restaurante";
	}
	
	@PostMapping("/restauranteCad")
	public String showLRestaurante(Restaurante restaurante ,Model model,@RequestParam(name = "cnpj")String cnpj,@RequestParam(name = "noCNPJ") String noCNPJ) {
		model.addAttribute("restaurante", restaurante);
		
		if(noCNPJ != "0") {
			model.addAttribute("cnpj", cnpj);
		}
		
		System.out.println(noCNPJ);
		
		List<Categoria> categorias = serviceCategoria.listAll();
		model.addAttribute("categorias", categorias);
		
		return "/admin/restauranteCad";
	}
	
	@PostMapping("/savarRestaurante")
	public String saveRestaurante(Restaurante restaurante,@RequestParam("imgRest") MultipartFile file,@RequestParam(name = "cep") String cep,@RequestParam(name = "bairro") String bairro,
			@RequestParam(name = "cidade") String cidade,@RequestParam(name = "numero") String numero,@RequestParam(name = "uf") String uf,RedirectAttributes ra) {
		
		//criptografando a senha
		restaurante.setSenha(Functions.getSHA256(restaurante.getSenha()));
		
		restaurante.setAtivo(0);
		restaurante.setPrioridade(2);
		restaurante.setSaltera(0);
		
		serviceRestaurante.save(restaurante);
		
		Imagem imagem = new Imagem();
		imagem.setRestaurante(restaurante);
		try {
			imagem.setImagem(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		serviceImagem.save(imagem);
		
		
		mail.enviar(restaurante);
		
		//save a new Oferta
		Oferta oferta = new Oferta();
		oferta.setRestaurante(restaurante);
		serviceOferta.save(oferta);
		
		ra.addFlashAttribute("mensagemErro", "1");
		return "redirect:/restaurante";
		
		
	}
	
	//LIST OF IMAGE
	@GetMapping("/imagemR/{idRest}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("idRest") Integer idRest) {
		
		Imagem imagem = serviceImagem.getImagemRest(idRest);
		return imagem.getImagem();
	}

	@PostMapping("/verificationCNPJ")
	@ResponseBody
	public String valideCnpj(@RequestParam(name = "cnpj") String cnpj) {

		Boolean cpfChecado = serviceRestaurante.verificaCPF(cnpj) == null;
		
		return cpfChecado.toString();
		
	}
	
	@PostMapping("/verificationEmailRest")
	@ResponseBody
	public String valideEmailRest(@RequestParam(name = "email") String email) {

		Boolean emailChecado = serviceRestaurante.verificaEmailRest(email) == null; 
		
		return emailChecado.toString();
		
		
	}
	
	
	@GetMapping("/homeRest/{idRes}")
	public String showHomeRest(@PathVariable("idRes")Integer idRest,Model model) {
				
		Oferta oferta = serviceOferta.get(idRest);
		model.addAttribute("oferta", oferta);

		return "/restaurante/homeRest";
	}
	
	@GetMapping("/configuracoes/{idRest}")
	public String showConf(@PathVariable("idRest")Integer idRest,Model model) {
		
		Restaurante restaurante = serviceRestaurante.get(idRest);	
		model.addAttribute("restaurante", restaurante);
		
		return "/restaurante/configuracoes";
	}
	
	@PostMapping("/editeRestaurante")
	public String showConf(Restaurante restaurante,Model model) {
		
		Restaurante restaurante1 = serviceRestaurante.findByHashId(restaurante.getHashId());
		
		serviceRestaurante.save(restaurante1);
		
		return "/restaurante/configuracoes";
	}
	
	@GetMapping("/deleteRest/{idRest}")
	public String delete(@PathVariable("idRest")Integer idRest,Model model) {

		serviceOferta.buscarPorIdRest(idRest);
		serviceRestaurante.delete(idRest);
		
		return "redirect:/homeAdm";
	}	
	
	//PESQUISA
	@PostMapping("/pesquisar")
	public ModelAndView pesquisar(@RequestParam(name = "pesquisa")String pesquisa,Model model) {
		ModelAndView modelAndView= new ModelAndView("usuario/home");
		
		System.out.println(pesquisa);
		if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("japonesa")) {
			
			if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("coreana")) {
				
				if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("tailandesa")) {
					
					if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("indiana")) {
						
						if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("pizza")) {
							
							if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("italiana")) {
								
								if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("regional")) {
									if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("")) {
										
											
											List<Restaurante> listRestaurante = serviceRestaurante.pesquisa(pesquisa);
											System.out.println(listRestaurante);
											if(listRestaurante.isEmpty()) {
												model.addAttribute("nada", "Nenhum Resultado Encontrado!!");
												return modelAndView;
											}else {
												modelAndView.addObject("restaurantes", listRestaurante);
												System.out.println("qqq");
												return modelAndView;
												
											}
										
									}else {
										List<Restaurante> listRestaurante = serviceRestaurante.listAll();
										modelAndView.addObject("restaurantes", listRestaurante);
										
										return modelAndView;
									}
									
								}else {
									pesquisa = "Regional";
									
									List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
									modelAndView.addObject("restaurantes", listRestaurante);
									
									return modelAndView;
								}
							}else {
								pesquisa = "Italiana";
								
								List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
								modelAndView.addObject("restaurantes", listRestaurante);
								
								return modelAndView;
							}
						}else {
							pesquisa = "Pizza";
							
							List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
							modelAndView.addObject("restaurantes", listRestaurante);
							
							return modelAndView;
						}
					}else {
						pesquisa = "Indiana";
						
						List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
						modelAndView.addObject("restaurantes", listRestaurante);
						
						return modelAndView;
					}
				}else {
					pesquisa = "Tailandesa";
					
					List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
					modelAndView.addObject("restaurantes", listRestaurante);
					
					return modelAndView;
				}
			}else {
				pesquisa = "Coreana";
				
				List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
				modelAndView.addObject("restaurantes", listRestaurante);
				
				return modelAndView;
			}
		}else {
			pesquisa = "Japonesa";
			
			List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
			modelAndView.addObject("restaurantes", listRestaurante);
			
			return modelAndView;
		}
		
		
	}
	@PostMapping("/pesquisa")
	public ModelAndView pesquisa(@RequestParam(name = "pesquisa")String pesquisa,Model model) {
		ModelAndView modelAndView= new ModelAndView("restaurantes");
		
		System.out.println(pesquisa);
		if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Japonesa")) {
			
			if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Coreana")) {
				
				if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Tailandesa")) {
					
					if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Indiana")) {
						
						if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Pizza")) {
							
							if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Italiana")) {
								
								if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("Regional")) {
									if(pesquisa.trim().equals(pesquisa) != pesquisa.trim().equals("")) {
										
											
											List<Restaurante> listRestaurante = serviceRestaurante.pesquisa(pesquisa);
											System.out.println(listRestaurante);
											if(listRestaurante.isEmpty()) {
												model.addAttribute("nada", "Nenhum Resultado Encontrado!!");
												return modelAndView;
											}else {
												modelAndView.addObject("restaurantes", listRestaurante);
												System.out.println("qqq");
												return modelAndView;
												
											}
										
									}else {
										List<Restaurante> listRestaurante = serviceRestaurante.listAll();
										modelAndView.addObject("restaurantes", listRestaurante);
										
										return modelAndView;
									}
									
								}else {
									pesquisa = "Regional";
									
									List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
									modelAndView.addObject("restaurantes", listRestaurante);
									
									return modelAndView;
								}
							}else {
								pesquisa = "Italiana";
								
								List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
								modelAndView.addObject("restaurantes", listRestaurante);
								
								return modelAndView;
							}
						}else {
							pesquisa = "Pizza";
							
							List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
							modelAndView.addObject("restaurantes", listRestaurante);
							
							return modelAndView;
						}
					}else {
						pesquisa = "Indiana";
						
						List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
						modelAndView.addObject("restaurantes", listRestaurante);
						
						return modelAndView;
					}
				}else {
					pesquisa = "Tailandesa";
					
					List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
					modelAndView.addObject("restaurantes", listRestaurante);
					
					return modelAndView;
				}
			}else {
				pesquisa = "Coreana";
				
				List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
				modelAndView.addObject("restaurantes", listRestaurante);
				
				return modelAndView;
			}
		}else {
			pesquisa = "Japonesa";
			
			List<Restaurante> listRestaurante = serviceRestaurante.listRestauranteCate(pesquisa);
			modelAndView.addObject("restaurantes", listRestaurante);
			
			return modelAndView;
		}
		
		
	}
	//FIM DA PESQUISA	S
	
	//LEITOR DE QR CODE
	@GetMapping("/scanner/qr")
	public String showSacan() {
		
		
		return "restaurante/leitorQR";
	}
	
	@GetMapping("/restaurante/qr")
	public String showqr(Model model) {
		
		
		return "/restaurante/qr";
	}
	
	
	//REDEFINIÇÃO DE SENHA
	@GetMapping("/redefini/{hash}")
	public String showRedefinir(@PathVariable("hash")String hash,Model model) {
		
		Restaurante restaurante = serviceRestaurante.findByHashId(hash);
		model.addAttribute("redefinir", restaurante);
		
		return "/restaurante/redefinir";
	}
	
	@PostMapping("/redefinirSenhaRest")
	public String redefinirR(@RequestParam(name = "hashId")String hash,@RequestParam(name = "confSenha")String senha,RedirectAttributes ra) {

		serviceRestaurante.redefinirSenha(hash,senha);
		ra.addFlashAttribute("mensagemErro", "5");
		
		Restaurante restaurante = serviceRestaurante.findByHashId(hash);
     	
     	Oferta oferta = serviceOferta.get(restaurante.getIdRestaurante());
		ra.addFlashAttribute("oferta", oferta);
		
		return "redirect:/restaurante/homeRest/"+restaurante.getHashId();
	}
	
	@GetMapping("/senhaR/{hash}")
	public String showRedeR(@PathVariable("hash")String hash,Model model) {
		
		Restaurante restaurante = serviceRestaurante.findByHashId(hash);
		model.addAttribute("redefinir", restaurante);
		
		return "/restaurante/senha";
	}

}
