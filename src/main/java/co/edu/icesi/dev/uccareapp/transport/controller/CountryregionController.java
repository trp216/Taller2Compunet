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
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

@Controller
public class CountryregionController {
	
	CountryregionServiceImp crService;

	@Autowired
	public CountryregionController(CountryregionServiceImp crService) {
		super();
		this.crService = crService;
	}
	
	@GetMapping("/countryregion/")
	public String indexCountryregion(Model model) {
		model.addAttribute("countries", crService.findAll());
		return "countryregion/index";
	}
	
	@GetMapping("/countryregion/add")
	public String addCountryregion(Model model) {
		model.addAttribute("countryregion", new Countryregion());

		return "countryregion/add-countryregion";
	}
	
	@PostMapping("/countryregion/add/")
	public String saveCountryregion(@Validated(Miracle.class) @ModelAttribute Countryregion countryregion, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("countryregion", countryregion);

			if (bindingResult.hasErrors()) {
				

				return "/countryregion/add-countryregion";

			}

			crService.save(countryregion);
		}
		return "redirect:/countryregion/";
	}

}
