package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class TaxControllerImpl {
	
	SalestaxrateServiceImp strService;
	
	StateprovinceServiceImp spService;

	@Autowired
	public TaxControllerImpl(SalestaxrateServiceImp strService, StateprovinceServiceImp spService) {
		
		this.strService = strService;
		this.spService = spService;
	}
	
	@GetMapping("/tax/")
	public String indexAddress(Model model) {
		model.addAttribute("taxes", strService.findAll());
		return "tax/index";
	}
	
	@GetMapping("/tax/add")
	public String addTax(Model model) {
		model.addAttribute("tax", new Salestaxrate());
		model.addAttribute("stateprovinces", spService.findAll());

		return "tax/add-tax";
	}
	
	@PostMapping("/tax/add/")
	public String saveTax(@Validated(Miracle.class) @ModelAttribute Salestaxrate str, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("tax", str);

			if (bindingResult.hasErrors()) {
				model.addAttribute("provinces", spService.findAll());


				return "/tax/add-tax";

			}

			strService.save(str);
		}
		return "redirect:/tax/";
	}

}
