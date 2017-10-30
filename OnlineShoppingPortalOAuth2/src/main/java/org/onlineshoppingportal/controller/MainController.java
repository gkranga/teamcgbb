package org.onlineshoppingportal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.onlineshoppingportal.authentication.SecurityService;
//import org.onlineshoppingportal.dao.AccountDao;
//import org.onlineshoppingportal.dao.OrderDao;
//import org.onlineshoppingportal.dao.ProductDao;
//import org.onlineshoppingportal.entity.Account;
//import org.onlineshoppingportal.entity.Order;
//import org.onlineshoppingportal.entity.Product;
import org.onlineshoppingportal.exceptions.AlreadyLoginException;
import org.onlineshoppingportal.exceptions.NoOrderFoundException;
import org.onlineshoppingportal.exceptions.ProductOutOfStockException;
//import org.onlineshoppingportal.validator.AccountValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
@Transactional //Enable Hibernate Transaction.
@RequestMapping("/")
public class MainController {

    //public static Logger logger = Logger.getLogger(MainController.class);
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //@Autowired
    //private AccountDao accountDao;
	//@Autowired
    //private OrderDao orderDAO;
    //@Autowired
    //private ProductDao productDAO;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if(logger.isDebugEnabled())
            logger.debug("Target ="+target);

        //if(target.getClass() == Account.class)
        //	dataBinder.setValidator(accountValidator);
    }
    
    // Product List page.
    @RequestMapping({ "/productList","/" })
    public String listProductHandler(Model model) {
        //model.addAttribute("list", productDAO.getAllProducts());
        return "productList";
    }
    
    @RequestMapping("/403")
    public String accessDenied(HttpServletRequest request) {
        logger.error("Access Denied URL = " + request.getRequestURL());
        return "/403";
    }
    
    // GET: Show Login Page
    //@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    //public String login(HttpServletRequest request) {
    //	if(request.getUserPrincipal() != null)
    //		throw new AlreadyLoginException();
    //    return "login";
    //}

    @RequestMapping({ "/buyProduct" })
    public String buyProduct(HttpServletRequest request, Model model, @RequestParam(value = "code", defaultValue = "") String code) {
    	if(code.isEmpty())
    	{
    		return "redirect:/productList";
    	}
    	
        //Product product = null;
        //if (code != null && code.length() > 0) {
        //    product = productDAO.findProduct(code);
        //}
        //if (product != null) {
        //	if(product.getQuantity() == 0)
        //	{	
		//		logger.error("Product out of stock having code "+code);
        //		throw new ProductOutOfStockException();
        //	}
        //	model.addAttribute("product",product);
        //}
        return "confirmation";
    }
 
    
    @RequestMapping(value = { "/purchase" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException
    @Transactional(propagation = Propagation.NEVER)
    public String purchaseProduct(HttpServletRequest request, Model model) {
    	String code = request.getParameter("code");
    	int quantity =Integer.parseInt( request.getParameter("quantity"));
        //Order order = orderDAO.saveOrder(code, quantity,securityService.findLoggedInUsername());
        //model.addAttribute("order", order);
    	 if(logger.isInfoEnabled())
    	        logger.info("Product purchased having code " + code + ", quantity " + quantity);
        return "orderDetail";
    }
    
    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String getOrderByUsername(HttpServletRequest request, Model model) {
    	//List<Order> list = orderDAO.getOrdersByUserName(securityService.findLoggedInUsername());
    	////if(list.isEmpty())
    	//	throw new NoOrderFoundException();
    	//model.addAttribute("list",list);
        return "orderList";
    }
}