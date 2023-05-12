package com.denniskurilov.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.denniskurilov.models.Employee;
import com.denniskurilov.models.EmployeeService;

@Controller
public class EmployeeController {
	  
	@Autowired
    private EmployeeService employeeService;
    
	@GetMapping("/")
	public String index(Model model) {
		List<Employee> listEmployee = employeeService.getAll();
		model.addAttribute("employees", listEmployee);
		return "index";
	}

	@GetMapping("/search")
	public String search(Model model) {
		List<Employee> listEmployee = new ArrayList<> ();
		model.addAttribute("employees", listEmployee);
		return "search";
	}

	@PostMapping("/search")
	public String search(@RequestParam String keyword, Model model) {
		List<Employee> listEmployee = employeeService.search(keyword);
		model.addAttribute("employees", listEmployee);
		return "search";
	}

	@GetMapping("/{id}")
	public String get(@PathVariable("id") long id, Model model) {
		model.addAttribute("employee", employeeService.get(id));
		return "item";
	}

	@GetMapping("/new")
	public String create(@ModelAttribute("employee") Employee employee) {
		return "new";
	}

	@PostMapping("/new")
	public String create(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new";
		}
		employeeService.save(employee);
		return "redirect:/";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("employee", employeeService.get(id));
		return "edit";
	}

	@PatchMapping("/{id}/edit")
	public String update(@PathVariable("id") long id, @ModelAttribute("employee") @Valid Employee employee,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}
		employee.setId(id);
		employeeService.save(employee);
		return "redirect:/" + id;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		employeeService.delete(id);
		return "redirect:/";
	}

}
