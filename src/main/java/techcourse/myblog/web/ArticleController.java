package techcourse.myblog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.repository.ArticleRepository;
import techcourse.myblog.web.dto.ArticleRequestDto;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ArticleController {
    private static final String EXCEPTION_MESSAGE_ARTICLE_NOT_FOUND = "게시물을 찾을 수 없습니다";

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String indexView(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/writing")
    public String writeArticleView() {
        return "article-edit";
    }

    @PostMapping("/articles")
    public String publishArticle(@Valid ArticleRequestDto article, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", true);
            model.addAttribute("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "article-edit";
        }
        Article saved = articleRepository.save(Article.from(article));
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{articleId}")
    public String articleView(@PathVariable Long articleId, Model model) {
        try {
            Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleController::createNotFoundException);
            model.addAttribute("article", article);
            return "article";
        } catch (NoSuchElementException e) {
            return "redirect:/";
        }
    }

    @GetMapping("/articles/{articleId}/edit")
    public String editArticleView(@PathVariable Long articleId, Model model) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(ArticleController::createNotFoundException);
        model.addAttribute("article", article);
        return "article-edit";
    }

    @PutMapping("/articles/{articleId}")
    public String editArticle(@PathVariable Long articleId, ArticleRequestDto reqArticle, Model model) {
        Article article = Article.of(articleId, reqArticle.getTitle(), reqArticle.getCoverUrl(), reqArticle.getContents());
        articleRepository.save(article);
        Article articleToShow = articleRepository.findById(articleId)
            .orElseThrow(ArticleController::createNotFoundException);
        model.addAttribute("article", articleToShow);
        return "article";
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable Long articleId) {
        articleRepository.deleteById(articleId);
        return "redirect:/";
    }

    private static NoSuchElementException createNotFoundException() {
        return new NoSuchElementException(EXCEPTION_MESSAGE_ARTICLE_NOT_FOUND);
    }
}
