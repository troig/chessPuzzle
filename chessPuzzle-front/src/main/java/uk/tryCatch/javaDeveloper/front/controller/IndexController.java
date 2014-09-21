package uk.tryCatch.javaDeveloper.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the index.
 *
 * @author troig
 */
@Controller
@RequestMapping("/index")
public class IndexController {

   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView index(){
      /** TODO (troig 21/09/2014): Implement */
      return new ModelAndView("index");
   }
}