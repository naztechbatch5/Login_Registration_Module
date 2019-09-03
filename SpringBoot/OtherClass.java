//package SpringBoot;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//
//import SpringBoot.entites.ConfirmationToken;
//import SpringBoot.entites.User;
//import SpringBoot.repository.ConfirmationTokenRepository;
//import SpringBoot.service.EmailSenderService;
//
//public class OtherClass {
//	
//	@Autowired
//	private ConfirmationTokenRepository confirmationTokenRepository;
//	@Autowired
//	private EmailSenderService emailSenderService;
//	
//	
//	
//	private static Pattern pswNamePtrn = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
//	
//	public void SendEmail(@Valid User user, String password) {
//
//		ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//		confirmationTokenRepository.save(confirmationToken);
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(user.getEmail());
//		mailMessage.setSubject("Complete Registration!");
//		mailMessage.setFrom("mirajulislam5746@gmail.com");
//		if (password.equals("reset")) {
//			mailMessage.setText("To complete the password reset process, please click here: "
//					+ "http://localhost:8082/confirm-reset?token=" + confirmationToken.getConfirmationToken());
//
//		} else {
//			mailMessage.setText("please click here : " + "http://localhost:8082/confirm-account?token="
//					+ confirmationToken.getConfirmationToken());
//		}
//
//		emailSenderService.sendEmail(mailMessage);
//	}
//	
//	public boolean PasswordCheck(String password) {
//
//		Matcher mtch = pswNamePtrn.matcher(password);
//		if (mtch.matches()) {
//			return true;
//		}
//		return false;
//
//	}
//	
//
//}
