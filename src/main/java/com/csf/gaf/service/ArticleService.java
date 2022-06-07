package com.csf.gaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.gaf.domain.entity.Article;
import com.csf.gaf.repository.ArticleRepository;


@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> listAll() {
		return articleRepository.findAll();	
	}
	
	public Article save(Article article){
		articleRepository.save(article);
		return article;
	}
	
	public Article get(String id) {
		return articleRepository.getById(id);
	}
	
	public void delete(String id) {
		articleRepository.deleteById(id);
	}

}