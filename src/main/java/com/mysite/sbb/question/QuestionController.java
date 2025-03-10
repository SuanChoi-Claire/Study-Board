package com.mysite.sbb.question;

import java.util.List;
import java.security.Principal;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.mysite.sbb.answer.AnswerForm;
import org.springframework.security.access.prepost.PreAuthorize;




@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	 private final UserService userService;
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "question_detail";
	}
	
	//private final QuestionRepository questionRepository;
	
	
	@GetMapping("/list")
//	@ResponseBody
	public String list(Model model, @RequestParam(value="page", defaultValue="0")int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
		return "question_list";
				
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate
	(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}

}
