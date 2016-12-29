/**
 * 
 */
package com.lms.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author gurminder.singh
 *
 */
@Component
public class EntityConverter<X,Y> {
	@Autowired
	private ModelMapper modelMapper;
	public  Y convertToDto(X convertFrom, Y convertTo) {
		modelMapper.map(convertTo, convertFrom);
		return convertTo;
	}

}
