package com.axity.arquetipo.commons.aspectj;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.axity.arquetipo.commons.exception.BusinessException;
import com.axity.arquetipo.commons.exception.ValidationException;
import com.axity.arquetipo.commons.response.GenericResponseDto;

class JsonResponseHandlerInterceptorTest
{
  private JsonResponseHandlerInterceptor interceptor;

  @BeforeEach
  void init()
  {
    interceptor = new JsonResponseHandlerInterceptor();
    var env = mock( Environment.class );
    when( env.getProperty( anyString() ) ).thenReturn( "An error..." );
    ReflectionTestUtils.setField( interceptor, "env", env );
  }

  @Test
  void testInterceptMethodAdvice() throws Throwable
  {
    var pjp = mock( ProceedingJoinPoint.class );
    when( pjp.toLongString() ).thenReturn( "mocked" );
    when( pjp.proceed() ).thenReturn( true );
    var result = interceptor.interceptMethodAdvice( pjp );

    assertNotNull( result );
    assertTrue( (boolean) result );
  }

  @Test
  void testInterceptMethodAdvice_throwsValidationException() throws Throwable
  {
    var pjp = mock( ProceedingJoinPoint.class );
    when( pjp.toLongString() ).thenReturn( "mocked" );
    when( pjp.proceed() ).thenThrow( new ValidationException( "An error has occurred!!!" ) );

    var result = interceptor.interceptMethodAdvice( pjp );

    assertNotNull( result );
    assertInstanceOf( ResponseEntity.class, result );
    var response = (ResponseEntity) result;
    assertNotNull( response.getHeaders() );
    assertNotNull( response.getBody() );
    assertInstanceOf( GenericResponseDto.class, response.getBody() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getMessage() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getCode() );
  }

  @Test
  void testInterceptMethodAdvice_throwsBusinessException() throws Throwable
  {
    var pjp = mock( ProceedingJoinPoint.class );
    when( pjp.toLongString() ).thenReturn( "mocked" );
    when( pjp.proceed() ).thenThrow( new BusinessException( "An error has occurred!!!" ) );

    var result = interceptor.interceptMethodAdvice( pjp );

    assertNotNull( result );
    assertInstanceOf( ResponseEntity.class, result );
    var response = (ResponseEntity) result;
    assertNotNull( response.getHeaders() );
    assertNotNull( response.getBody() );
    assertInstanceOf( GenericResponseDto.class, response.getBody() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getMessage() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getCode() );
  }
  
  @Test
  void testInterceptMethodAdvice_throwsExceptionWithTrace() throws Throwable
  {
    ReflectionTestUtils.setField( interceptor, "allowTrace", true );
    
    var pjp = mock( ProceedingJoinPoint.class );
    when( pjp.toLongString() ).thenReturn( "mocked" );
    when( pjp.proceed() ).thenThrow( new IllegalArgumentException( "An error has occurred!!!" ) );

    var result = interceptor.interceptMethodAdvice( pjp );

    assertNotNull( result );
    assertInstanceOf( ResponseEntity.class, result );
    var response = (ResponseEntity) result;
    assertNotNull( response.getHeaders() );
    assertNotNull( response.getBody() );
    assertInstanceOf( GenericResponseDto.class, response.getBody() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getMessage() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getCode() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getDetail() );
  }
  
  @Test
  void testInterceptMethodAdvice_throwsExceptionWithouTrace() throws Throwable
  {
    ReflectionTestUtils.setField( interceptor, "allowTrace", false );
    
    var pjp = mock( ProceedingJoinPoint.class );
    when( pjp.toLongString() ).thenReturn( "mocked" );
    when( pjp.proceed() ).thenThrow( new IllegalArgumentException( "An error has occurred!!!" ) );

    var result = interceptor.interceptMethodAdvice( pjp );

    assertNotNull( result );
    assertInstanceOf( ResponseEntity.class, result );
    var response = (ResponseEntity) result;
    assertNotNull( response.getHeaders() );
    assertNotNull( response.getBody() );
    assertInstanceOf( GenericResponseDto.class, response.getBody() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getMessage() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getCode() );
    assertNotNull( ((GenericResponseDto)response.getBody()).getHeader().getDetail() );
  }
}
