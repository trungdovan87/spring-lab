package edu.hanoi.service.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDao;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.Response;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by trungdovan on 12/3/16.
 */
@RestController
public class UserRestServiceController {
    private static final Logger logger = Logger.getLogger(UserRestServiceController.class);

    @Autowired
    UserDao userDao;

    @Autowired
    GroupDAO groupDAO;

    @RequestMapping("/list/users")
    @PreAuthorize("hasRole('USER')")
    @PostFilter("hasPermission(filterObject, 'read')")
    public List<User> listUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("authentication: " + auth.getName() + " - " + auth.getAuthorities());
        return userDao.list();
    }

	//@RequestMapping("/list/users")
	public List<User> listUsers(HttpServletRequest request) {
    	if (!request.isUserInRole("ROLE_ADMIN")) {
    		throw new RuntimeException("Access Denied!!");
	    }
    	return userDao.list();
	}

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        userDao.insert(user);
        return "OK";
    }

    @RequestMapping(value = "/get/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name) {
        return userDao.get(name);
    }

    @RequestMapping(value = "/delete/user/{name}", method = RequestMethod.GET)
    public Response deleteUser(@PathVariable String name) {
         userDao.delete(name);
         return new Response(0, "success");
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public Response updateUser(@RequestBody User user) {
        userDao.update(user);
        return new Response(0, "updated");
    }

    @RequestMapping("/list/groups")
    public List<Group> listGroups() {
        return groupDAO.list();
    }
}
