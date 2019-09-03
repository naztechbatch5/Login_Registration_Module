package SpringBoot.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import SpringBoot.entites.ConfirmationToken;
import SpringBoot.entites.Question;
import SpringBoot.entites.User;
import SpringBoot.repository.ConfirmationTokenRepository;
import SpringBoot.repository.UserRepository;
import SpringBoot.service.EmailSenderService;
import SpringBoot.service.QuestionService;
import SpringBoot.service.UserService;



@Controller
public class MainController {
	
	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserRepository userRepository;
	
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	private static Pattern pswNamePtrn = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
	
	@Autowired
	private QuestionService questionService;
//	show register page
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView login(Model mod) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index"); // resources/template/login.html
		return modelAndView;
	}
	
//	show register page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Model mod) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		List<Question>questions=questionService.GetAllQues();
		mod.addAttribute("questions",questions);			
		 modelAndView.addObject("user", user);	
		 modelAndView.setViewName("register");
		
		return modelAndView;
	}
	
	
//	show login page
	@RequestMapping(value = {"/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	
	
	//show user home page
		@RequestMapping(value = "/userhome", method = RequestMethod.GET)
		public ModelAndView home() {
			ModelAndView modelAndView = new ModelAndView();
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 User user = userService.findByEmail(auth.getName());
			 modelAndView.addObject("userName", user.getTx_user_name());
			modelAndView.setViewName("UserHome"); // resources/template/home.html
			return modelAndView;
		}
		
		//show user home page
		@RequestMapping(value = "/admin", method = RequestMethod.GET)
		public ModelAndView adminHome() {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("AdminHome"); // resources/template/admin.html
			return modelAndView;
		}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap,
		@RequestParam("tx_user_password") String tx_user_password,@RequestParam("tx_role_name") String tx_role_name) {
		user.setTx_created_date(timestamp);
		user.setTx_update_date(timestamp);

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());
//		otherClass.
		boolean passwordCheck =PasswordCheck(tx_user_password);
		
		
		if (passwordCheck == false) {
			bindingResult.rejectValue("tx_user_password", "error.user",
					"Your Password is Not Strong...(Sample password Dhaka#1212");
		}

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		}else if (userService.isUserAlreadyPresent(user)) {
			modelAndView.addObject("successMessage", "user already exists!");
		}

// we will save the user if, no binding errors
		else {
			
			if(tx_role_name.equals("SITE_USER")) {
				userService.saveUser(user);
			}else {
				userService.saveAdmin(user);
			}
		
			SendEmail(user, tx_user_password);

			modelAndView.addObject("email", user.getEmail());

			modelAndView.setViewName("successfulRegisteration");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {

			User user = userRepository.findByEmail(token.getUser().getEmail());
			user.setEnabled(true);
			
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}
	
	public boolean PasswordCheck(String password) {

		Matcher mtch = pswNamePtrn.matcher(password);
		if (mtch.matches()) {
			return true;
		}
		return false;

	}
	
	public void SendEmail(@Valid User user, String password) {

		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("mirajulislam5746@gmail.com");
		if (password.equals("reset")) {
			mailMessage.setText("To complete the password reset process, please click here: "
					+ "http://localhost:8082/confirm-reset?token=" + confirmationToken.getConfirmationToken());

		} else {
			mailMessage.setText("please click here : " + "http://localhost:8082/confirm-account?token="
					+ confirmationToken.getConfirmationToken());
		}

		emailSenderService.sendEmail(mailMessage);
	}
	}

