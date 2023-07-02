package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

//Converters allows for the conversion from one type to another should
//it be implemented(unless already provided by spring)
//However, in many use cases, conversion from one object to another object is
//rarer than conversion of one object into String and vice versa
//
//Formatters are the specialist Converters that specialises in the toString()
//method of an Object and vice versa.

//Converter : generic (Object - Object)
//Formatter : specialist (Object String, String Object) + Locale

//Formatter interface implements the 2 methods

//String print(T object, Locale locale) : convert Object to String
//T parse(String text, Locale locale) : convert String to Object

//public interface Printer<T> {
//    String print(T object, Locale locale);
//}
//public interface Parser<T> {
//    T parse(String text, Locale locale) throws ParseException;
//}
//public interface Formatter<T> extends Printer<T>, Parser<T> {
//}

//refer implementation of Formatter in {MyNumberFormatter}

//Formatters are provided in various forms by Spring according to their usage such as
// Formatter : base form
// AnnotationFormatterFactory : allows reference to the field's type and annotation information
//further info at official docs
//https://docs.spring.io/spring-framework/reference/core/validation/format.html

@Controller
public class FormatterController {
    @GetMapping("/formatter/edit")
    public String formatterForm(Model model) {
        Form form = new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", form);
        return "formatter-form";
    }
    @PostMapping("/formatter/edit")
    public String formatterEdit(@ModelAttribute Form form) {
        return "formatter-view";
    }
    @Data
    static class Form {
        @NumberFormat(pattern = "###,###")
        private Integer number;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}
