package de.myproject.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LandingPageController {
    
    @GetMapping("/")
    public String start(Model model, Locale locale) {

        model.addAttribute("erstesErgebnis", 0);
        model.addAttribute("zweitesErgebnis", 0);
        model.addAttribute("drittesErgebnis", 0);

        return "portfolio";
    }
    
    @PostMapping("/result")
    public String resultPage(Model model, Locale locale, examResult examResult) {

        model.addAttribute("erstesErgebnis", examResult.r1);
        model.addAttribute("zweitesErgebnis", examResult.r2);
        model.addAttribute("drittesErgebnis", examResult.r3);

        double ergebnisDouble = examResult.Rechner();
        String ergebnisString = examResult.RechnerString();
        boolean ergebnisBoolean = examResult.RechnerBoolean();

        model.addAttribute("ergebnis", ergebnisDouble);
        model.addAttribute("ergebnisString", ergebnisString);

        
        return "portfolio";
    }

    record examResult(double r1, double r2, double r3){

        public double Rechner(){

            double result = ((r1 * 0.2) + (r2 * 0.2) + (r3 *0.6));

            return result;

        }

        public String RechnerString(){

            double result = Rechner();

            if(result >= 50){
                return "BESTANDEN!!!";
            }else {
                return "NICHT bestanden...";
            }

        }

        public boolean RechnerBoolean(){

            double result = Rechner();

            if(result >= 50){
                return true;
            }else {
                return false;
            }


        }
    }
    

}
