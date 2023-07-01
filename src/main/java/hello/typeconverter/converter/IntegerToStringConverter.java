package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;


//In order to facilitate easy conversion from one type to another,
//Spring provides a Converter interface as follows
//
//package org.springframework.core.convert.converter;
//public interface Converter<S, T> {
//    T convert(S source);
//}

//which can be implemented by another class and then REGISTERED
//This converter Interface can be implemented to all types.
//One just needs to create a converter from T -> S and S -> T (bi-directional?)

//previously, PropertyEditor was used but propertyEditor is not thread safe so it has less use nowadays

//Without a class to register, and manually used ( refer ConverterTest ), using the converter interface is quite
//meaningless, as it involves the developer manually calling out the convert methods from a given implemented converter class.

//so Spring provides various types of converters according to different use cases

//> Converter -> base type
//> ConverterFactory -> when entire class hierarchy needs to be shown
//> GenericConverter -> precise and complicated implementation, with the use of field annotation information
//> ConditionalGenericConverter -> conditional converters

//and a lot of other converters for base java types, enums , etc. etc.

//further documentation : https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#core-convert

//Spring also provides a class to manage these vast arrays of converters, which allows the abstraction of the individual converters
//known as ConversionService,

//key point is, they allow (dependency injection?)
//ConversionService conversionService = new DefaultConversionService();
//conversionService.convert(10,String.class) for registered classes, without doing
//IntegerToStringConverter converter = new IntegerToStringConverter;
//converter.convert()

//refer to ConversionServiceTest for actual usage.

//DefaultConversionService is an implementation of the ConversionService Interface,
//with an extra feature(implementation) to REGISTER CONVERTERS(ConverterRegistry)

//this also abides by the Interface Segregation Principle
//where the client does not rely on methods that are not used by them.
//since the devs only need to focus on the ConverterRegistry(registration)
//while the users only need to use the ConversionService(.convert()) part of the DefualtConversionService class

//further comments -> https://ko.wikipedia.org/wiki/%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4_%EB%B6%84%EB%A6%AC_%EC%9B%90%EC%B9%99

//It should be noted that Spring implements and provides ConversionService internally, as can be seen from the automatic conversion
//of parameters with @RequestParams, mentioned above.
//so, we only need to register the converters created somewhere, which is the
//addFormatters(FormatterRegistry registry) method overridden, in WebConfig (refer there)


//trivia
//@RequestParam -> ArgumentResolver -> RequestParamMethodArgumentResolver -> ConversionService is the actual(not complete but roughly)
//control flow for the type conversion.

//refer ConverterController for application of converters in view templates.
//mostly present to convert Object -> String needed in the View


@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        log.info("convert source={}", source);
        return String.valueOf(source);
    }
}
