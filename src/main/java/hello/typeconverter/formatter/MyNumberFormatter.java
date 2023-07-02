package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

//    the NumberFormat.getInstance(locale) gets locale information and parses
//    a String representation of Number to the respective number format according to locale
//    such as 1000 -> 1,000
//    parse() converts String, with a Number Format to a Number type -> note that parse returns a Long for "1,000"
//    print() parses the Number into a String with the according NumberFormat

//    Test of the methods are done in {MyNumberFormatterTest}

//    ConversionService only allow for the registration of converters but not formatters
//    but this is weird as Formatters are specialist versions of Converters that work on the
//    conversion from Object -> String and String -> Object

//    the reason for the above is that there exists a subclass(child interface?) of ConversionService that
//    allows for the registration of Formatters
//    FormattingConversionService.
//    DefaultFormattingConversionService is the implementation of FormattingConversionService
//    that has basic functionality for formatters

//    refer {FormattingConversionServiceTest}

//    as Formatting ConversionService inherits ConversionService, it allows the registration of both converters and formatters
//    to use the FormattingConversionService's registered converters/formatters, the method convert() does all.
//    for Spring boot, DefaultFormattingConversionService extends WebConversionService internally

//    it should also be noted that converters have higher priority in registration then formatters.
//    if both a formatter and converter are registered in the FormatterRegistry with similar functionalities
//    StringToIntegerConverter() and MyNumberFormatter(),  StringToIntegerConverter() will be used.


//    Formatters have a base format designated(hard-coded) into them, so it is difficult to give each fields of an Object
//    a different format.
//    Spring tries to overcome this problem through annotation processing,
//    with
//    @NumberFormat : use designated formatter for Number Objects, NumberFormatAnnotationFormatterFactory
//    @DateTimeFormat : use designated formatter for DateTime Objects, Jsr310DateTimeFormatAnnotationFormatterFactory
//    refer static class Form below.

//    for further functionalities provided by > @NumberFormat , @DateTimeFormat , refer to the official docs
//    link : https://docs.spring.io/spring-framework/reference/core/validation/format.html#format-CustomFormatAnnotations

//    regardless of converters or formatters, the registration and functionalities differ by individual implementation, but
//    their usage is uniform, through ConversionService's convert() method.

//    do note that HttpMessageConverter does not support ConversionService.
//    there is a common misunderstanding especially during the conversion of an Object into a JSON,
//    HttpMessageConverter functions by converting a Http body's content into an Object
//    through field Mapping, or placing the Object into a Http Body via the opposite process.

//    in this case, the field mapping and the individual conversion library, such as JackSonConverter solely
//    affects the end result. Should one want to use formatter functionalities, it should be through what the library provides,
//    not through this ConversionService.

//    however, conversionService can be used for view templates, @RequestParam, @ModelAttribute, @PathVariable
//    aforementioned in the autoConvert functionality applied by Spring in {HelloConverter}

//    just as a refresher,

//    1. @ModelAttribute , @PathVariable , @RequestParam
//    2. @Value for YML
//    3. XML spring beans
//    4. rendering views

    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text={}, locale={}", text, locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
