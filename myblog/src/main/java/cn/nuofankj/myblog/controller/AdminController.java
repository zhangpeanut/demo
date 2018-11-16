package cn.nuofankj.myblog.controller;

import cn.nuofankj.myblog.constant.FriendTipData;
import cn.nuofankj.myblog.dto.impl.*;
import cn.nuofankj.myblog.pojo.TagsPojo;
import cn.nuofankj.myblog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogapi/index.php/a")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageDto login(String username, String password) {
        try {
            AdminUserDto login = adminService.login(username, password);
            return MessageDto.valueOf(login, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }


    @RequestMapping("/error")
    public MessageDto error(String username, String password) {
        try {
            return MessageDto.valueOf("验证身份失败", FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/statistics/home")
    public MessageDto statisticsHome() {
        try {
            StatisticsDto statisticsDto = adminService.statisticsHome();
            return MessageDto.valueOf(statisticsDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/sys/log")
    public MessageDto sysLog(int page, int pageSize) {
        try {
            SysListDto sysListDto = adminService.sysLog(page, pageSize);
            return MessageDto.valueOf(sysListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/article/list")
    public MessageDto articleList(String by, int status, int page, int pageSize) {
        try {
            AdminArticleListDto adminArticleListDto = adminService.articleList(by, status, page, pageSize);
            return MessageDto.valueOf(adminArticleListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/comments/alllist")
    public MessageDto commentsList(int page, int pageSize) {
        try {
            CommentListDto commentListDto = adminService.commentsList(page, pageSize);
            return MessageDto.valueOf(commentListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/article/save")
    public MessageDto commentsList(String id, String content, String htmlContent, String title, String cover, String subMessage, String isEncrypt) {
        try {
            String articleId = adminService.saveArticle(id, content, htmlContent, title, cover, subMessage, isEncrypt);
            return MessageDto.valueOf(articleId, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }


    // TODO 最后两个参数我实在有点日狗了，完全不知道什么格式
    @RequestMapping("/article/publish")
    public MessageDto publish(String id, String content, String htmlContent, String title, String cover, String subMessage, String isEncrypt, @RequestParam(value = "category[]") List<String> category, @RequestParam(value = "tags[][]") List<String> tags) {
        try {
            String articleId = adminService.publish(id, content, htmlContent, title, cover, subMessage, isEncrypt, category.get(0),tags.get(0));
            return MessageDto.valueOf(articleId, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/category/list")
    public MessageDto categoryList(String all) {
        try {
            CategoryListDto categoryListDto = adminService.categoryList(all);
            return MessageDto.valueOf(categoryListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/tag/list")
    public MessageDto tagList(String all) {
        try {
            TagListDto tagListDto = adminService.tagList(all);
            return MessageDto.valueOf(tagListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/article/info")
    public MessageDto articleInfo(String id) {
        try {
            ArticleDetailDto articleDetailDto = adminService.articleInfo(id);
            return MessageDto.valueOf(articleDetailDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    // TODO 此处的tags数组参数待定
    @RequestMapping("/article/modify")
    public MessageDto articleModify(String title, String cover, String subMessage, String isEncrypt, String content, String htmlContent, String id, @RequestParam("category[id]") String categoryId, TagsPojo[] tags) {
        try {
            String articleId = adminService.modifyArticle(title, cover, subMessage, isEncrypt, content, htmlContent, id, categoryId, tags);
            return MessageDto.valueOf(articleId, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/article/delete")
    public MessageDto articleDelete(String id) {
        try {
            adminService.articleDelete(id);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/category/add")
    public MessageDto addCategory(String categoryName) {
        try {
            String id = adminService.addCategory(categoryName);
            return MessageDto.valueOf(id, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/category/modify")
    public MessageDto modifyCategory(String categoryId, String categoryName) {
        try {
            adminService.modifyCategory(categoryId, categoryName);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/category/delete")
    public MessageDto deleteCategory(String categoryId, String categoryName) {
        try {
            adminService.deleteCategory(categoryId);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/tag/add")
    public MessageDto addTag(String tagName) {
        try {
            String id = adminService.addTag(tagName);
            return MessageDto.valueOf(id, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/tag/delete")
    public MessageDto deleteTag(String tagId) {
        try {
            adminService.deleteTag(tagId);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/tag/modify")
    public MessageDto modifyTag(String tagId, String tagName) {
        try {
            adminService.modifyTag(tagId, tagName);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/comments/delete")
    public MessageDto deleteComments(long id) {
        try {
            adminService.deleteComments(id);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/comments/add")
    public MessageDto replyComments(String articleId, long replyId, String content, String sourceContent) {
        try {
            adminService.replyComments(articleId, replyId, content, sourceContent);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/webConfig")
    public MessageDto webConfig() {
        try {
            BlogConfigDto blogConfigDto = adminService.webConfig();
            return MessageDto.valueOf(blogConfigDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/webConfig/getAbout")
    public MessageDto getAbout() {
        try {
            PageMDDto about = adminService.getAbout();
            return MessageDto.valueOf(about, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/friends/list")
    public MessageDto friendsList(int page, int pageSize) {
        try {
            FriendListDto friendListDto = adminService.friendList(page, pageSize);
            return MessageDto.valueOf(friendListDto, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/friends/modify")
    public MessageDto modifyFriends(String name, String url, int type, String friends) {
        try {
            adminService.modifyFriends(name, url, type, friends);
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/friends/delete")
    public MessageDto deleteFriends(String friendId) {
        try {
            adminService.deleteFriends(friendId);            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }

    @RequestMapping("/test")
    public MessageDto test() {
        try {
            return MessageDto.valueOf(null, FriendTipData.SUCCESS_CODE, FriendTipData.SUCCESS_MSG, true);
        } catch (Exception e) {
            log.error("error",e);
            return MessageDto.valueOf(null, FriendTipData.ERROR_CODE, FriendTipData.ERROR_MSG, false);
        }
    }
}