package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	
	@GetMapping("/form")
	public String contactForm(Model model) {
		
		// すでにインスタンスが存在する場合は行わない
		if (!model.containsAttribute("contactForm")) {
			
			// ビューにフォームクラスのインスタンスを渡す
			model.addAttribute("contactForm", new ContactForm());
		}
		
		return "contactFormView";
	}
	
	@PostMapping("/confirm")
	public String confirm(RedirectAttributes redirectAttributes, 
		@Validated ContactForm contactForm,
		BindingResult result, Model model) {
		
		// バリデーションエラーがあったら終了
		if (result.hasErrors()) {
			// フォームクラスをビューに受け渡す
			redirectAttributes.addFlashAttribute("contactForm", contactForm);
			// バリデーション結果をビューに受け渡す
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ Conventions.getVariableName(contactForm), result);
			
			// formにリダイレクトして再表示
			return "redirect:/form";
		
		} else {
			// 成功時は確認画面へ
			model.addAttribute("contactForm", contactForm);
		
			return "confirmView";
		}
	}
}