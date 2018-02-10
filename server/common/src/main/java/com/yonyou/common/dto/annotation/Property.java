package com.yonyou.common.dto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;

import com.yonyou.common.dto.PropertyType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Property {

	String value() default StringUtils.EMPTY;
	
	PropertyType type() default PropertyType.String;
	
	String format() default StringUtils.EMPTY;
	
}
