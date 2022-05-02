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
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.SalesTerritoryService;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class StateprovinceControllerImpl {
	
	StateprovinceServiceImp spService;
	
	CountryregionServiceImp crService;
	
	SalesTerritoryService stService;

	@Autowired
	public StateprovinceControllerImpl(StateprovinceServiceImp spService, 
			CountryregionServiceImp crService,
			SalesTerritoryService stService) {
		
		this.spService = spService;
		this.crService = crService;
		this.stService = stService;
	}
	
	@GetMapping("/stateprovince/")
	public String indexStateprovince(Model model) {
		model.addAttribute("stateprovinces", spService.findAll());
		return "stateprovince/index";
	}
	
	@GetMapping("/stateprovince/add")
	public String addStateprovince(Model model) {
		model.addAttribute("stateprovince", new Stateprovince());
		model.addAttribute("countryregions", crService.findAll());
		System.out.println(crService.findAll());
		model.addAttribute("salesterritory", stService.findAll());

		return "stateprovince/add-stateprovince";
	}
	
	@PostMapping("/stateprovince/add/")
	public String saveStateprovince(@Validated(Miracle.class) @ModelAttribute Stateprovince sp, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("stateprovince", sp);

			if (bindingResult.hasErrors()) {
				model.addAttribute("salesterritory", stService.findAll());
				model.addAttribute("contryregion", crService.findAll());


				return "/stateprovince/add-stateprovince";

			}

			spService.save(sp);
		}
		return "redirect:/stateprovince/";
	}
	

}
