package com.csf.gaf.web.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.csf.gaf.domain.entity.Article;
import com.csf.gaf.service.ArticleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	
	@GetMapping("/hello")
	public String allAccess() {
		return "HELLO from article :) !.";
	}
	
	
	// get all Articles
	@GetMapping("/findAll")
	public List<Article> getAllArticle() {
		return articleService.listAll();
	}

	// get Articles by id
	@GetMapping("/find/{idArticle}")
	public Article getArticleById(@PathVariable(value = "idArticle") String id) {
		return articleService.get(id);
	}

	// create Articles
	@PostMapping("/save")
	public Article save(@RequestBody Article article) throws Exception {
		return articleService.save(article);
	}

	// update Articles
	@PostMapping("/update")
	public Article update(@RequestBody Article article) throws Exception {
		return articleService.save(article);
	}

	// delete Article by id
	@DeleteMapping("delete/{idArticle}")
	public String deleteArticle(@PathVariable("idArticle") String id) {
		articleService.delete(id);
		return  " Article is deleted";
	}


}
