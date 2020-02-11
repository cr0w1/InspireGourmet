package com.example.demo.controllers;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Oferta;
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.models.Voucher;
import com.example.demo.services.OfertaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.services.UsuarioService;
import com.example.demo.services.VoucherService;
import com.example.demo.util.QRGeneration;

@Controller
public class VouncherController {

	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	private OfertaService serviceOferta;
	
	@GetMapping("/gerar/voucher/{idUser}/{idOferta}")
	public String salvarVoucher(@PathVariable("idUser")Usuario idUser, @PathVariable("idOferta")Oferta idOferta,RedirectAttributes ra) {
		
		Usuario usuario = serviceUser.get(idUser.getIdUsuario());
		Oferta oferta = serviceOferta.pegarPorId(idOferta.getIdOferta());
		Restaurante restaurante = serviceRestaurante.get(oferta.getRestaurante().getIdRestaurante());
		
		
		Voucher voucher = new Voucher();
		voucher.setIdOferta(idOferta);
		voucher.setIdUsuario(idUser);
		voucher.setNomeUser(usuario.getNomeUsuario());
		voucher.setNomeRest(restaurante.getNomeRestaurante());
		
		voucherService.save(voucher);
		
		ra.addFlashAttribute("mensagemErro", "1");
		return "redirect:/home";
	}
	
	
	@GetMapping("/Meus/Vouchers/{hash}")
	public String showVouchers(@PathVariable("hash")String hash, Model model) {
		
		Usuario usuario = serviceUser.buscarPeloHash(hash);
		
		if(usuario != null) {
			
			List<Voucher> vouchers = voucherService.buscarPorIdUser(usuario.getIdUsuario());
			model.addAttribute("vouchers", vouchers);
		}
		
		return "/usuario/vouchers";
	}
	
	@GetMapping("/qrcode/{hash}")
	public void qrcode(@PathVariable("hash")String hash,HttpServletResponse response) throws Exception{
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(QRGeneration.getQRCodeImage(hash, 200, 200));
		outputStream.flush();
		outputStream.close();
	}
	
	@PostMapping("/validar")
	public String shiw(@RequestParam(name = "codigo")String codi,RedirectAttributes ra) {
		
		Boolean voucherCheck = voucherService.buscarPorHASH(codi) == null;
		if(voucherCheck != false) {
			ra.addFlashAttribute("mensagemErro", "1");
			return "redirect:/scanner/qr";
		}else {
			
			Voucher voucher = voucherService.buscarPorHASH1(codi);
			voucher.setStatus(0);
			voucherService.save(voucher);
			
			ra.addFlashAttribute("mensagemErro", "2");
			return "redirect:/scanner/qr";
		}
		
		
	}
	
	
	
}
