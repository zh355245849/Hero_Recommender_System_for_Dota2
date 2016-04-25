package com.dota2hero.controllor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RecommenderController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommenderController.class);
	public static void main(String[] args) {
		HeroRecommender hr = new HeroRecommender();
		List<Contribution> cosine = hr.recommendBasedCosineSimilarity("1");
		List<Contribution> pearson = hr.recommendBasedPearsonCorrelationSimilarity("2");
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("player", new Player());
		return "home";
	}
	@RequestMapping(value = "/recommendResult", method = RequestMethod.POST)
	public ModelAndView recommend(@ModelAttribute Player p, ModelMap model, RedirectAttributes reattr) {
		//List<Contribution> cosine = HeroRecommender.recommendBasedCosineSimilarity(playerId);
		HeroRecommender hr = new HeroRecommender();
		if(hr.isExistPlayer(p.getPlayerId())) {
			System.out.println(p.getPlayerId());
			reattr.addAttribute("playerInRange", p);
			return new ModelAndView(new RedirectView("redirect:recommendResult"));
		}
		return new ModelAndView("home");
	}
	@RequestMapping(value = "/recommendResult", method = RequestMethod.GET)
	public String recommendResult(@ModelAttribute("playerInRange") Player p, Model model) {	
		System.out.println(p.getPlayerId());
		List<Contribution> cosine = HeroRecommender.recommendBasedCosineSimilarity(p.getPlayerId());
		List<Contribution> pearson = HeroRecommender.recommendBasedPearsonCorrelationSimilarity(p.getPlayerId());
		model.addAttribute("cosine", cosine);
		model.addAttribute("pearson", pearson);
		return "recommendResult";
	}
}
