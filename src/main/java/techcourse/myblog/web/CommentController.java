package techcourse.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import techcourse.myblog.service.comment.CommentService;
import techcourse.myblog.service.dto.comment.CommentRequestDto;
import techcourse.myblog.service.dto.user.UserResponseDto;

import javax.servlet.http.HttpSession;

import static techcourse.myblog.service.user.UserService.USER_SESSION_KEY;

@Controller
public class CommentController {
    final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public ModelAndView addComment(@PathVariable Long articleId, CommentRequestDto commentRequestDto, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        UserResponseDto user = (UserResponseDto) session.getAttribute(USER_SESSION_KEY);
        commentService.save(commentRequestDto, user.getId(), articleId);
        modelAndView.setView(new RedirectView("/articles/" + articleId));
        return modelAndView;
    }

    @PutMapping("/articles/{articleId}/comments/{commentId}")
    public ModelAndView updateComment(@PathVariable Long articleId, @PathVariable Long commentId, HttpSession session, CommentRequestDto commentRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        UserResponseDto user = (UserResponseDto) session.getAttribute(USER_SESSION_KEY);
        modelAndView.setView(new RedirectView("/articles/" + articleId));

        if (user.getId().equals(commentService.findById(commentId).getAuthorId())) {
            commentService.update(commentRequestDto, commentId);
        }
        return modelAndView;
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public ModelAndView deleteComment(@PathVariable Long articleId, @PathVariable Long commentId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new RedirectView("/articles/" + articleId));

        commentService.delete(commentId);
        return modelAndView;
    }
}