package co.gv.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String errorHandler(Exception ex, Model model) {
		log.error(ex.getLocalizedMessage());
		model.addAttribute("ex", ex);
		return "err";
	}
}
