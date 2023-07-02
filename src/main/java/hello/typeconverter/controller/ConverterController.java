package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

//    thymeleaf automatically calls its conversion service with the ${{...}}
//    but as thymleaf is well integrated with spring, it calls upon Spring's conversion service,
//    so any converters registered can be used by thymeleaf as well (with end point as a String of course)

//    do not be confused with variable representation ${...} -single curly braces
//    with the conversion service variable representation ${{}} - double curly braces
    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

//    this is showing how to apply converters for Forms
//    GET /converter/edit : prints IpPort on view
//    POST /converter/edit : receives IpPort information from view template form and prints it.

//    for the Post /converter/edit it is the @ModelAttribute calling the Converter,
//    while for the Get /converter/edit it is the th:field that applies the conversion service

//    we will now move on to formatters (formatterController)
    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);
        return "converter-form";
    }
    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "converter-view";
    }
    @Data
    static class Form {
        private IpPort ipPort;
        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }
}
