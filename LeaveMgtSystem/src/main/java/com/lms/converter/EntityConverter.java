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
	public  X convertToDto(X convertTo, Y convertFrom) {
		modelMapper.map(convertFrom, convertTo);
		return convertTo;
	}

}
